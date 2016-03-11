package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the csh_user_message_info database table.
 * 
 */
@Entity
@Table(name="csh_user_message_info")
@NamedQuery(name="UserMessageInfo.findAll", query="SELECT u FROM UserMessageInfo u")
public class UserMessageInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_USER_MESSAGE_INFO_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_USER_MESSAGE_INFO_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	@Lob
	private String body;

	private String content;

	private String icon;

	private String status;

	private String title;

	private String type;

	private BigInteger uid;

	//bi-directional many-to-one association to UserMessage
	@OneToMany(mappedBy="cshUserMessageInfo")
	private Set<UserMessage> cshUserMessages;

	public UserMessageInfo() {
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

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigInteger getUid() {
		return this.uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public Set<UserMessage> getCshUserMessages() {
		return this.cshUserMessages;
	}

	public void setCshUserMessages(Set<UserMessage> cshUserMessages) {
		this.cshUserMessages = cshUserMessages;
	}

	public UserMessage addCshUserMessage(UserMessage cshUserMessage) {
		getCshUserMessages().add(cshUserMessage);
		cshUserMessage.setCshUserMessageInfo(this);

		return cshUserMessage;
	}

	public UserMessage removeCshUserMessage(UserMessage cshUserMessage) {
		getCshUserMessages().remove(cshUserMessage);
		cshUserMessage.setCshUserMessageInfo(null);

		return cshUserMessage;
	}

}