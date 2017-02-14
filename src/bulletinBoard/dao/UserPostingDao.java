package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserPostings;
import bulletinBoard.exception.SQLRuntimeException;

public class UserPostingDao {

	public List<UserPostings> getUserPostings(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select");
			sql.append(
					" name, postings.title, postings.message, postings.category, postings.update_date, postings.id ");
			sql.append(" from users left join postings on users.id = postings.user_id ");
			sql.append(" order by postings.id desc;");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPostings> ret = toUserPostingsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	/* 日時とカテゴリーの絞り込み */
	public List<UserPostings> getValidPostings(Connection connection, String startDate, String endDate,
			String category) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select name, postings.title, postings.message, postings.category, postings.update_date");
			sql.append(" , postings.id from users left join postings on users.id = postings.user_id ");
			sql.append(" where update_date between ? and ? ");
			if (category != null ){
				if (category.length() != 0){
				sql.append(" and category = ?");
				}
			}
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, startDate);
			ps.setString(2, endDate);
			if ( category != null) {
				if ( category.length() != 0) {
				ps.setString(3, category);
				}
			}

			ResultSet rs = ps.executeQuery();
			List<UserPostings> ret = toUserPostingsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPostings> toUserPostingsList(ResultSet rs) throws SQLException {

		List<UserPostings> ret = new ArrayList<UserPostings>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String message = rs.getString("message");
				String name = rs.getString("name");
				String category = rs.getString("category");
				Timestamp updateDate = rs.getTimestamp("update_date");

				UserPostings postings = new UserPostings();

				postings.setId(id);
				postings.setTitle(title);
				postings.setMessage(message);
				postings.setName(name);
				postings.setCategory(category);
				postings.setUpdateDate(updateDate);

				ret.add(postings);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
