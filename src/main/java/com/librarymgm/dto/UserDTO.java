package com.librarymgm.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER")
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "LOGIN", length = 225)
	private String login;

	@Column(name = "PASSWORD", length = 225)
	private String password;

	private String confirmPassword;

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "MOBILE_NO", length = 225)
	private String mobileNo;

	@Column(name = "EMAIL_ID", length = 225)
	private String email;

	@Column(name = "ROLE_ID", length = 225)
	private long roleId;

	@Column(name = "GENDER", length = 225)
	private String gender;

	@Lob
	@Column(name = "profile_Pic", columnDefinition = "LONGBLOB")
	private byte[] profilePic;
	
	@Column(name = "LIBRARY_CODE")
	private long libraryCodel;
	
	

	public long getLibraryCodel() {
		return libraryCodel;
	}

	public void setLibraryCodel(long libraryCodel) {
		this.libraryCodel = libraryCodel;
	}

	public UserDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", dob=" + dob + ", mobileNo=" + mobileNo
				+ ", roleId=" + roleId + ", gender=" + gender + ", profilePic=" + profilePic + "]";
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return firstName + " " + lastName;
	}
}
