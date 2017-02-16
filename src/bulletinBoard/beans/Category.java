package bulletinBoard.beans;

import java.io.Serializable;

public class Category implements Serializable{
	private static final long serialversionUID = 1L;


	public static long getSerialversionuid() {
		return serialversionUID;
	}

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



}
