package model;

import dao.AccountDAO;

public class DeleteAccountLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.deleteAccount(account);
		return result;
	}
}
