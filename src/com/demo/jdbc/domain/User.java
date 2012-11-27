package com.demo.jdbc.domain;

public class User {

	private int UserId;
	private String Name;
	private String Password;
	private int Coin;
	private String Type;
	private int Consume;

	public User() {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "UserId=" + this.UserId + "Name=" + this.Name + "Password="
				+ this.Password + "Coin=" + this.Coin + "Type=" + this.Type
				+ "Consume=" + this.Consume;
	}
	
	public void showMessage(){
		System.out.println("name:" + this.Name);
	}

	public User(String name) {
		this.Name = name;
	}

	public User(int UserId) {
		this.UserId = UserId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getCoin() {
		return Coin;
	}

	public void setCoin(int coin) {
		Coin = coin;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getConsume() {
		return Consume;
	}

	public void setConsume(int consume) {
		Consume = consume;
	}
}
