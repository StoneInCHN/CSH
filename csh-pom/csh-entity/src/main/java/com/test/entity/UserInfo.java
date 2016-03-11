package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the csh_user_info database table.
 * 
 */
@Entity
@Table(name="csh_user_info")
@NamedQuery(name="UserInfo.findAll", query="SELECT u FROM UserInfo u")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSH_USER_INFO_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSH_USER_INFO_ID_GENERATOR")
	private String id;

	@Column(name="add_time")
	private Timestamp addTime;

	private String appedition;

	@Column(name="birth_date")
	private Timestamp birthDate;

	private String client;

	@Column(name="driver_license")
	private String driverLicense;

	private String email;

	private String icon;

	@Column(name="identity_card")
	private String identityCard;

	private BigInteger lid;

	private String mobile;

	@Column(name="mobile_system")
	private String mobileSystem;

	@Column(name="mobile_version")
	private String mobileVersion;

	@Column(name="nick_name")
	private String nickName;

	private String qq;

	private int sex;

	private String signature;

	private int vip;

	//bi-directional many-to-one association to CallBill
	@OneToMany(mappedBy="cshUserInfo")
	private Set<CallBill> cshCallBills;

	//bi-directional many-to-one association to DriverLicense
	@OneToMany(mappedBy="cshUserInfo")
	private Set<DriverLicense> cshDriverLicenses;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="cshUserInfo")
	private Set<Feedback> cshFeedbacks;

	//bi-directional many-to-one association to IdentityCard
	@OneToMany(mappedBy="cshUserInfo")
	private Set<IdentityCard> cshIdentityCards;

	//bi-directional many-to-one association to UserMessage
	@OneToMany(mappedBy="cshUserInfo")
	private Set<UserMessage> cshUserMessages;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="cshUserInfo")
	private Set<Vehicle> cshVehicles;

	//bi-directional many-to-one association to Wallet
	@OneToMany(mappedBy="cshUserInfo")
	private Set<Wallet> cshWallets;

	public UserInfo() {
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

	public String getAppedition() {
		return this.appedition;
	}

	public void setAppedition(String appedition) {
		this.appedition = appedition;
	}

	public Timestamp getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDriverLicense() {
		return this.driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public BigInteger getLid() {
		return this.lid;
	}

	public void setLid(BigInteger lid) {
		this.lid = lid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileSystem() {
		return this.mobileSystem;
	}

	public void setMobileSystem(String mobileSystem) {
		this.mobileSystem = mobileSystem;
	}

	public String getMobileVersion() {
		return this.mobileVersion;
	}

	public void setMobileVersion(String mobileVersion) {
		this.mobileVersion = mobileVersion;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getVip() {
		return this.vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public Set<CallBill> getCshCallBills() {
		return this.cshCallBills;
	}

	public void setCshCallBills(Set<CallBill> cshCallBills) {
		this.cshCallBills = cshCallBills;
	}

	public CallBill addCshCallBill(CallBill cshCallBill) {
		getCshCallBills().add(cshCallBill);
		cshCallBill.setCshUserInfo(this);

		return cshCallBill;
	}

	public CallBill removeCshCallBill(CallBill cshCallBill) {
		getCshCallBills().remove(cshCallBill);
		cshCallBill.setCshUserInfo(null);

		return cshCallBill;
	}

	public Set<DriverLicense> getCshDriverLicenses() {
		return this.cshDriverLicenses;
	}

	public void setCshDriverLicenses(Set<DriverLicense> cshDriverLicenses) {
		this.cshDriverLicenses = cshDriverLicenses;
	}

	public DriverLicense addCshDriverLicens(DriverLicense cshDriverLicens) {
		getCshDriverLicenses().add(cshDriverLicens);
		cshDriverLicens.setCshUserInfo(this);

		return cshDriverLicens;
	}

	public DriverLicense removeCshDriverLicens(DriverLicense cshDriverLicens) {
		getCshDriverLicenses().remove(cshDriverLicens);
		cshDriverLicens.setCshUserInfo(null);

		return cshDriverLicens;
	}

	public Set<Feedback> getCshFeedbacks() {
		return this.cshFeedbacks;
	}

	public void setCshFeedbacks(Set<Feedback> cshFeedbacks) {
		this.cshFeedbacks = cshFeedbacks;
	}

	public Feedback addCshFeedback(Feedback cshFeedback) {
		getCshFeedbacks().add(cshFeedback);
		cshFeedback.setCshUserInfo(this);

		return cshFeedback;
	}

	public Feedback removeCshFeedback(Feedback cshFeedback) {
		getCshFeedbacks().remove(cshFeedback);
		cshFeedback.setCshUserInfo(null);

		return cshFeedback;
	}

	public Set<IdentityCard> getCshIdentityCards() {
		return this.cshIdentityCards;
	}

	public void setCshIdentityCards(Set<IdentityCard> cshIdentityCards) {
		this.cshIdentityCards = cshIdentityCards;
	}

	public IdentityCard addCshIdentityCard(IdentityCard cshIdentityCard) {
		getCshIdentityCards().add(cshIdentityCard);
		cshIdentityCard.setCshUserInfo(this);

		return cshIdentityCard;
	}

	public IdentityCard removeCshIdentityCard(IdentityCard cshIdentityCard) {
		getCshIdentityCards().remove(cshIdentityCard);
		cshIdentityCard.setCshUserInfo(null);

		return cshIdentityCard;
	}

	public Set<UserMessage> getCshUserMessages() {
		return this.cshUserMessages;
	}

	public void setCshUserMessages(Set<UserMessage> cshUserMessages) {
		this.cshUserMessages = cshUserMessages;
	}

	public UserMessage addCshUserMessage(UserMessage cshUserMessage) {
		getCshUserMessages().add(cshUserMessage);
		cshUserMessage.setCshUserInfo(this);

		return cshUserMessage;
	}

	public UserMessage removeCshUserMessage(UserMessage cshUserMessage) {
		getCshUserMessages().remove(cshUserMessage);
		cshUserMessage.setCshUserInfo(null);

		return cshUserMessage;
	}

	public Set<Vehicle> getCshVehicles() {
		return this.cshVehicles;
	}

	public void setCshVehicles(Set<Vehicle> cshVehicles) {
		this.cshVehicles = cshVehicles;
	}

	public Vehicle addCshVehicle(Vehicle cshVehicle) {
		getCshVehicles().add(cshVehicle);
		cshVehicle.setCshUserInfo(this);

		return cshVehicle;
	}

	public Vehicle removeCshVehicle(Vehicle cshVehicle) {
		getCshVehicles().remove(cshVehicle);
		cshVehicle.setCshUserInfo(null);

		return cshVehicle;
	}

	public Set<Wallet> getCshWallets() {
		return this.cshWallets;
	}

	public void setCshWallets(Set<Wallet> cshWallets) {
		this.cshWallets = cshWallets;
	}

	public Wallet addCshWallet(Wallet cshWallet) {
		getCshWallets().add(cshWallet);
		cshWallet.setCshUserInfo(this);

		return cshWallet;
	}

	public Wallet removeCshWallet(Wallet cshWallet) {
		getCshWallets().remove(cshWallet);
		cshWallet.setCshUserInfo(null);

		return cshWallet;
	}

}