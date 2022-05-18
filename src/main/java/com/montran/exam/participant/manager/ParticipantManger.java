package com.montran.exam.participant.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.jpa.rs.PersistenceFactoryBase;

import com.montran.exam.account.SettlementAccount;
import com.montran.exam.account.manager.impl.AccountManager;
import com.montran.exam.currency.Currency;
import com.montran.exam.currency.manager.CurrencyManager;
import com.montran.exam.exceptions.CurrencyException;
import com.montran.exam.exceptions.LogException;
import com.montran.exam.exceptions.ParticipantException;
import com.montran.exam.exceptions.PersistenceException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.participant.Participant;
import com.montran.exam.participant.ParticipantsDTO;
import com.montran.exam.persistence.Archivable;
import com.montran.exam.persistence.PersistenceFactory;
import com.montran.exam.persistence.PersistenceStrategy;
import com.montran.exam.status.ParticipantStatus;
import com.montran.exam.utils.UUIDGenerator;

public class ParticipantManger {
	/**
	 * Instance of this class
	 */
	private static ParticipantManger instance;
	/**
	 * Map to stores the participants
	 */
	private Map<Integer, Participant> participants;
	private Log log;
	
	/**
	 * Get the instance of this class This method is Thread Safety
	 * 
	 * @return the unique instance of the class
	 * @throws LogException 
	 */
	public static ParticipantManger getInstance() throws LogException {
		if (instance == null) {
			synchronized (ParticipantManger.class) {
				if (instance == null) {
					instance = new ParticipantManger();
				}
			}
		}
		return instance;
	}

	/**
	 * Constructor without parameters
	 * @throws LogException 
	 */
	public ParticipantManger() throws LogException {
		participants = new HashMap<Integer, Participant>();
		log = Log.getInstance();
	}

	/**
	 * Method to create a new object of type Participant
	 * 
	 * @param idParticipant
	 * @param fullNameParticipant
	 * @param shortNameParticipant
	 * @param emailParticipant
	 * @param cellPhoneNumber
	 * @param switfCode
	 * 
	 * @return a new instantiated object of type participant
	 * @throws LogException 
	 * @throws CurrencyException 
	 */
	public Participant createParticipant(String fullNameParticipant, String shortNameParticipant,
			String emailParticipant, String cellPhoneNumber, String switfCode) throws LogException, CurrencyException {

		Participant newParticipant = new Participant();
		newParticipant.setIdParticipant(UUIDGenerator.getInstance().getUuid32bits());
		newParticipant.setFullNameParticipant(fullNameParticipant);
		newParticipant.setShortNameParticipant(shortNameParticipant);
		newParticipant.setEmailParticipant(emailParticipant);
		newParticipant.setCellPhoneNumber(cellPhoneNumber);
		newParticipant.setSwitfCode(switfCode);
		newParticipant.setStatus(ParticipantStatus.ACTIVE);

		Map<String, SettlementAccount> settlementAccounts = new HashMap<String, SettlementAccount>();

		try {
			for (Currency currency : CurrencyManager.getInstance().getInfoCurrencies().values()) {
				SettlementAccount newSettlementAccount = AccountManager.getInstance().createSettlementAccount(
						newParticipant.getIdParticipant(), currency.getCode(), BigDecimal.ZERO, BigDecimal.ZERO);
				settlementAccounts.put(currency.getCode(), newSettlementAccount);
			}
		} catch (CurrencyException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new CurrencyException(e.getMessage(),e);
		}
		newParticipant.setSettlementAccounts(settlementAccounts);
		participants.put(newParticipant.getIdParticipant(), newParticipant);

		return newParticipant;
	}

	/**
	 * This method will search for a participant by its SWIFT| code
	 * @param codeSwift
	 * @return 
	 * @throws ParticipantException
	 */
	public Participant findParticipantByCodeSwitf(String codeSwift) throws ParticipantException{

		Participant participantFilter = participants.values().stream()
				.filter(participant -> participant.getSwitfCode().equals(codeSwift))
				.findFirst()
				.orElseThrow(()-> new ParticipantException("Unregistered participant"));
		return participantFilter;

	}
	
