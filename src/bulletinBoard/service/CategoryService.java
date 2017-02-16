package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.Category;
import bulletinBoard.dao.CategoryDao;

public class CategoryService {

	public List<Category> getCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			CategoryDao categoryDao = new CategoryDao();
			List<Category> ret = categoryDao.getCategoryList();

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
