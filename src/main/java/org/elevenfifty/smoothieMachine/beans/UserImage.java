package org.elevenfifty.smoothieMachine.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.springframework.util.Base64Utils;

@Entity
@Table(name = "user_images")
public class UserImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(unique=true)
	long userId;
	
	String contentType;
	
	//possibly switch around
	@Column(unique=true)
	@Lob
	private byte[] image;
	
	protected UserImage(){}

	public UserImage(long userId) {
		this.userId = userId;
	}

	public UserImage(long userId, String contentType, byte[] image) {
		this.userId = userId;
		this.contentType = contentType;
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserImage [id=" + id + ", userId=" + userId + ", contentType=" + contentType + "]";
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getHtmlSrc() {
        return "data:" + this.contentType + ";base64," + Base64Utils.encodeToString(image);
    }
}

