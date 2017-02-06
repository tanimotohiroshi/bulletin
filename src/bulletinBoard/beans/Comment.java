package bulletinBoard.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private int id;
	private int postingId;
	private int userId;
	private String message;
	private Date updateDate;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostingId() {
		return postingId;
	}
	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
