package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the csh_wallet database table.
 * 
 */
@Entity
@Table(name="csh_wallet")
@NamedQuery(name="Wallet.findAll", query="SELECT w FROM Wallet w")
public class Wallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_WALLET_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_WALLET_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private float money;

	private float red;

	private float score;

	//bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name="uid")
	private UserInfo cshUserInfo;

	//bi-directional many-to-one association to WalletPacket
	@OneToMany(mappedBy="cshWallet")
	private Set<WalletPacket> cshWalletPackets;

	public Wallet() {
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

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getRed() {
		return this.red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public UserInfo getCshUserInfo() {
		return this.cshUserInfo;
	}

	public void setCshUserInfo(UserInfo cshUserInfo) {
		this.cshUserInfo = cshUserInfo;
	}

	public Set<WalletPacket> getCshWalletPackets() {
		return this.cshWalletPackets;
	}

	public void setCshWalletPackets(Set<WalletPacket> cshWalletPackets) {
		this.cshWalletPackets = cshWalletPackets;
	}

	public WalletPacket addCshWalletPacket(WalletPacket cshWalletPacket) {
		getCshWalletPackets().add(cshWalletPacket);
		cshWalletPacket.setCshWallet(this);

		return cshWalletPacket;
	}

	public WalletPacket removeCshWalletPacket(WalletPacket cshWalletPacket) {
		getCshWalletPackets().remove(cshWalletPacket);
		cshWalletPacket.setCshWallet(null);

		return cshWalletPacket;
	}

}