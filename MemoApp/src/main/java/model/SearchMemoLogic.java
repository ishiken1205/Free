package model;

import java.util.List;

import dao.MemoDAO;
import dao.TrashMemoDAO;

public class SearchMemoLogic {
	public List<Memo> execute(String id, String searchMemoTitle) {
		MemoDAO dao = new MemoDAO();
		List<Memo> ml = dao.findByMemo(id, searchMemoTitle);
		return ml;
	}
	public List<Memo> executeTrashMemo(String id) {
		TrashMemoDAO dao = new TrashMemoDAO();
		List<Memo> ml = dao.findByAllMemo(id);
		return ml;
	}
}
