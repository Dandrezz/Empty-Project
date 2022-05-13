package com.montran.exam.ach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.montran.exam.exceptions.AchException;
import com.montran.exam.exceptions.FileReaderUtilException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.transaction.TransacionAch;
import com.montran.exam.transaction.TransactionRtgs;
import com.montran.exam.utils.FileReaderUtil;

/**
 * @author Diego Portero
 *
 */
public class AchManager {

	private Log log = Log.getInstance();

	private static AchManager instance;

	/**
	 * Record transactions
	 */
	private List<TransacionAch> transactions;
	
	public AchManager() {
		transactions = new ArrayList<TransacionAch>();
	}

	/**
	 * Get the instance of this class
	 * 
	 * @return the unique instance of the class
	 */
	public static AchManager getInstance() {
		if (instance == null) {
			synchronized (AchManager.class) {
				if (instance == null) {
					instance = new AchManager();
				}
			}
		}
		return instance;
	}

	/**
	 * This method read the content of the content of the file that the ach module
	 * reads
	 * 
	 * @return a String with the content of the file
	 * @throws FileReaderUtilException
	 */
	public String readFileACH() throws FileReaderUtilException {
		return FileReaderUtil.getInstance().readFile("ach_file.txt");
	}

	/**
	 * This method parses the content of the file that the ach module reads
	 * 
	 * @return
	 * @throws AchException
	 */
	public String[] parsinData() throws AchException {
		String data;
		try {
			data = readFileACH();
		} catch (FileReaderUtilException e) {
			log.write(e.getMessage(), getClass(), LogLevels.ERROR);
			throw new AchException(e.getMessage(), e);
		}
		String[] lines = data.split("\n");
		if (lines.length < 1) {
			log.write("Format invalid", getClass(), LogLevels.ERROR);
			throw new AchException("Format invalid");
		}
		for (int i = 1; i < lines.length; i++) {
			if (!validateNextLine(lines[i])) {
				throw new AchException("Format invalid");
			}
		}
		return lines;
	}

	public boolean validateFirstLine(String line) {
		return line.split("|").length == 5;
	}

	public boolean validateNextLine(String line) {
		String div[] = line.split("\\|");
		return div.length == 5;
	}

	public void transactionProcessing() throws AchException {
		try {
			String lines[] = parsinData();
			String[] header = lines[0].split("\\|");
			String currencyTrasaction = header[1];

			Map<String, BigDecimal> individualPaymets = new HashMap<String, BigDecimal>();

			for (int i = 1; i < lines.length; i++) {
				String[] pay = lines[i].split("\\|");
				String aux = pay[4].replace("\r", "");
				if (individualPaymets.containsKey(pay[1])) {
					BigDecimal newValue = individualPaymets.get(currencyTrasaction);
					individualPaymets.remove(currencyTrasaction);
					newValue.add(new BigDecimal(aux));
					individualPaymets.put(currencyTrasaction, newValue);
				} else {
					individualPaymets.put(currencyTrasaction, new BigDecimal(aux));
				}
			}
			
			for (Map.Entry<String, BigDecimal> entry : individualPaymets.entrySet()) {
				ParticipantManger.getInstance().balanceAccounts(header[1], entry.getKey(), header[2],entry.getValue() );
			}
			
			TransacionAch transacionAch = new TransacionAch();
			transacionAch.setAmount(new BigDecimal(header[5].replace("\r", "")));
			transacionAch.setCurrency(currencyTrasaction);
			transacionAch.setNumberIndivitualPayments(lines.length-1);
			transacionAch.setReceived(LocalDate.now());
			transacionAch.setSwiftCodeSenderParticipant(header[1]);
			
			transactions.add(transacionAch);
			
		} catch (AchException e) {
			log.write("Format invalid", getClass(), LogLevels.ERROR);
			throw new AchException("Error in the parsin", e);
		} catch (ParticipantException e) {
			log.write(e.getMessage(), getClass(), LogLevels.ERROR);
			throw new AchException(e.getMessage(), e);
		}
	}

}
