package com.librarymgm.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class BookDTO extends BaseDTO {
	
	@Column(name="BOOK_CODE")
	private long bookCode;
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="BOOK_UPDATE",length = 225)
	private String bookUpdate;
	@Column(name="WRITER_NAME",length = 225)
	private String writerName;
	@Column(name="DESCRIPTION",length = 755)
	private String description;
	
	@Lob
	@Column(name = "IMAGE", columnDefinition = "LONGBLOB")
	private byte[] image;
	
	
	
	public long getBookCode() {
		return bookCode;
	}

	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookUpdate() {
		return bookUpdate;
	}

	public void setBookUpdate(String bookUpdate) {
		this.bookUpdate = bookUpdate;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return name;
	}

}
