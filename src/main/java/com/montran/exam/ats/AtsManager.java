package com.montran.exam.ats;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.NotificationException;
import com.montran.exam.exceptions.ParserException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.rtgs.RtgsManager;

/**
 * This class manage all ATS system and represents a Manager of the API
 * 
 * @author Diego Portero
 *
 */
public class AtsManager {

	private RtgsManager rtgsManager;
	
	public AtsManager() throws LogException, ParserException, PersistenceException, NotificationException {
		rtgsManager = RtgsManager.getInstance();
	}
	
	public void executeRtgs() throws RTGSException, LogException {
		rtgsManager.transactionProcessing();
	}
	
}
