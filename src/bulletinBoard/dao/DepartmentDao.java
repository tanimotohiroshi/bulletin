package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Department;
import bulletinBoard.exception.SQLRuntimeException;

public class DepartmentDao {

	public List<Department> getDepartmentName() {

		PreparedStatement ps = null;
		Connection connection =null;

		try {

			connection = getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select * from departments");
			sql.append(" order by id");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Department> ret = toDepartmentName(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<Department> toDepartmentName(ResultSet rs) throws SQLException {
		List<Department> ret = new ArrayList<Department>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Department department = new Department();
				department.setId(id);
				department.setName(name);

				ret.add(department);
			}
			return ret;
		} finally {
			close(rs);
		}

	}
}