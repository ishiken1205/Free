package model;

import dao.MemoDAO;

public class EditMemoLogic {
	public boolean execute(Memo beforeMemo, Memo afterMemo) {
		MemoDAO dao = new MemoDAO();
		boolean result1 = dao.deleteMemo(beforeMemo);
		boolean result2 = dao.addMemo(afterMemo);
		if(result1 && result2) {
			return true;
		}else {
			return false;
		}
	}
}
