package model;

import dao.TrashMemoDAO;

public class DeleteAllMemoLogic {
	public boolean execute(String id) {
		TrashMemoDAO dao = new TrashMemoDAO();
		boolean result = dao.allDeleteMemo(id);
		return result;
	}
}
