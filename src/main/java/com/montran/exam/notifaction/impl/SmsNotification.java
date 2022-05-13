package com.montran.exam.notifaction.impl;

import com.montran.exam.notifaction.BaseNotification;

/**
 * This class is the implementation for strategy to notification via SMS
 * 
 * @author Diego Portero
 *
 */
public class SmsNotification implements BaseNotification{

	/**
	 * This method will send a notification via SMS
	 *
	 *@param message The information to be reported 
	 *
	 */
	@Override
	public void sendNotification(String message) {
		throw new UnsupportedOperationException("Will released in version 2");
	}

}
