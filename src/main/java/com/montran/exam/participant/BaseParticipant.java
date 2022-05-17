/**
 * 
 */
package com.montran.exam.participant;

import java.util.Objects;

import com.montran.exam.persistence.Archivable;
import com.montran.exam.status.ParticipantStatus;

/**
 * This class is an abstract class that contains the common values that a
 * participant will have in the system.
 * 
 * @author Diego Portero
 *
 */
public abstract class BaseParticipant implements Archivable {
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 6202411673609238598L;

	/**
	 * Participant identifier
	 */
	private int idParticipant;

	/**
	 * Complete name of the participant
	 */
	private String fullNameParticipant;
	/**
	 * Short name representation of the participant
	 */
	private String shortNameParticipant;
	/**
	 * Contact email address
	 */
	private String emailParticipant;
	/**
	 * Contact cellphone number
	 */
	private String cellPhoneNumber;
	/**
	 * SWIFT code
	 */
	private String switfCode;

	/**
	 * Current participant status
	 */
	private ParticipantStatus status;

	// getters and setters
	public int getIdParticipant() {
		return idParticipant;
	}

	public void setIdParticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}

	public String getFullNameParticipant() {
		return fullNameParticipant;
	}

	public void setFullNameParticipant(String fullNameParticipant) {
		this.fullNameParticipant = fullNameParticipant;
	}

	public String getShortNameParticipant() {
		return shortNameParticipant;
	}

	public void setShortNameParticipant(String shortNameParticipant) {
		this.shortNameParticipant = shortNameParticipant;
	}

	public String getEmailParticipant() {
		return emailParticipant;
	}

	public void setEmailParticipant(String emailParticipant) {
		this.emailParticipant = emailParticipant;
	}

	public String getSwitfCode() {
		return switfCode;
	}

	public void setSwitfCode(String switfCode) {
		this.switfCode = switfCode;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public ParticipantStatus getStatus() {
		return status;
	}

	public void setStatus(ParticipantStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BaseParticipant [idParticipant=" + idParticipant + ", fullNameParticipant=" + fullNameParticipant
				+ ", shortNameParticipant=" + shortNameParticipant + ", emailParticipant=" + emailParticipant
				+ ", cellPhoneNumber=" + cellPhoneNumber + ", switfCode=" + switfCode + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idParticipant, switfCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseParticipant other = (BaseParticipant) obj;
		return idParticipant == other.idParticipant && Objects.equals(switfCode, other.switfCode);
	}

}
