package com.montran.exam.participant;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.montran.exam.persistence.Archivable;

/**
 * Class
 * 
 * @author Diego Portero
 *
 */
@XmlRootElement
public class ParticipantDTO implements Archivable{
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -6451134298055758387L;
	/**
	 * List of participants
	 */
	private List<Participant> participants;
	
	public ParticipantDTO() {
		participants = new ArrayList<Participant>();
	}

	// getters and setters
	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
	
	
}
