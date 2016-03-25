package com.csh.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.MessageType;


/**
 * Entity - 推送消息
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_message_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_message_info_sequence")
public class MessageInfo extends BaseEntity {

  private static final long serialVersionUID = 1170442128165498366L;


  /** 消息类型 */
  private MessageType messageType;

  /** 消息标题 */
  private String messageTitle;

  /** 消息内容 */
  private String messageContent;

  /** 消息、会员对应关系实体 */
  private Set<MsgEndUser> msgUser = new HashSet<MsgEndUser>();


  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  public String getMessageTitle() {
    return messageTitle;
  }

  public void setMessageTitle(String messageTitle) {
    this.messageTitle = messageTitle;
  }

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


}
