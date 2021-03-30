package com.librarymgm.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class RoleDTO extends BaseDTO {

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE_SCHOOL = 3;
	public static final int KIOSK = 4;

	@Column(name = "NAME", length = 225)
	private String name;

	@Column(name = "DESCRIPTION", length = 225)
	private String description;

	@Override
	public String toString() {
		return "RoleDTO [name=" + name + ", description=" + description + "]";
	}

	public RoleDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}
}
