package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserComment;
import bulletinBoard.exception.SQLRuntimeException;


public class UserCommentDao {

	public List<UserComment> getUserComment ( Connection connection ) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select");
			sql.append(" users.name, comments.message, comments.insert_date, comments.posting_id ");
			sql.append(" from comments left join users on ");
			sql.append(" users.id = comments.user_id ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserComment(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserComment(ResultSet rs)
		throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int postingId = rs.getInt("posting_id");
				String message = rs.getString("message");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();

				comment.setPostingId(postingId);
				comment.setMessage(message);
				comment.setName(name);
				comment.setInsertDate(insertDate);

				ret.add(comment);

			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
