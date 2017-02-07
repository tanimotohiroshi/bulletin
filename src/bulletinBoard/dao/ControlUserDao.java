package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.ControlUser;
import bulletinBoard.exception.SQLRuntimeException;

public class ControlUserDao {


	public List<ControlUser> getControlUser (Connection connection) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" id, login_id, name, is_stopped");
			sql.append(" from users ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<ControlUser> ret = toControlUser(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<ControlUser> toControlUser (ResultSet rs)
		throws SQLException {

		List<ControlUser> ret = new ArrayList<ControlUser>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				int isStopped = rs.getInt("is_stopped");

				ControlUser controlUsers = new ControlUser();

				controlUsers.setId(id);
				controlUsers.setLoginId(loginId);
				controlUsers.setName(name);
				controlUsers.setIsStopped(isStopped);


				ret.add(controlUsers);

			}
			return ret;
		} finally {
			close(rs);
		}

	}


}
