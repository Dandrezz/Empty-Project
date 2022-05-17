package com.montran.exam.transaction.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.montran.exam.account.manager.impl.AccountManager;
import com.montran.exam.transaction.BaseTransaction;
import com.montran.exam.transaction.impl.TransacionAch;
import com.montran.exam.transaction.impl.TransactionRtgs;
import com.montran.exam.utils.UUIDGenerator;

/**
 * This class manage Transaction class
 * 
 * @author Diego Portero
 *
 */
public class TransactionManager {
	
	private List<BaseTransaction> transactions;
	
	private static TransactionManager instance;
	
	/**
	 * Get the instance of this class
	 * This method is Thread Safety
	 * @return the unique instance of the class
	 */
	public static TransactionManager getInstance() {
		if (instance == null) {
			synchronized (TransactionManager.class) {
				if (instance == null) {
					instance = new TransactionManager();
				}
			}
		}
		return instance;
	}
	
	private TransactionManager() {
		transactions = new ArrayList<BaseTransaction>();
	}

	/**
	 * @param swiftCodeSenderParticipant
	 * @param swiftCodeReceiverParticipant
	 * @param amount
	 * @param currency
	 * @return
	 */
	public TransactionRtgs createTransactionRTGS(String swiftCodeSenderParticipant, String swiftCodeReceiverParticipant,
			BigDecimal amount, String currency) {
		TransactionRtgs newTransaction = new TransactionRtgs();
		newTransaction.setIdTransaction(UUIDGenerator.getInstance().getUuid32bits());
		newTransaction.setSwiftCodeSenderParticipant(swiftCodeSenderParticipant);
		newTransaction.setSwiftCodeReceiverParticipant(swiftCodeReceiverParticipant);
		newTransaction.setAmount(amount);
		newTransaction.setCurrency(currency);
		transactions.add(newTransaction);
		return newTransaction;
	}

	/**
	 * @param swiftCodeSenderParticipant
	 * @param swiftCodeReceiverParticipant
	 * @param amount
	 * @param currency
	 * @return
	 */
	public TransacionAch createTransactionACH(String swiftCodeSenderParticipant, String swiftCodeReceiverParticipant,
			BigDecimal amount, String currency) {
		TransacionAch newTransaction = new TransacionAch();
		newTransaction.setIdTransaction(UUIDGenerator.getInstance().getUuid32bits());
		newTransaction.setSwiftCodeSenderParticipant(swiftCodeSenderParticipant);
		newTransaction.setSwiftCodeReceiverParticipant(swiftCodeReceiverParticipant);
		newTransaction.setAmount(amount);
		newTransaction.setCurrency(currency);
		transactions.add(newTransaction);
		return newTransaction;
	}

}
