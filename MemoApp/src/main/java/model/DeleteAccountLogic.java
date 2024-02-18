package model;

import dao.AccountDAO;
import dao.MemoDAO;
import dao.TrashMemoDAO;

public class DeleteAccountLogic {
	public boolean execute(String id, String pass) {
		AccountDAO dao1 = new AccountDAO();
		boolean result = dao1.deleteAccount(id, pass);
		if(result) {
			MemoDAO dao2 = new MemoDAO();
			dao2.deleteAllMemo(id);
			TrashMemoDAO dao3 = new TrashMemoDAO();
			dao3.allDeleteMemo(id);
			return true;
		}else {
			return false;
		}
	}
}
