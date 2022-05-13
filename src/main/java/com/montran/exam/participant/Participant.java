/**
 * 
 */
package com.montran.exam.participant;

import java.util.Map;

import com.montran.exam.account.SettlementAccount;

/**
 * This class has the necessary information to create a new participant
 * @author Diego Portero
 *
 */

public class Participant extends BaseParticipant {

	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = -6554769197944104852L;
	/**
	 * Map to store different types of settlement account
	 */
	private Map<String,SettlementAccount> settlementAccounts;

	// getters and setters
	public Map<String, SettlementAccount> getSettlementAccounts() {
		return settlementAccounts;
	}

	public void setSettlementAccounts(Map<String, SettlementAccount> settlementAccounts) {
		this.settlementAccounts = settlementAccounts;
	}
	
}
