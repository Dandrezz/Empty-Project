package com.montran.exam.notifaction;

/**
 * This interfaces shows the basic operations that must implemented in Notification
 * @author Diego Portero
 *
 */
public interface BaseNotification {

	/**
	 * This method will send a notification of an event
	 */
	public void sendNotification(String message);
	
}
