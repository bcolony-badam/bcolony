package com.ksi.bcolony.util;

import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable {

	private static final long serialVersionUID = -467165889557030656L;
	private int customerId;
	private String loginId;
	private String loginUsrFirstName;
	private String loginUsrLastName;
	private String loginUsrMiddleName;
	private int currRoleId;
	private String currRoleName;
	private int defaultRoleId;
	private Map<Integer, String> rolemap;

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

	public String getLoginUsrFirstName() {
		return loginUsrFirstName;
	}

	public void setLoginUsrFirstName(String loginUsrFirstName) {
		this.loginUsrFirstName = loginUsrFirstName;
	}

	public String getLoginUsrLastName() {
		return loginUsrLastName;
	}

	public void setLoginUsrLastName(String loginUsrLastName) {
		this.loginUsrLastName = loginUsrLastName;
	}

	public String getLoginUsrMiddleName() {
		return loginUsrMiddleName;
	}

	public void setLoginUsrMiddleName(String loginUsrMiddleName) {
		this.loginUsrMiddleName = loginUsrMiddleName;
	}

	public int getCurrRoleId() {
		return currRoleId;
	}

	public void setCurrRoleId(int currRoleId) {
		this.currRoleId = currRoleId;
	}

	public int getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(int defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getCurrRoleName() {
		return currRoleName;
	}

	public void setCurrRoleName(String currRoleName) {
		this.currRoleName = currRoleName;
	}

	public Map<Integer, String> getRolemap() {
		return rolemap;
	}

	public void setRolemap(Map<Integer, String> rolemap) {
		this.rolemap = rolemap;
	}

}
