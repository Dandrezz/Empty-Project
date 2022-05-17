package com.montran.exam.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import com.montran.exam.exceptions.LogException;


/**
 * This class will fill a log file with relevant information. This class will be a singleton class
 * @author Diego Portero
 *
 */
public class Log {
	
	/**
	 * Instance of this class
	 */
	protected static Log instance = null;
	/**
	 * The outputstream that will save a file
	 */
	FileOutputStream fileOutputStream;
	/**
	 * Writer that sends the data to file for saving
	 */
	private Writer writer;
	
	/**
	 * Singleton constructor that creates a file named from a properties file
	 * @throws LogException 
	 */
	private Log() throws LogException {
		try {
			fileOutputStream = new FileOutputStream("ats.log",true);			
			writer = new OutputStreamWriter(fileOutputStream);
		} catch (FileNotFoundException e) {
			throw new LogException(e.getMessage(),e);
		}
	}
	
	/**
	 * Write a message to the existing file
	 * @param message Message that will be written in the file
	 */
	public void write(String message, LogLevels logLevel) {
		try {
			writer.append(LocalDateTime.now().toString().replace("T", "|") + "|" + logLevel.toString() + "|"  + message+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get the instance of this class
	 * @return the unique instance of the class
	 * @throws LogException 
	 */
	public static Log getInstance() throws LogException {
		if(instance ==null) {
			synchronized(Log.class) {
				if(instance ==null) {
					instance = new Log();
				}
			}
		}
		return instance;
	}
}
