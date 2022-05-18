package com.montran.exam.persistence;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.ParserException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.utils.PropertiesUtils;

public class PersistenceFactory {
	
	private Map<String, PersistenceStrategy<Archivable>> persistencesCache = new ConcurrentHashMap<>();

	private Log log;

	private PersistenceFactory() throws LogException {
		log = Log.getInstance();
	}
	
	private static PersistenceFactory instance;
	
	/**
	 * Get the instance of this class
	 * This method is Thread Safety
	 * @return the unique instance of the class
	 * @throws LogException 
	 */
	public static PersistenceFactory getInstance() throws LogException {
		if (instance == null) {
			synchronized (PersistenceFactory.class) {
				if (instance == null) {
					instance = new PersistenceFactory();
				}
			}
		}
		return instance;
	}
	
	public PersistenceStrategy<Archivable> buildPersistenceStrategy() throws PersistenceException, LogException {
		Properties persistenceProperties = null;
		try {
			persistenceProperties = PropertiesUtils.loadPropertiesFile("persistence.properties");
		} catch (PropertiesReaderException e) {
			log.write("Failed with reading properties " + e.getMessage(),LogLevels.ERROR);
			throw new PersistenceException("failed with reading properties", e);
		}
		String key = persistenceProperties.getProperty("persistence.provider");
		if (key == null) {
			log.write("Peristence provider not found, using default",LogLevels.ERROR);
			key = "com.montran.exam.persistence.impl.XmlPersistence";
		}
		PersistenceStrategy<Archivable> persistence = persistencesCache.get(key);
		if (persistence == null) {
			synchronized (persistencesCache) {
				if (persistencesCache.get(key) == null) {
					Class<?> clazz;
					try {
						clazz = Class.forName(key);
						persistence = (PersistenceStrategy<Archivable>) clazz.newInstance();
						persistencesCache.put(key, persistence);
						log.write("Perist context: " + clazz, LogLevels.INFO);
					} catch (ClassNotFoundException classNotFoundException) {
						log.write("Property with classname doesnt exists:" + classNotFoundException.getMessage(),LogLevels.ERROR);
						throw new PersistenceException("property with classname doesnt exists", classNotFoundException);
					} catch (InstantiationException instantiationException) {
						log.write("Instantiation error:" + instantiationException.getMessage(), LogLevels.ERROR);
						throw new PersistenceException("Instantiation error", instantiationException);
					} catch (IllegalAccessException illegalAccessException) {
						log.write("Illegal acces error:" + illegalAccessException.getMessage(), LogLevels.ERROR);
						throw new PersistenceException("illegal acces error", illegalAccessException);
					}
				}
			}
		}
		return persistencesCache.get(key);
	}
	
}
