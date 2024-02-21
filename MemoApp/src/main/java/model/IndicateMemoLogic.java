package model;

import dao.MemoDAO;
import dao.TrashMemoDAO;

public class IndicateMemoLogic {
	public Memo execute(String id, String title) {
		MemoDAO dao = new MemoDAO();
		Memo m = dao.indicateMemo(id, title);
		return m;
	}
	public Memo executeTrashMemo(String id, String title) {
		TrashMemoDAO dao = new TrashMemoDAO();
		Memo m = dao.indicateMemo(id, title);
		return m;
	}
}