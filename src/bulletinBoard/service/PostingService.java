package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;

import bulletinBoard.beans.Posting;
import bulletinBoard.dao.PostingDao;

public class PostingService {


	public void register (Posting posting) {

		Connection connection = null;
		try{
			connection = getConnection();

			PostingDao postingDao = new PostingDao();
			postingDao.insert(connection, posting);

			commit(connection);

		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


}
