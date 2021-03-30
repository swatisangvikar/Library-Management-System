package com.librarymgm.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ISSUE_BOOK")
public class IssueBookDTO extends BaseDTO {

	@Column(name = "BOOK_NAME", length = 225)
	private String bookName;
	@Column(name = "WRITER_NAME", length = 225)
	private String writerName;
	@Column(name = "ISSUE_DATE")
	@Temporal(TemporalType.DATE)
	private Date issuDate;
	@Column(name = "RETURN_DATE")
	@Temporal(TemporalType.DATE)
	private Date returnDate;
	@Column(name = "BOOK_CODE")
	private long bookCode;
	@Column(name = "STUDENT_NAME", length = 225)
	private String studentName;
	@Column(name = "LIB_CODE")
	private long libCode;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public Date getIssuDate() {
		return issuDate;
	}

	public void setIssuDate(Date issuDate) {
		this.issuDate = issuDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public long getBookCode() {
		return bookCode;
	}

	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	

	public long getLibCode() {
		return libCode;
	}

	public void setLibCode(long libCode) {
		this.libCode = libCode;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
