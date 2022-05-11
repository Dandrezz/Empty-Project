package com.montran.exam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.montran.exam.exceptions.PropertiesReaderException;

/**
 * Class to open a properties file.
 * 
 * @author Diego Portero
 *
 */
public class PropertiesUtils {
	/**
	 * Method to open a properties file
	 * @param filename
	 * @return Properties file ready to use
	 * @throws PropertiesReaderException
	 */
	public static Properties loadPropertiesFile(String filename) throws PropertiesReaderException {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		if (input == null) {
			throw new PropertiesReaderException("file not found: " + filename);
		}
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new PropertiesReaderException("file not found: " + filename);
		}
		return prop;
	}
}
