package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Category;
import bulletinBoard.exception.SQLRuntimeException;

public class CategoryDao {

	public List<Category> getCategoryList() {

		PreparedStatement ps = null;
		Connection connection =null;

		try {

			connection = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select distinct category from postings"
					+ " inner join users on postings.user_id = users.id");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Category> ret = toCategoryName(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<Category> toCategoryName(ResultSet rs) throws SQLException {
		List<Category> ret = new ArrayList<Category>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				Category categoryName = new Category();

				categoryName.setCategory(category);

				ret.add(categoryName);
			}
			return ret;
		} finally {
			close(rs);
		}

	}
}