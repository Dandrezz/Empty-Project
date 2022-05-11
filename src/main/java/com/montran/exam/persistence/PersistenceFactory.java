package com.montran.exam.persistence;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.utils.PropertiesUtils;

/**
 * Factory that constructs singletons
 * 
 * @author Diego Portero
 *
 */
public class PersistenceFactory {
	/**
	 * Map to create different types of persistence
	 */
	private Map<String, PersistenceStrategy<Archivable>> persistencesCache = new ConcurrentHashMap<>();

	/**
	 * Private constructor of singleton
	 */
	private PersistenceFactory() { 
	}

	/**
	 * Instance of singleton
	 */
	private static PersistenceFactory instance;

	/**
	 * Method to get a instance of singleton
	 * @return singleton instance
	 */
	public static PersistenceFactory getInstance() {
		if (instance == null) {
			synchronized (PersistenceFactory.class) {
				if (instance == null) {
					instance = new PersistenceFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Method to create a single instance of persistence type
	 * @return instance of persistence type
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public PersistenceStrategy<Archivable> buildPersistenceStrategy() throws PersistenceException { 
		Properties persistenceProperties = null; 
		try {  
			persistenceProperties = PropertiesUtils.loadPropertiesFile("persistence.properties");
		} catch (PropertiesReaderException e1) {
			throw new PersistenceException("failed with reading properties", e1);
		}
		String key = persistenceProperties.getProperty("persistence.provider");
		//if no persistence is found in properties, uses xml persistence
		if (key == null) {
			System.out.println("Provider not found");
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
					} catch (ClassNotFoundException classNotFoundException) {
						throw new PersistenceException("property with classname doesnt exists", classNotFoundException);
					} catch (InstantiationException instantiationException) {
						throw new PersistenceException("Instantiation error", instantiationException);
					} catch (IllegalAccessException illegalAccessException) {
						throw new PersistenceException("illegal acces error", illegalAccessException);
					}
				}
			}
		}
		return persistencesCache.get(key);
	}
}
