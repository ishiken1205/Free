package model;

import dao.MemoDAO;
import dao.TrashMemoDAO;

public class AddMemoLogic {
	public boolean execute(Memo m) {
		MemoDAO dao = new MemoDAO();
		boolean result = dao.addMemo(m);
		return result;
	}
	public boolean executeTrashMemo(Memo m) {
		MemoDAO dao1 = new MemoDAO();
		boolean result1 = dao1.deleteMemo(m);
		TrashMemoDAO dao2 = new TrashMemoDAO();
		boolean result2 = dao2.addMemo(m);
		if(result1 && result2) {
			return true;
		}else {
			return false;
		}
	}
	public boolean executeRestoreMemo(Memo m) {
		TrashMemoDAO dao1 = new TrashMemoDAO();
		boolean result1 = dao1.deleteMemo(m);
		MemoDAO dao2 = new MemoDAO();
		boolean result2 = dao2.addMemo(m);
		if(result1 && result2) {
			return true;
		}else {
			return false;
		}
	}
}
