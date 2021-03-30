package com.librarymgm.form;

import javax.validation.constraints.NotEmpty;

import com.librarymgm.dto.BaseDTO;
import com.librarymgm.dto.UserDTO;


public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Login is required")
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public BaseDTO getDto() {

		UserDTO dto = new UserDTO();
		dto.setLogin(login);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {

	}

}
