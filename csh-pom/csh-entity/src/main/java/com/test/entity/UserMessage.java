package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_user_message database table.
 * 
 */
@Entity
@Table(name="csh_user_message")
@NamedQuery(name="UserMessage.findAll", query="SELECT u FROM UserMessage u")
public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_USER_MESSAGE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_USER_MESSAGE_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String status;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	//bi-directional many-to-one association to UserMessageInfo
	@ManyToOne
	@JoinColumn(name="msg_id")
	private UserMessageInfo cshUserMessageInfo;

	public UserMessage() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserInfo getCshUserInfo() {
		return this.cshUserInfo;
	}

	public void setCshUserInfo(UserInfo cshUserInfo) {
		this.cshUserInfo = cshUserInfo;
	}

	public UserMessageInfo getCshUserMessageInfo() {
		return this.cshUserMessageInfo;
	}

	public void setCshUserMessageInfo(UserMessageInfo cshUserMessageInfo) {
		this.cshUserMessageInfo = cshUserMessageInfo;
	}

}