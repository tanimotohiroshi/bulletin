package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinBoard.beans.Posting;
import bulletinBoard.exception.SQLRuntimeException;

public class PostingDao {

	public void insert (Connection connection, Posting posting) {

		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("insert into postings");
			sql.append(" (user_id, title, message, category, insert_date)");
			sql.append(" values ");
			sql.append("(?, ?, ?, ?, current_timestamp )");

			ps = connection.prepareStatement(sql.toString());


			ps.setInt(1, posting.getUserId());
			ps.setString(2, posting.getTitle());
			ps.setString(3, posting.getMessage());
			ps.setString(4, posting.getCategory());


			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


}
