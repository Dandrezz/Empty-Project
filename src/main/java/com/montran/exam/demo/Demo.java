package com.montran.exam.demo;

import java.math.BigDecimal;

import com.montran.exam.ach.AchManager;
import com.montran.exam.exceptions.AchException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.PersistenceException;
<<<<<<< HEAD
import com.montran.exam.exceptions.RTGSException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.participant.ParticipantDTO;
import com.montran.exam.participant.manager.ParticipantManger;
=======
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.persistence.PersistenceStrategy;
import com.montran.exam.persistence.impl.JsonPersistence;
>>>>>>> 24dff98f02ed980131ea3fafac8f3e55f0b1cb84
import com.montran.exam.persistence.impl.XmlPersistence;
import com.montran.exam.rtgs.RtgsManager;

/**
 * @author Diego Portero
 *
 */

public class Demo {

	public static void main(String[] args) {
		
		Log log = Log.getInstance();
		
		
		ParticipantManger.getInstance().createParticipant("Banco Pichicha", "Pichin", "diego@pichincha.fin.ec", "0999261556", "PICHECEQ");
		ParticipantManger.getInstance().createParticipant("Banco de Guayaquil", "Guay", "daniel@guayaquil.fin.ec", "0999261557", "GUAYECEG");
		ParticipantManger.getInstance().createParticipant("Bancho Produbanco", "Produ", "paul@produbanco.fin.ec", "0999261558", "PRODECEQ");
		
		
		//String codeSwift, String currency, BigDecimal initialBalance,BigDecimal currentBalance
		try {
			ParticipantManger.getInstance().setBalancetSettlementAccount("PICHECEQ", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(4500));
			ParticipantManger.getInstance().setBalancetSettlementAccount("GUAYECEG", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(2500));
			ParticipantManger.getInstance().setBalancetSettlementAccount("PRODECEQ", "BTC", BigDecimal.ZERO, BigDecimal.valueOf(500));
		} catch (ParticipantException e2) {
			log.write("Failed set the balance", Demo.class , LogLevels.ERROR);
		}
		
		try {
			//It will read and perform transactions with the rtgs
			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
			RtgsManager.getInstance().transactionProcessing();
			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
		} catch (RTGSException e) {
			log.write("Failed to execute RTGS", Demo.class , LogLevels.ERROR);
		} catch (ParticipantException e) {
			log.write("Failed to execute RTGS", Demo.class , LogLevels.ERROR);
		}
		
		try {
			ParticipantManger.getInstance().saveParticipant(new XmlPersistence<ParticipantDTO>());
		} catch (ParticipantException | PersistenceException e1) {
			e1.printStackTrace();
			log.write("Persist error", Demo.class , LogLevels.ERROR);
		}
		System.out.println("--------");
		//It will read and perform transactions with the ach_file files
		try {
			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
			AchManager.getInstance().transactionProcessing();
			ParticipantManger.getInstance().printSettlementAccount("PICHECEQ", "BTC");
		} catch (AchException e) {
			log.write("Failed to execute ACH", Demo.class , LogLevels.ERROR);
		} catch (ParticipantException e) {
			log.write("Failed to execute ACH", Demo.class , LogLevels.ERROR);
		}

	}

}
