/**
 * 
 */
package com.montran.exam.account.manager;

import com.montran.exam.account.BaseAccount;

/**
 * @author Diego Portero
 *
 */
public interface BaseAccountManager<T extends BaseAccount> {

	public void createAccount(T acountToBeAdded );
	
}
