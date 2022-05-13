package com.montran.exam.account.manager.impl;

import java.math.BigDecimal;

import com.montran.exam.account.SettlementAccount;
import com.montran.exam.utils.UUIDGenerator;

/**
 * This class is used to manage objects of type Account
 * @author Diego Portero
 *
 */
public class AccountManager{
	
	/**
	 * Instance of this class
	 */
	private static AccountManager instance;
	
	/**
	 * Constructor without parameters
	 */
	private AccountManager() {
		
	}
	
	/**
	 * Get the instance of this class
	 * This method is Thread Safety
	 * @return the unique instance of the class
	 */
	public static AccountManager getInstance() {
		if (instance == null) {
			synchronized (AccountManager.class) {
				if (instance == null) {
					instance = new AccountManager();
				}
			}
		}
		return instance;
	}

	/**
	 * This method create a new settlement account
	 * 
	 * @param ownerAcount account owner identifier
	 * @param currency type currency
	 * @param initialBalance Initial balance with which the account is created
	 * @param currentBalance Current balance
	 * @return a new instantiated object of type SettlementAccount
	 */
	public SettlementAccount createSettlementAccount(int ownerAcount, String currency, BigDecimal initialBalance,
			BigDecimal currentBalance) {
		SettlementAccount newSettlementAccount = new SettlementAccount();
		newSettlementAccount.setIdAccount(UUIDGenerator.getInstance().getUuid32bits());
		newSettlementAccount.setOwnerAcount(ownerAcount);
		newSettlementAccount.setCurrency(currency);
		newSettlementAccount.setInitialBalance(initialBalance);
		newSettlementAccount.setCurrentBalance(currentBalance);
		return newSettlementAccount;
	}

}
