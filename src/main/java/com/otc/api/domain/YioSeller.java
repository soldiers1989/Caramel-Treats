package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class YioSeller {

	//
	private String ids;
	
	private Integer id;
	//名称
	private String name;
	//二维码图片
	private String url;
	//1:支付宝 2:微信
	private Integer type;
	//用户id
	private Integer userId;
	//
	private Date createAt;
	//
	private String username;
	//
	private String qr;

	private Integer frozen;
	
	private String pid;

	private String appId;

	private String privateKey;

	private String publicKey;

	private Integer face;//1开始 2 停止

	private BigDecimal amount;//充值随机立减金额

	private String payType;

	private String cardNo;

	private Integer bankId;

	private Integer frozen;//1：冻结 2：解冻

	private String dingUser;//钉钉UserId

	private List<YioPant> pants;
	
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Integer getFace() {
		return face;
	}

	public void setFace(Integer face) {
		this.face = face;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getDingUser() {
		return dingUser;
	}

	public void setDingUser(String dingUser) {
		this.dingUser = dingUser;
	}

	public List<YioPant> getPants() {
		return pants;
	}

	public void setPants(List<YioPant> pants) {
		this.pants = pants;
	}
>>>>>>> src/main/java/com/otc/api/domain/YioSeller.java

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getFrozen() {
		return frozen;
	}

	public void setFrozen(Integer frozen) {
		this.frozen = frozen;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setCreateAt(Date createAt){
		this.createAt=createAt;
	}

	public Date getCreateAt(){
		return createAt;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getUsername(){
		return username;
	}

	public void setQr(String qr){
		this.qr=qr;
	}

	public String getQr(){
		return qr;
	}

}
