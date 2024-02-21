package model;

import dao.AccountDAO;

public class RegisterAccountLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.registerAccount(account);
		return result;
	}
}
