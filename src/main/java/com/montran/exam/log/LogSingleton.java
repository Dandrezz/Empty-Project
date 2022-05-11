package com.montran.exam.log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.utils.PropertiesUtils;

/**
 * Singleton class to log the activity of the app.
 * 
 * @author Diego Portero
 *
 */
public class LogSingleton {
	/**
	 * path of the log file.
	 */
	private String logFilePath;
	/**
	 * Variable to write the log file.
	 */
	private PrintWriter writer;

	/**
	 * Variable to manage the date format.
	 */
	private String dateFormat;

	/**
	 * Instance of singleton
	 */
	private static LogSingleton logger = null;

	/**
	 * Private constructor of singleton, initialized all properties with a
	 * properties file
	 * 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws LogException
	 */
	private LogSingleton() throws FileNotFoundException, UnsupportedEncodingException, LogException {
		Properties props;
		try {
			props = PropertiesUtils.loadPropertiesFile("log.properties");
		} catch (PropertiesReaderException e) {
			throw new LogException("properties is missing");
		}
		this.logFilePath = props.getProperty("log.path");
		this.dateFormat = props.getProperty("log.date.format");
		PrintWriter fw = new PrintWriter(logFilePath, "UTF-8");
		writer = new PrintWriter(fw, true);
	}

	/**
	 * Method to get a single instance of the class. The method has a synchronized
	 * block to return only one instance of the logger.
	 * 
	 * @return instance of LogSingleton
	 * @throws LogException
	 */
	public static LogSingleton getInstance() throws LogException {
		if (logger == null)
			synchronized (LogSingleton.class) {
				if (logger == null)
					try {
						logger = new LogSingleton();
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						System.err.println("Error creating singleton LogSingleton");
					} catch (LogException logException) {
						throw new LogException("properties is missing", logException);
					}
			}
		return logger;
	}

	/**
	 * Private method to return actual date and hour in a format
	 * 
	 * @return date in String format
	 */
	private String getDateTimeNow() {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	/**
	 * Method to log a message with actual date and hour
	 * 
	 * @param level
	 * @param message
	 */
	public void log(LogLevel level, String message) {
		writer.println(getDateTimeNow() + "|" + level.name() + "|" + message);
	}
}
