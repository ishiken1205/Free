package model;

import dao.AccountDAO;

public class LoginLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		Account ac = dao.findByAccount(account);
		if(ac != null) {
			return true;
		}return false;
	}
}
