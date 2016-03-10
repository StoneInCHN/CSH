package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_user_role database table.
 * 
 */
@Entity
@Table(name="cshm_user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_USER_ROLE_RELID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_USER_ROLE_RELID_GENERATOR")
	@Column(name="rel_id")
	private int relId;

	@Column(name="role_id")
	private int roleId;

	@Column(name="user_id")
	private int userId;

	public UserRole() {
	}

	public int getRelId() {
		return this.relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}