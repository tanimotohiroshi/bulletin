package bulletinBoard.beans;

import java.util.Date;

public class UserComment {


	private int id;
	private int postingId;
	private String message;
	private String name;
	private Date updateDate;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}
	public int getPostingId() {
		return postingId;
	}
	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}





}