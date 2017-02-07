package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.User;
import bulletinBoard.exception.SQLRuntimeException;

public class EditUserReadingDao {

	public User getReadingUser(Connection connection, int id) {

		PreparedStatement ps = null;

		try {
			String sql = "select*from users where id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);


			ResultSet rs = ps.executeQuery();
			List<User> ret = to(rs);
			
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int departmentId = rs.getInt("department_id");

				User user = new User();

				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branchId);
				user.setDepartmentId(departmentId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
