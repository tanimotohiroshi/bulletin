package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.Posting;
import bulletinBoard.beans.UserPostings;
import bulletinBoard.dao.PostingDao;
import bulletinBoard.dao.UserPostingDao;

public class PostingService {

	public List<UserPostings> getPostings() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			List<UserPostings> ret = postingDao.getUserPostings(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	
	public List<UserPostings> datePostings() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			List<UserPostings> ret = postingDao.getDatePosting (connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



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


	public List<UserPostings> validPosting (String startDate , String endDate , String category ) {

		Connection connection = null;
		try {
			connection = getConnection();


			UserPostingDao userPostingDao = new UserPostingDao();
			List<UserPostings> ret  = userPostingDao.getValidPostings(connection, startDate, endDate, category);

			commit(connection);

			return ret;
		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


}
