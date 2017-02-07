package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.ControlUser;
import bulletinBoard.dao.ControlUserDao;



public class ControlUserService {

	public List<ControlUser> getControlUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			ControlUserDao controlUserDao = new ControlUserDao();
			List<ControlUser> ret = controlUserDao.getControlUser(connection);

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

}
