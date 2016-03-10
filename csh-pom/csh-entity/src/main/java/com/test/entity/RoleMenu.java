package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_role_menu database table.
 * 
 */
@Entity
@Table(name="cshm_role_menu")
@NamedQuery(name="RoleMenu.findAll", query="SELECT r FROM RoleMenu r")
public class RoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="menu_id")
	private String menuId;

	@Column(name="rel_id")
	private int relId;

	@Column(name="role_id")
	private String roleId;

	public RoleMenu() {
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getRelId() {
		return this.relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}