	/**
	 * This method to will balance the account of two participants
	 * @param participantA
	 * @param participantB
	 * @param currency
	 * @param amount
	 */
	public void balanceAccounts(Participant participantA, Participant participantB, String currency,BigDecimal amount) {
		
		SettlementAccount settlementAccountsParticipantA = participantA.getSettlementAccounts().get(currency);
		SettlementAccount settlementAccountsParticipantB = participantB.getSettlementAccounts().get(currency);

		BigDecimal newBalanceParticipantA = settlementAccountsParticipantA.getCurrentBalance().subtract(amount);
		BigDecimal newBalanceParticipantB = settlementAccountsParticipantB.getCurrentBalance().add(amount);
		
		settlementAccountsParticipantA.setCurrentBalance(newBalanceParticipantA);
		settlementAccountsParticipantA.setNumberOfDebits(settlementAccountsParticipantA.getNumberOfDebits()+1);

		settlementAccountsParticipantB.setCurrentBalance(newBalanceParticipantB);
		settlementAccountsParticipantB.setNumberOfCredits(settlementAccountsParticipantB.getNumberOfCredits()+1);
		
	}
	
	/**
	 * This method to will balance the account of two participants
	 * @param codeSwiftA
	 * @param codeSwiftB
	 * @param currency
	 * @param amount
	 * @throws ParticipantException
	 */
	public void balanceAccounts(String codeSwiftA, String codeSwiftB, String currency,BigDecimal amount) throws ParticipantException {
		
		try {
			balanceAccounts(findParticipantByCodeSwitf(codeSwiftA),findParticipantByCodeSwitf(codeSwiftB),currency,amount);
		} catch (ParticipantException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new ParticipantException(e.getLocalizedMessage(),e);
		}
		
	}
	
	/**
	 * This method to set initial balance and current balance to settlement account
	 * 
	 * @param codeSwift code Swift of settlement account
	 * @param currency type of currency
	 * @param initialBalance initial value of balance
	 * @param currentBalance current value of balance
	 * @throws ParticipantException
	 */
	public void setBalancetSettlementAccount(String codeSwift, String currency, BigDecimal initialBalance,BigDecimal currentBalance) throws ParticipantException {
		try {
			Participant participantSearch = findParticipantByCodeSwitf(codeSwift);
			SettlementAccount settlementAccountSearch = participantSearch.getSettlementAccounts().get(currency);
			settlementAccountSearch.setInitialBalance(initialBalance);
			settlementAccountSearch.setCurrentBalance(currentBalance);
		} catch (ParticipantException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new ParticipantException(e.getMessage(),e);
		}
	}
	
	/**
	 * This method to print data of settlement account
	 * @param codeSwift code Swift of settlement account
	 * @param currency type of currency
	 * @throws ParticipantException 
	 */
	public void printSettlementAccount(String codeSwift, String currency) throws ParticipantException {
		try {
			Participant participantSearch = findParticipantByCodeSwitf(codeSwift);
			SettlementAccount settlementAccountSearch = participantSearch.getSettlementAccounts().get(currency);
			System.out.println(settlementAccountSearch);
		} catch (ParticipantException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new ParticipantException(e.getMessage(),e);
		}
	}
	
	public void saveParticipant() throws ParticipantException, LogException, PersistenceException {
		PersistenceStrategy<Archivable> persistence = PersistenceFactory.getInstance().buildPersistenceStrategy();
		ParticipantsDTO participantDTO = new ParticipantsDTO();
		List<Participant> partipantAux = new ArrayList<Participant>(participants.values());
		participantDTO.setParticipants(partipantAux);
		try {
			persistence.save(participantDTO, "participants");
			log.write("Save participants", LogLevels.INFO);
		} catch (PersistenceException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
			throw new ParticipantException(e.getMessage(),e);
		}
	}

}
