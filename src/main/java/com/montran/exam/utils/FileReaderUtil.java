package com.montran.exam.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.montran.exam.exceptions.FileReaderUtilException;
import com.montran.exam.exceptions.LogException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;

/**
 * This class contains the necessary functionalities to read files
 * 
 * @author Diego Portero
 *
 */
public class FileReaderUtil {

	private static FileReaderUtil instance;
	
	private Log log;
	
	private FileReaderUtil() throws LogException {
		log = Log.getInstance();
	}
	
	public synchronized String readFile(String path) throws FileReaderUtilException {
		String data = null;
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    data = sb.toString();
		} catch (FileNotFoundException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new FileReaderUtilException(e.getMessage(),e);
		} catch (IOException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new FileReaderUtilException(e.getMessage(),e);
		}
		return data;
	}
	
	/**
	 * Get the instance of this class
	 * This method is Thread Safety
	 * @return the unique instance of the class
	 * @throws LogException 
	 */
	public static FileReaderUtil getInstance() throws LogException {
		if (instance == null) {
			synchronized (FileReaderUtil.class) {
				if (instance == null) {
					instance = new FileReaderUtil();
				}
			}
		}
		return instance;
	}
	
}
