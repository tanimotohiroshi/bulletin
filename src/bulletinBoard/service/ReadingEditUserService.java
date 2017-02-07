package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.User;
import bulletinBoard.beans.UserPostings;
import bulletinBoard.dao.UserDao;
import bulletinBoard.dao.UserPostingDao;

public class ReadingEditUserService {

	public List<User> getReadingUser() {

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
	
	public void reading(User user) {
		Connection connection = null;
		try {
			connection = getConnection();
			
			UserDao userDao = new UserDao();
			userDao.reading(connection, user);
			
			commit(connection);
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

}
