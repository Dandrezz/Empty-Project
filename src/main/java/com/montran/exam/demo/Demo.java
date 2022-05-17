package com.montran.exam.demo;

import java.math.BigDecimal;

import com.montran.exam.ach.AchManager;
import com.montran.exam.exceptions.AchException;
import com.montran.exam.exceptions.CurrencyException;
import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.parser.ParserFactory;
import com.montran.exam.participant.ParticipantDTO;
import com.montran.exam.participant.manager.ParticipantManger;
import com.montran.exam.persistence.impl.XmlPersistence;
import com.montran.exam.rtgs.RtgsManager;

/**
 * @author Diego Portero
 *
 */

public class Demo {

	public static void main(String[] args) {
		
		
		
//		Log log = null;
//		try {
//			log = Log.getInstance();
//		} catch (LogException e1) {
//			System.err.println("Error creating log instace");
//		}
//		
//		
//		try {
//			ParticipantManger.getInstance().createParticipant("Banco Pichicha", "Pichin", "diego@pichincha.fin.ec", "0999261556", "PICHECEQ");
//			ParticipantManger.getInstance().createParticipant("Banco de Guayaquil", "Guay", "daniel@guayaquil.fin.ec", "0999261557", "GUAYECEG");
//			ParticipantManger.getInstance().createParticipant("Bancho Produbanco", "Produ", "paul@produbanco.fin.ec", "0999261558", "PRODECEQ");
//		} catch (LogException e1) {
//			log.write("Failed set the balance", LogLevels.ERROR);
//		} catch (CurrencyException e1) {
//			log.write("Failed set the balance", LogLevels.ERROR);
//		}
//		
//		
//		//String codeSwift, String currency, BigDecimal initialBalance,BigDecimal currentBalance
//		try {
//			ParticipantManger.getInstance().setBalancetSettlementAccount("PICHECEQ", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(4500));
//			ParticipantManger.getInstance().setBalancetSettlementAccount("GUAYECEG", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(2500));
//			ParticipantManger.getInstance().setBalancetSettlementAccount("PRODECEQ", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(500));
//		} catch (ParticipantException e2) {
//			log.write("Failed set the balance", LogLevels.ERROR);
//		} catch (LogException e) {
//			log.write("Failed set the balance", LogLevels.ERROR);
//		}
//		
//		try {
//			//It will read and perform transactions with the rtgs
//			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
//			RtgsManager.getInstance().transactionProcessing();
//			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
//		} catch (RTGSException e) {
//			log.write("Failed to execute RTGS", LogLevels.ERROR);
//		} catch (ParticipantException e) {
//			log.write("Failed to execute RTGS", LogLevels.ERROR);
//		} catch (LogException e) {
//			log.write("Failed to execute RTGS", LogLevels.ERROR);
//		}
//		
//		try {
//			ParticipantManger.getInstance().saveParticipant(new XmlPersistence<ParticipantDTO>());
//		} catch (PersistenceException e) {
//			log.write("Persist error", LogLevels.ERROR);
//		} catch (ParticipantException e) {
//			log.write("Persist error", LogLevels.ERROR);
//		} catch (LogException e) {
//			log.write("Log error", LogLevels.ERROR);
//		}
//		System.out.println("--------");
//		//It will read and perform transactions with the ach_file files
//		try {
//			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
//			AchManager.getInstance().transactionProcessing();
//			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
//		} catch (AchException e) {
//			log.write("Failed to execute ACH", LogLevels.ERROR);
//		} catch (ParticipantException e) {
//			log.write("Failed to execute ACH", LogLevels.ERROR);
//		} catch (LogException e) {
//			log.write("Log error", LogLevels.ERROR);
//		}

	}

}
