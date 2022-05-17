package com.montran.exam.notification;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.NotificationException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.utils.PropertiesUtils;

public class NotificationFactory {
	private Map<String, NotificationStrategy> notificationsCache = new ConcurrentHashMap<>();
	private Log log;
	private static NotificationFactory instance;

	private NotificationFactory() throws LogException {
		log = Log.getInstance();
	}

	public static NotificationFactory getInstance() throws LogException {
		if (instance == null) {
			synchronized (NotificationFactory.class) {
				if (instance == null) {
					instance = new NotificationFactory();
				}
			}
		}
		return instance;
	}
	
	public NotificationStrategy buildNotificationStrategy() throws NotificationException {
		Properties notificationProperties = null;
		try {
			notificationProperties = PropertiesUtils.loadPropertiesFile("notifications.properties");
		} catch (PropertiesReaderException e) {
			log.write("failed with reading properties " + e.getMessage(),LogLevels.ERROR);
			throw new NotificationException("Failed with reading properties", e);
		}
		String key = notificationProperties.getProperty("notification.channel");
		if (key == null) {
			log.write("Notification provider not found, using default",LogLevels.ERROR);
			key = "com.montran.exam.notification.impl.EmailNotification";
		}
		if(notificationsCache.containsKey(key)) {
			synchronized (notificationsCache) {
				if(notificationsCache.containsKey(key)) {
					Class<?> clazz;
					try {
						clazz = Class.forName(key);
						NotificationStrategy notification =  (NotificationStrategy) clazz.newInstance();
						notificationsCache.put(key, notification);
						log.write("Parser context: " + clazz, LogLevels.INFO);
					} catch (ClassNotFoundException classNotFoundException) {
						log.write("property with classname doesnt exists:" + classNotFoundException.getMessage(),LogLevels.ERROR);
						throw new NotificationException("property with classname doesnt exists", classNotFoundException);
					} catch (InstantiationException instantiationException) {
						log.write("Instantiation error:" + instantiationException.getMessage(), LogLevels.ERROR);
						throw new NotificationException("Instantiation error", instantiationException);
					} catch (IllegalAccessException illegalAccessException) {
						log.write("illegal acces error:" + illegalAccessException.getMessage(), LogLevels.ERROR);
						throw new NotificationException("illegal acces error", illegalAccessException);
					}
				}
			}
		}
		return notificationsCache.get(key);
	}
	
}
