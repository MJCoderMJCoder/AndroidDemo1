package com.lzf.tempobj;

/**
 * acount_left�����µ���ʱǰ��bean��
 */
public class AccountLeft {
	private int portrait;
	private String account;

	public int getPortrait() {
		return portrait;
	}

	public void setPortrait(int portrait) {
		this.portrait = portrait;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public AccountLeft() {
		super();
	}

	public AccountLeft(int portrait, String account) {
		super();
		this.portrait = portrait;
		this.account = account;
	}

}
