package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Branch;
import bulletinBoard.exception.SQLRuntimeException;

public class BranchDao {

	public List<Branch> getBranchName() {

		PreparedStatement ps = null;
		Connection connection =null;

		try {

			connection = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from branchs");
			sql.append(" order by id");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchName(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<Branch> toBranchName(ResultSet rs) throws SQLException {
		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);

				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}

	}
}