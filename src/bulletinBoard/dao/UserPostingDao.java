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
			sql.append(" name, users.id as userId,  users.branch_id as branchId,"
					+ " postings.title, postings.message, postings.category"
					+ ", postings.insert_date, postings.id ");
			sql.append(" from users left join postings on users.id = postings.user_id ");
			sql.append(" order by postings.insert_date desc ;");

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
			sql.append("select name, users.id as userId, users.branch_id as branchId , "
					+ " postings.title, postings.message, postings.category, postings.insert_date");
			sql.append(" , postings.id from users left join postings on users.id = postings.user_id ");
			sql.append(" where insert_date between ? and ? ");
			if (category != null ){
				if (category.length() != 0){
				sql.append(" and category = ?");
				}
			}

			sql.append(" order by postings.insert_date desc");

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
				int userId = rs.getInt("userId");
				int branchId = rs.getInt("branchId");
				String title = rs.getString("title");
				String message = rs.getString("message");
				String name = rs.getString("name");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPostings postings = new UserPostings();

				postings.setId(id);
				postings.setUserId(userId);
				postings.setBranchId(branchId);
				postings.setTitle(title);
				postings.setMessage(message);
				postings.setName(name);
				postings.setCategory(category);
				postings.setInsertDate(insertDate);

				ret.add(postings);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	/*投稿日時の取得のselect文*/

	public UserPostings getDatePosting (Connection connection ) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select");
			sql.append(" name, users.id as userId, postings.title,  users.branch_id as branchId,"
					+ " postings.message, postings.category, postings.insert_date, postings.id ");
			sql.append(" from users inner join postings on users.id = postings.user_id ");
			sql.append(" order by insert_date;");

			ps = connection.prepareStatement(sql.toString());


			ResultSet rs = ps.executeQuery();
			List<UserPostings> ret = toUserPostingsList(rs);
			if (ret.isEmpty() == true) {
				return null;
			} else {
				return ret.get(0);
			}

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
