package com.librarymgm.form;

import javax.validation.constraints.NotEmpty;

import com.librarymgm.util.DataUtility;

import com.librarymgm.dto.BaseDTO;
import com.librarymgm.dto.UserDTO;


public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotEmpty(message = "Login is required")
	private String login;
	
	@NotEmpty(message = "Mobile No is required")
	private String mobileNo;

	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "gender is required")
	private String gender;
	
	@NotEmpty(message = "Date of Birth is required")
	private String dob;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BaseDTO getDto() {
		UserDTO dto = new UserDTO();
		dto.setMobileNo(mobileNo);
		dto.setLogin(login);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(DataUtility.getDate(dob));
		dto.setGender(gender);
		dto.setEmail(email);
		return dto;
	}

	
	public void populate(BaseDTO bDto) {
		UserDTO dto = (UserDTO) bDto;
		mobileNo = dto.getMobileNo();
		login = dto.getLogin();
		firstName = dto.getFirstName();
		lastName = dto.getLastName();
		gender=dto.getGender();
		dob=DataUtility.getDateString(dto.getDob());
		email=dto.getEmail();

	}

	

}
