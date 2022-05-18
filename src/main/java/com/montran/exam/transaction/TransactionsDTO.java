package com.montran.exam.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.montran.exam.persistence.Archivable;

@XmlRootElement
public class TransactionsDTO implements Archivable {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 2807589563520829143L;
	
	private List<BaseTransaction> transactions;
	
	public TransactionsDTO() {
		transactions = new ArrayList<BaseTransaction>();
	}
	
	public TransactionsDTO(List<BaseTransaction> transactions) {
		this.transactions = transactions;
	}

	// getters and setters
	
	public List<BaseTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BaseTransaction> transactions) {
		this.transactions = transactions;
	}
	
}
