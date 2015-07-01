package com.ksi.bcolony.util;

import org.springframework.stereotype.Component;

@Component
public class SessionData {

	private int customerId;
	private String loginId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
