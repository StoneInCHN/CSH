package com.csh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.entity.commonenum.CommonEnum.SendType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 推送消息
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_message_info")
@Indexed(index = "messageInfo")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_message_info_sequence")
public class MessageInfo extends BaseEntity {

	private static final long serialVersionUID = 1170442128165498366L;

	/** 消息类型 */
	private MessageType messageType;

	/** 消息标题 */
	private String messageTitle;

	/** 消息内容 */
	private String messageContent;

	private SendType sendType;

	/**
	 * 经度
	 */
	private String lon;
	/**
	 * 纬度
	 */
	private String lat;
	/** 异常发生地址 */
	private String alarmPlace;
	/** 消息、会员对应关系实体 */
	private Set<MsgEndUser> msgUser = new HashSet<MsgEndUser>();

	/**
	 * 租户ID
	 */
	private Long tenantID;

	@JsonProperty
	@Field(store = Store.NO, index = Index.YES, analyze = Analyze.NO)
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@JsonProperty
	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	@JsonProperty
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<MsgEndUser> getMsgUser() {
		return msgUser;
	}

	public void setMsgUser(Set<MsgEndUser> msgUser) {
		this.msgUser = msgUser;
	}

	@org.hibernate.annotations.Index(name = "messageInfo_tenantid")
	@Field(store = Store.NO, index = Index.YES, analyze = Analyze.NO)
	public Long getTenantID() {
		return tenantID;
	}

	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}

	@Field(store = Store.NO, index = Index.YES, analyze = Analyze.NO)
	@JsonProperty
	public SendType getSendType() {
		return sendType;
	}

	public void setSendType(SendType sendType) {
		this.sendType = sendType;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getAlarmPlace() {
		return alarmPlace;
	}

	public void setAlarmPlace(String alarmPlace) {
		this.alarmPlace = alarmPlace;
	}

}
