package com.montran.exam.parser.formats;

import java.math.BigDecimal;

/**
 * Format with the information of an RTGS transaction
 * 
 * @author Diego Portero
 *
 */
public class FormatRtgs extends Format {

	/**
	 * Receiver participant identifier
	 */
	private String receiverCode;
	/**
	 * Transaction amount
	 */
	private BigDecimal amount;
	
	/**
	 * Unique reference
	 */
	private String uniqueRef;

	// getters and setters

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUniqueRef() {
		return uniqueRef;
	}

	public void setUniqueRef(String uniqueRef) {
		this.uniqueRef = uniqueRef;
	}

}
