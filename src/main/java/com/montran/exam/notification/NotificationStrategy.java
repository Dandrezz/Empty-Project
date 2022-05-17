package com.montran.exam.notification;

/**
 * This interfaces shows the basic operations that must implemented in Notification
 * @author Diego Portero
 *
 */
public interface NotificationStrategy {

	/**
	 * This method will send a notification of an event
	 */
	public void sendNotification(String message);
	
}
