package com.montran.exam.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is an abstract class that contains the common values that an account will have in the system.
 * @author Diego Portero
 *
 */
public abstract class BasicTransaction {

	/**
	 * Transaction identifier
	 */
	private int idTransaction;
	/**
	 * Sender participant identifier
	 */
	private String swiftCodeSender;
	/**
	 * Receiver participant identifier
	 */
	private String swiftCodeReceiver;
	/**
	 * Transaction amount  
	 */
	private BigDecimal amount;
	/**
	 * Type currency
	 */
	private String currency;
	/**
	 * Timestamp when the transaction was received by the system
	 */
	private LocalDate received;
	
	// getters and setters

	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public String getSwiftCodeSenderParticipant() {
		return swiftCodeSender;
	}
	public void setSwiftCodeSenderParticipant(String swiftCodeSenderParticipant) {
		this.swiftCodeSender = swiftCodeSenderParticipant;
	}
	public String getSwiftCodeReceiverParticipant() {
		return swiftCodeReceiver;
	}
	public void setSwiftCodeReceiverParticipant(String swiftCodeReceiverParticipant) {
		this.swiftCodeReceiver = swiftCodeReceiverParticipant;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public LocalDate getReceived() {
		return received;
	}
	public void setReceived(LocalDate received) {
		this.received = received;
	}
	@Override
	public String toString() {
		return "BasicTransaction [idTransaction=" + idTransaction + ", swiftCodeSenderParticipant="
				+ swiftCodeSender + ", swiftCodeReceiverParticipant=" + swiftCodeReceiver
				+ ", amount=" + amount + ", currency=" + currency + ", received=" + received + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idTransaction);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicTransaction other = (BasicTransaction) obj;
		return idTransaction == other.idTransaction;
	}
	
}
