package com.montran.exam.demo;

import java.math.BigDecimal;

import com.montran.exam.account.Account;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.persistence.PersistenceStrategy;
import com.montran.exam.persistence.impl.JsonPersistence;
import com.montran.exam.persistence.impl.XmlPersistence;
import com.montran.exam.status.Status;

/**
 * @author Diego Portero
 *
 */

public class Demo {

	public static void main(String[] args) {
		
		Log log = Log.getInstance();
		
		log.write("Account cannot be deleted", Demo.class, LogLevels.ERROR);
		System.out.println("Termino ejecucion");
		
//		try {
//			Account accountTest = new Account();
//			accountTest.setIdAccount("test");
//			accountTest.setNameAccount("testAccount");
//			accountTest.setOwnerAccount("testOwner");
//			accountTest.setInitialBalance(BigDecimal.ONE);
//			accountTest.setCurrentBalance(BigDecimal.TEN);
//			accountTest.setStatus(Status.ACTIVE);
//			PersistenceStrategy<Account> xml = new JsonPersistence<Account>();
//			xml.save(accountTest);
//			
////			Account accountSecond = xml.load();
////			System.out.println(accountSecond);
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}
		

	}

}
