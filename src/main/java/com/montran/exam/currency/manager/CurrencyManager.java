package com.montran.exam.currency.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.montran.exam.currency.Currency;
import com.montran.exam.exceptions.CurrencyException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;

/**
 * This class is used to manage objects of type Currency
 * @author Diego Portero
 *
 */
public class CurrencyManager {
	
	private Log log = Log.getInstance();
	/**
	 * Instance of this class
	 */
	private static volatile CurrencyManager instance;
	/**
	 * Map to store different types of currencies
	 */
	private Map<String,Currency> currencies;

	/**
	 * Constructor without parameters
	 * Currency data is loaded
	 */
	private CurrencyManager() throws CurrencyException {
		currencies = new HashMap<String,Currency>();
		File file = new File("currency-config.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			NodeList nListCode = document.getElementsByTagName("code");
			NodeList nListFractions = document.getElementsByTagName("fractionDigits");
			for (int i = 0; i < nListCode.getLength(); i++) {
				Currency auxCurrent = new Currency();
				auxCurrent.setCode(nListCode.item(i).getTextContent());
				auxCurrent.setFractionDigits(Integer.valueOf(nListFractions.item(i).getTextContent()));
				currencies.put(auxCurrent.getCode(), auxCurrent);
			}
		} catch (ParserConfigurationException e) {
			log.write(e.getMessage(), getClass(), LogLevels.ERROR);
			throw new CurrencyException(e.getMessage(),e);
		} catch (SAXException e) {
			log.write(e.getMessage(), getClass(), LogLevels.ERROR);
			throw new CurrencyException(e.getMessage(),e);
		} catch (IOException e) {
			log.write(e.getMessage(), getClass(), LogLevels.ERROR);
			throw new CurrencyException(e.getMessage(),e);
		}
	}

	/**
	 * This method to get data about currencies
	 * @return a map containing the currency data
	 */
	public Map<String,Currency> getInfoCurrencies() {
		return currencies;
	}

	/**
	 * This method to get a instance of singleton
	 * @return a single instance of the class
	 * @throws CurrencyException
	 */
	public static CurrencyManager getInstance() throws CurrencyException {
		if (instance == null) {
			synchronized (CurrencyManager.class) {
				if (instance == null) {
					instance = new CurrencyManager();
				}
			}
		}
		return instance;
	}

}
