package com.montran.exam.persistence.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.PropertiesReaderException;
import com.montran.exam.persistence.Archivable;
import com.montran.exam.persistence.PersistenceStrategy;
import com.montran.exam.utils.PropertiesUtils;

/**
 * Class to persist an open an object in XML
 * 
 * @author Diego Portero
 *
 */
public class JsonPersistence<T extends Archivable> implements PersistenceStrategy<T> {

	/**
	 * Path to the xml to persit and open
	 */
	private String path;
	/**
	 * Context admitted to be persisted
	 */
	@SuppressWarnings("rawtypes")
	private Class[] context;

	/**
	 * Key to manage the path
	 */
	private String key = "";

	/**
	 * Constructor to load the path and context from a properties file
	 * 
	 * @throws PersistenceException
	 */
	public JsonPersistence() throws PersistenceException {
		// change property for json persistence
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

		Properties props;
		try {
			props = PropertiesUtils.loadPropertiesFile("persistence.properties");
		} catch (PropertiesReaderException e) {
			throw new PersistenceException("properties is missing");
		}
		if (key.length() == 0)
			this.path = props.getProperty("persistence.json.path");
		else
			this.path = props.getProperty(key);
		String contextProperty = props.getProperty("persistence.json.context");
		String[] contextClassNames = contextProperty.split(",");
		context = new Class[contextClassNames.length];
		for (int i = 0; i < contextClassNames.length; i++) {
			try {
				context[i] = Class.forName(contextClassNames[i]);
			} catch (ClassNotFoundException e) {
				throw new PersistenceException("Context Class is wrong", e);
			}
		}
	}

	/**
	 * Method to persist an object in xml
	 */
	@Override
	public void save(T objectToBePersisted) throws PersistenceException {
		// TODO Auto-generated method stub
		JAXBContext context;
		Marshaller marshaller;
		try {
			context = JAXBContext.newInstance(this.context);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.setProperty("eclipselink.media-type", "application/json");
			marshaller.marshal(objectToBePersisted, new FileWriter(path));
		} catch (JAXBException jaxbException) {
			throw new PersistenceException("Object cant be converted to json", jaxbException);
		} catch (IOException ioException) {
			throw new PersistenceException("file cant be written to path " + this.path, ioException);
		}
	}

	/**
	 * Method to open a object persited in XML
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T load() throws PersistenceException {
		// TODO Auto-generated method stub
		JAXBContext context;
		Unmarshaller unmarshaller;
		try {
			context = JAXBContext.newInstance(this.context);
			unmarshaller = context.createUnmarshaller();
			unmarshaller.setProperty("eclipselink.media-type", "application/json");
			File fileToBeRead = new File(path);
			if (!fileToBeRead.exists()) {
				throw new FileNotFoundException(path + " does not exist");
			}
			T container = ((T) unmarshaller.unmarshal(new File(path)));
			return container;
		} catch (JAXBException jaxbException) {
			throw new PersistenceException("xml cant be loaded", jaxbException);
		} catch (FileNotFoundException fileNotFoundException) {
			// TODO Auto-generated catch block
			throw new PersistenceException("xml file doesnt exist", fileNotFoundException);
		}
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 * @throws PersistenceException
	 */
	public void setKey(String key) throws PersistenceException {
		this.key = key;
		Properties props;
		try {
			props = PropertiesUtils.loadPropertiesFile("persistence.properties");
		} catch (PropertiesReaderException e) {
			throw new PersistenceException("properties is missing");
		}
		this.path = props.getProperty(key);

	}

}
