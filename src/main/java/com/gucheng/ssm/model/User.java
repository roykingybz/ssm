package com.gucheng.ssm.model;

public class User {
	/** id */
	private String id;
	/** username */
	private String userName;
	/** password */
	private String passWord;
	private int sex; // �Ա�
	private int age; // ����
	private String profilehead; // ͷ��
	private String profile; // ���
	private String firsttime; // ע��ʱ��
	private String lasttime; // ����¼ʱ��
	private int status; // �˺�״̬(1���� 0����)

	

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProfilehead() {
		return profilehead;
	}

	public void setProfilehead(String profilehead) {
		this.profilehead = profilehead;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
