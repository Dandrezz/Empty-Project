package com.montran.exam.persistence;

import com.montran.exam.exceptions.PersistenceException;

/**
 * Strategy to create different types of persitent
 * 
 * @author Diego Portero
 *
 */
public interface PersistenceStrategy<T extends Archivable> {
	/**
	 * Method to persist an objetc
	 * @param objectToBePersisted
	 */
	public void save(T objectToBePersisted, String fileName) throws PersistenceException;

	/**
	 * Method to open a persisted object 
	 * @return object
	 */
	public T load(String fileName) throws PersistenceException;

}
