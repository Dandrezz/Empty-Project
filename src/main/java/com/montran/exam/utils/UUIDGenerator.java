package com.montran.exam.utils;

import java.util.UUID;

/**
 * This class will generate unique identifiers
 * @author Diego Portero
 *
 */
public class UUIDGenerator {
	/**
	 * The instance of this class.
	 */
	private static UUIDGenerator instance = new UUIDGenerator();
	/**
	 * Private constructor to avoid instantiation by other classes
	 */
	private UUIDGenerator() {
		
	}
	/**
	 * This method creates a unique 32-bit identifier
	 * @return A value cast to long
	 */
	public int getUuid32bits() {
		return (int) (UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE);
	}

	/**
	 * Get the instance of this class
	 * This method is Thread Safety
	 * @return the unique instance of the class
	 */
	public static UUIDGenerator getInstance() {
		if (instance == null) {
			synchronized (UUIDGenerator.class) {
				if (instance == null) {
					instance = new UUIDGenerator();
				}
			}
		}
		return instance;
	}
}
