package com.librarymgm.form;

import javax.validation.constraints.NotEmpty;

import com.librarymgm.util.DataUtility;

import com.librarymgm.dto.BaseDTO;
import com.librarymgm.dto.IssueBookDTO;

public class IssueBookForm extends BaseForm {

	@NotEmpty(message = "Student Library Code is required")
	private String libCode;
	@NotEmpty(message = "Book Code is required")
	private String bCode;
	@NotEmpty(message = "Issue Date is required")
	private String issueDate;
	@NotEmpty(message = "Return is required")
	private String returnDate;
	
	
	
	
	public String getLibCode() {
		return libCode;
	}

	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}

	public String getbCode() {
		return bCode;
	}

	public void setbCode(String bCode) {
		this.bCode = bCode;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public BaseDTO getDto() {
	IssueBookDTO dto=new IssueBookDTO();
		dto.setId(id);
		dto.setLibCode(DataUtility.getLong(libCode));
		dto.setBookCode(DataUtility.getLong(bCode));
		dto.setIssuDate(DataUtility.getDate(issueDate));
		dto.setReturnDate(DataUtility.getDate(returnDate));
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		IssueBookDTO dto=(IssueBookDTO)bDto;
		id=dto.getId();
		bCode=String.valueOf(dto.getBookCode());
		libCode=String.valueOf(dto.getLibCode());
		issueDate=DataUtility.getDateString(dto.getIssuDate());
		returnDate=DataUtility.getDateString(dto.getReturnDate());
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
	}
	

}
