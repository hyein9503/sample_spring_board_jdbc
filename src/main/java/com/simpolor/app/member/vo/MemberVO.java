package com.simpolor.app.member.vo;

public class MemberVO {
	
	// default information
	private String member_id;		// 회원 아이디
	private String member_pw;		// 회원 비밀번호
	private String member_pw2;		// 회원 비빌먼호 확인
	private String member_pw_org;	// 회원 기존 비밀번호
	private String member_name;		// 회원 이름
	private String email;			// 회원 이메일
	private String reg_date;		// 가입일
	private String mod_date;		// 수정일
	private int level;				// 회원 권한
	
	// additional information
	private String mobile;			// 핸드폰 번호
	private String sex;				// 성별
	private String birthday;		// 생년월일
	private String address;			// 주소
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_pw2() {
		return member_pw2;
	}
	public void setMember_pw2(String member_pw2) {
		this.member_pw2 = member_pw2;
	}
	public String getMember_pw_org() {
		return member_pw_org;
	}
	public void setMember_pw_org(String member_pw_org) {
		this.member_pw_org = member_pw_org;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
