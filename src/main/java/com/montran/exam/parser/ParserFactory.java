package com.montran.exam.parser;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.ParserException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.utils.PropertiesUtils;

public class ParserFactory {
	
	/**
	 * Map to create different types of parsers
	 */
	private Map<String, ParserStrategy> parsersCache = new ConcurrentHashMap<>();

	private Log log;
	private static ParserFactory instance;
	
	private ParserFactory() throws LogException {
		log = Log.getInstance();
	}
	
	public static ParserFactory getInstance() throws LogException {
		if (instance == null) {
			synchronized (ParserFactory.class) {
				if (instance == null) {
					instance = new ParserFactory();
				}
			}
		}
		return instance;
	}
	
	public ParserStrategy buildParserStrategy(String propertiesPath) throws ParserException {
		Properties parserProperties = null;
		try {
			parserProperties = PropertiesUtils.loadPropertiesFile(propertiesPath);
		} catch (PropertiesReaderException e) {
			log.write("failed with reading properties " + e.getMessage(),LogLevels.ERROR);
			throw new ParserException("failed with reading properties", e);
		}
		String key = parserProperties.getProperty("parser.name");
		if (key == null) {
			log.write("Parser key not found, using default",LogLevels.INFO);
			key = "com.montran.exam.parser.impl.ParserIso";
		}
		if(parsersCache.containsKey(key)) {
			synchronized (parsersCache) {
				if(parsersCache.containsKey(key)) {
					Class<?> clazz;
					try {
						clazz = Class.forName(key);
						ParserStrategy parser = (ParserStrategy) clazz.newInstance();
						parsersCache.put(key, parser);
						log.write("Parser context: " + clazz, LogLevels.INFO);
					} catch (ClassNotFoundException classNotFoundException) {
						log.write("property with classname doesnt exists:" + classNotFoundException.getMessage(),LogLevels.ERROR);
						throw new ParserException("property with classname doesnt exists", classNotFoundException);
					} catch (InstantiationException instantiationException) {
						log.write("Instantiation error:" + instantiationException.getMessage(), LogLevels.ERROR);
						throw new ParserException("Instantiation error", instantiationException);
					} catch (IllegalAccessException illegalAccessException) {
						log.write("illegal acces error:" + illegalAccessException.getMessage(), LogLevels.ERROR);
						throw new ParserException("illegal acces error", illegalAccessException);
					}
				}
			}
		}
		return parsersCache.get(key);
	}
	
}
