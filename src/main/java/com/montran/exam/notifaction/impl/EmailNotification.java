package com.montran.exam.notifaction.impl;

import com.montran.exam.notifaction.BaseNotification;

/**
 * This class is the implementation for strategy to notification via email
 * 
 * @author Diego Portero
 *
 */
public class EmailNotification implements BaseNotification {

	/**
	 *This method will send a notification via email
	 *
	 *@param message The information to be reported 
	 *
	 */
	@Override
	public void sendNotification(String message) {
		throw new UnsupportedOperationException("Will released in version 2");
	}

}
