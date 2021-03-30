package com.librarymgm.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.librarymgm.util.DataUtility;

import com.librarymgm.dto.BaseDTO;
import com.librarymgm.dto.BookDTO;

public class BookForm extends BaseForm {
	
	@NotEmpty(message = "Code is required")
	private String code;
	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "Status is required")
	private String bookUpdate;
	@NotEmpty(message = "Writer is required")
	private String writerName;
	@NotEmpty(message = "Description is required")
	private String description;
	private MultipartFile image;

	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public BaseDTO getDto() {
		BookDTO dto=new BookDTO();
		dto.setId(id);
		dto.setBookCode(DataUtility.getLong(code));
		dto.setName(name);
		dto.setBookUpdate(bookUpdate);
		dto.setWriterName(writerName);
		dto.setDescription(description);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		BookDTO dto=(BookDTO) bDto;
		id=dto.getId();
		code=String.valueOf(dto.getBookCode());
		bookUpdate=dto.getBookUpdate();
		writerName=dto.getWriterName();
		description=dto.getDescription();
		name=dto.getName();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
	}

}
