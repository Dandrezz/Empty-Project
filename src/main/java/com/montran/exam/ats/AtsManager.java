package com.montran.exam.ats;

import com.montran.exam.ach.AchManager;
import com.montran.exam.exceptions.CurrencyException;
import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.NotificationException;
import com.montran.exam.exceptions.ParserException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.rtgs.RtgsManager;

/**
 * This class manage all ATS system and represents a Manager of the API
 * 
 * @author Diego Portero
 *
 */
public class AtsManager {

	private RtgsManager rtgsManager;
	private AchManager achManager;
	private ParticipantManger participantManger;

	private static AtsManager instance;

	/**
	 * Get the instance of this class
	 * 
	 * @return the unique instance of the class
	 * @throws LogException
	 * @throws PersistenceException
	 * @throws NotificationException
	 * @throws ParserException
	 */

	public AtsManager() throws LogException, ParserException, PersistenceException, NotificationException {
		rtgsManager = RtgsManager.getInstance();
		achManager = AchManager.getInstance();
		participantManger = ParticipantManger.getInstance();
	}

	public static AtsManager getInstance()
			throws LogException, ParserException, NotificationException, PersistenceException {
		if (instance == null) {
			synchronized (AtsManager.class) {
				if (instance == null) {
					instance = new AtsManager();
				}
			}
		}
		return instance;
	}

	public void executeRtgs() throws RTGSException, LogException {
		rtgsManager.executeTransactions();
	}

	public void executeAch() throws RTGSException, LogException {
		achManager.executeTransactions();
	}

	public void createParticipant(String fullNameParticipant, String shortNameParticipant, String emailParticipant,
			String cellPhoneNumber, String switfCode) throws LogException, CurrencyException {
		participantManger.createParticipant(fullNameParticipant, shortNameParticipant, emailParticipant,
				cellPhoneNumber, switfCode);
	}

}
