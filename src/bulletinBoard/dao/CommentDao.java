package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinBoard.beans.Comment;
import bulletinBoard.exception.SQLRuntimeException;

public class CommentDao {
	public void insert (Connection connection, Comment comment) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into comments");
			sql.append(" (posting_id, user_id, message, insert_date )");
			sql.append(" values ");
			sql.append(" (?, ?, ?, current_timestamp )" );


			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getPostingId());
			ps.setInt(2, comment.getUserId());
			ps.setString(3, comment.getMessage());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
