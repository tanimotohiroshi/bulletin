package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.User;
import bulletinBoard.exception.NoRowsUpdatedRuntimeException;
import bulletinBoard.exception.SQLRuntimeException;

public class UserDao {

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("insert into users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(" ) values ( ");
			sql.append(" ?, ?, ?, ?, ?)");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDepartmentId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String loginId, String password) {

		PreparedStatement ps = null;

		try {
			String sql = "select*from users where login_id = ? and password=?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;

		try {
			String sql = "select * from users where id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> editUserList = toUserList(rs);

			if (editUserList.isEmpty()) {
				return null;
			} else {
				return editUserList.get(0);
			}

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

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update users set");
			sql.append(" login_id = ?, password = ?, name = ?, branch_id = ?, department_id = ?");
			sql.append(" WHERE");
			sql.append(" id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDepartmentId());
			ps.setInt(6, user.getId());


			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


}
