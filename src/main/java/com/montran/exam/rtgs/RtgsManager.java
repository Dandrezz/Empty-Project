package com.montran.exam.rtgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.NotificationException;
import com.montran.exam.exceptions.ParserException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.notification.NotificationFactory;
import com.montran.exam.notification.NotificationStrategy;
import com.montran.exam.parser.ParserFactory;
import com.montran.exam.parser.ParserStrategy;
import com.montran.exam.parser.formats.FormatRtgs;
import com.montran.exam.participant.Participant;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.persistence.Archivable;
import com.montran.exam.persistence.PersistenceStrategy;
import com.montran.exam.persistence.impl.XmlPersistence;
import com.montran.exam.transaction.impl.TransactionRtgs;
import com.montran.exam.transaction.manager.TransactionManager;
import com.montran.exam.utils.PropertiesUtils;

/**
 * This class is used to manage RTGS transaction logic
 * 
 * @author Diego Portero
 *
 */
public class RtgsManager {

	private Properties rtgsProperties;

	private Log log;

	private ParserStrategy<FormatRtgs> parser;

	private TransactionManager transactionManager;

	private PersistenceStrategy<Archivable> persist;

	private NotificationStrategy notification;

	private ParticipantManger participantManger;
	/**
	 * Record of transactions
	 */
	private List<TransactionRtgs> transactions;

	private static RtgsManager instance;

	private RtgsManager() throws LogException, PersistenceException, ParserException, NotificationException {
		transactions = new ArrayList<TransactionRtgs>();
		log = Log.getInstance();
		participantManger = ParticipantManger.getInstance();
		transactionManager = TransactionManager.getInstance();
		persist = new XmlPersistence<Archivable>();
		parser = ParserFactory.getInstance().buildParserStrategy("rtgs.properties");
		notification = NotificationFactory.getInstance().buildNotificationStrategy();
	}

	/**
	 * Get the instance of this class
	 * 
	 * @return the unique instance of the class
	 * @throws LogException
	 * @throws PersistenceException
	 * @throws ParserException
	 * @throws NotificationException
	 */
	public static RtgsManager getInstance()
			throws LogException, ParserException, PersistenceException, NotificationException {
		if (instance == null) {
			synchronized (RtgsManager.class) {
				if (instance == null) {
					instance = new RtgsManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 */
	public void executeTransactions() {
		parser.loadFile();
		List<FormatRtgs> listsFormat = parser.processFile();
		for (FormatRtgs formatRtgs : listsFormat) {
			TransactionRtgs newTransactionRtgs = transactionManager.createTransactionRTGS(formatRtgs.getSenderCode(),
					formatRtgs.getReceiverCode(), formatRtgs.getAmount(), formatRtgs.getCurrency());
			try {
				transactionProcessing(newTransactionRtgs);
				transactions.add(newTransactionRtgs);
			} catch (RTGSException e) {
				log.write(e.getMessage(), LogLevels.ERROR);
			} catch (LogException e) {
				log.write(e.getMessage(), LogLevels.ERROR);
			} catch (ParticipantException e) {
				log.write(e.getMessage(), LogLevels.ERROR);
			}
		}
	}

	/**
	 * This method to process a transaction of RTGS
	 * 
	 * @throws RTGSException
	 * @throws LogException
	 * @throws ParticipantException
	 */
	public void transactionProcessing(TransactionRtgs transactionRtgs)
			throws RTGSException, LogException, ParticipantException {

		participantManger.balanceAccounts(transactionRtgs.getSwiftCodeSenderParticipant(),
				transactionRtgs.getSwiftCodeReceiverParticipant(), transactionRtgs.getCurrency(),
				transactionRtgs.getAmount());

		log.write("Succesful transaction " + transactionRtgs.getIdTransaction(), LogLevels.INFO);
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
