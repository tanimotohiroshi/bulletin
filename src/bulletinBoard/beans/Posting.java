package bulletinBoard.beans;

import java.io.Serializable;
import java.util.Date;

public class Posting implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;
	private int userId;
	private String title;
	private String message;
	private String category;
	private Date update_date;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
