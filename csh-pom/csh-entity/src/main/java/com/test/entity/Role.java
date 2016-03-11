package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_role database table.
 * 
 */
@Entity
@Table(name="cshm_role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_ROLE_ROLEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_ROLE_ROLEID_GENERATOR")
	@Column(name="role_id")
	private int roleId;

	@Column(name="role_desc")
	private String roleDesc;

	@Column(name="role_name")
	private String roleName;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}