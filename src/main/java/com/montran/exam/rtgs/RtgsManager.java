package com.montran.exam.rtgs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.montran.exam.exceptions.FileReaderUtilException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.participant.Participant;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.status.TransactionStatus;
import com.montran.exam.transaction.TransactionRtgs;
import com.montran.exam.utils.FileReaderUtil;
import com.montran.exam.utils.UUIDGenerator;

public class RtgsManager {

	private Log log = Log.getInstance();
	
	private ParticipantManger participantManger = ParticipantManger.getInstance();
	/**
	 * Record of transactions
	 */
	private List<TransactionRtgs> transactions;
	
	private static RtgsManager instance;

	public RtgsManager() {
		transactions = new ArrayList<TransactionRtgs>();
	}
	
	/**
	 * Get the instance of this class
	 * @return the unique instance of the class
	 */
	public static RtgsManager getInstance() {
		if(instance ==null) {
			synchronized(RtgsManager.class) {
				if(instance ==null) {
					instance = new RtgsManager();
				}
			}
		}
		return instance;
	}

	/**
	 * This method read the content of the content of the file that the RTGS module
	 * reads
	 * 
	 * @return a String with the content of the file
	 * @throws FileReaderUtilException
	 */
	public String readFileRTGS() throws FileReaderUtilException {
		return FileReaderUtil.getInstance().readFile("rtgs");
	}

	/**
	 * This method to create a new object of type transaction
	 * 
	 * @param swiftCodeSenderParticipant
	 * @param swiftCodeReceiverParticipant
	 * @param amount
	 * @param currency
	 * @return a instance of object transaction
	 */
	public TransactionRtgs createTransaction(String swiftCodeSenderParticipant, String swiftCodeReceiverParticipant,
			BigDecimal amount, String currency) {
		TransactionRtgs newTransaction = new TransactionRtgs();
		newTransaction.setIdTransaction(UUIDGenerator.getInstance().getUuid32bits());
		newTransaction.setSwiftCodeSenderParticipant(swiftCodeSenderParticipant);
		newTransaction.setSwiftCodeReceiverParticipant(swiftCodeReceiverParticipant);
		newTransaction.setAmount(amount);
		newTransaction.setCurrency(currency);
		return newTransaction;
	}

	/**
	 * This method to process a transaction of RTGS
	 * 
	 * @throws RTGSException
	 */
	public void transactionProcessing() throws RTGSException {
		String dataFile = "";
		try {
			dataFile = readFileRTGS();
		} catch (FileReaderUtilException e1) {
			throw new RTGSException(e1.getMessage(), e1);
		}
		Pattern p = Pattern.compile("\\{(.+?)\\}");
		Matcher m = p.matcher(dataFile);
		List<String> blocks = new ArrayList<String>();
		while (m.find()) {
			blocks.add(m.group(1));
		}
		if (blocks.size() != 4 && !validateSwiftFormat(blocks.get(0)) && !validateSwiftFormat(blocks.get(1))) {
			Log.getInstance().write("Format invalid", getClass(), LogLevels.ERROR);
			return;
		}
		Participant participantA, participantB;
		try {
			
			String codeSwiftA =blocks.get(0).split(":")[1];
			String codeSwiftB =blocks.get(1).split(":")[1];
			
			participantA = participantManger.findParticipantByCodeSwitf(codeSwiftA);
			participantB = participantManger.findParticipantByCodeSwitf(codeSwiftB);
		} catch (ParticipantException e) {
			Log.getInstance().write(e.getMessage(), getClass(), LogLevels.ERROR);
			return;
		}

		String block3[] = blocks.get(2).split(":");
		if (block3.length != 2) {
			Log.getInstance().write("Format block 3 invalid", getClass(), LogLevels.ERROR);
			return;
		}

		String currency = block3[1].substring(0, 3);
		BigDecimal amount = new BigDecimal(block3[1].substring(3));

		TransactionRtgs newTransaction = createTransaction(participantA.getSwitfCode(), participantB.getSwitfCode(),
				amount, currency);

		if (amount.compareTo(participantA.getSettlementAccounts().get(currency).getCurrentBalance()) < 0) {
			newTransaction.setStatus(TransactionStatus.COMPLETE);

			participantManger.balanceAccounts(participantA, participantB, currency, amount);

		} else {
			newTransaction.setStatus(TransactionStatus.PENDING);
		}
		transactions.add(newTransaction);
		log.write("Succesful transaction", getClass(), LogLevels.INFO);
	}

	/**
	 * This method to validate SWIFT code
	 * 
	 * @param codeSwift
	 * @return
	 */
	public boolean validateSwiftFormat(String codeSwift) {
		return (codeSwift.length() != 8);
	}

}
