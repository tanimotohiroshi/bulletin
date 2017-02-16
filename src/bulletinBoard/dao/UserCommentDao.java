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
			sql.append(" users.name, users.branch_id as branchId, users.id as userId, comments.id, comments.message"
					+ ", comments.insert_date, comments.posting_id ");
			sql.append(" from users left join comments on users.id = comments.user_id");

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
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				int postingId = rs.getInt("posting_id");
				int branchId = rs.getInt("branchId");
				String message = rs.getString("message");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();

				comment.setId(id);
				comment.setUserId(userId);
				comment.setPostingId(postingId);
				comment.setBranchId(branchId);
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
