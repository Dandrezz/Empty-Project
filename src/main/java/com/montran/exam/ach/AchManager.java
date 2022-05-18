package com.montran.exam.ach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.montran.exam.exceptions.AchException;
import com.montran.exam.exceptions.FileReaderUtilException;
import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.parser.formats.FormatRtgs;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.transaction.impl.TransacionAch;
import com.montran.exam.transaction.impl.TransactionRtgs;
import com.montran.exam.utils.FileReaderUtil;

/**
 * This class is used to manage ACH transactions logic
 * @author Diego Portero
 *
 */
public class AchManager {

	private Log log;

	private static AchManager instance;

	/**
	 * Record transactions
	 */
	private List<TransacionAch> transactions;
	
	private AchManager() throws LogException {
		transactions = new ArrayList<TransacionAch>();
		log = Log.getInstance();
	}

	/**
	 * Get the instance of this class
	 * 
	 * @return the unique instance of the class
	 * @throws LogException 
	 */
	public static AchManager getInstance() throws LogException {
		if (instance == null) {
			synchronized (AchManager.class) {
				if (instance == null) {
					instance = new AchManager();
				}
			}
		}
		return instance;
	}
	
	public void executeTransactions() {
		
	}

	/**
	 * This method read the content of the content of the file that the ach module
	 * reads
	 * 
	 * @return a String with the content of the file
	 * @throws FileReaderUtilException
	 * @throws LogException 
	 */
	public String readFileACH() throws FileReaderUtilException, LogException {
		return FileReaderUtil.getInstance().readFile("ach_file.txt");
	}

	/**
	 * This method parses the content of the file that the ach module reads
	 * 
	 * @return
	 * @throws AchException
	 * @throws LogException 
	 */
	public String[] parsinData() throws AchException, LogException {
		String data;
		try {
			data = readFileACH();
		} catch (FileReaderUtilException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new AchException(e.getMessage(), e);
		}
		String[] lines = data.split("\n");
		if (lines.length < 1) {
			log.write("Format invalid", LogLevels.ERROR);
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

	public void transactionProcessing() throws AchException, LogException {
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
			log.write("Format invalid", LogLevels.ERROR);
			throw new AchException("Error in the parsin", e);
		} catch (ParticipantException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new AchException(e.getMessage(), e);
		}
	}

}
