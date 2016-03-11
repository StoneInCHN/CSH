package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csh_wallet_packet database table.
 * 
 */
@Entity
@Table(name="csh_wallet_packet")
@NamedQuery(name="WalletPacket.findAll", query="SELECT w FROM WalletPacket w")
public class WalletPacket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_WALLET_PACKET_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_WALLET_PACKET_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String admin;

	private String code;

	private Timestamp effect;

	private int money;

	private String status;

	private int type;

	//bi-directional many-to-one association to Wallet
	@ManyToOne
	@JoinColumn(name="wid")
	private Wallet cshWallet;

	public WalletPacket() {
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getEffect() {
		return this.effect;
	}

	public void setEffect(Timestamp effect) {
		this.effect = effect;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Wallet getCshWallet() {
		return this.cshWallet;
	}

	public void setCshWallet(Wallet cshWallet) {
		this.cshWallet = cshWallet;
	}

}