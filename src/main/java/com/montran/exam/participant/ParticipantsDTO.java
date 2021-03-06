package com.montran.exam.participant;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.montran.exam.persistence.Archivable;

/**
 * Class that helps to parse list of participants
 * 
 * @author Diego Portero
 *
 */
@XmlRootElement
public class ParticipantsDTO implements Archivable{
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -6451134298055758387L;
	/**
	 * List of participants
	 */
	private List<Participant> participants;
	
	public ParticipantsDTO() {
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
