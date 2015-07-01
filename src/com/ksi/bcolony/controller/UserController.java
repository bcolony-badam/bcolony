package com.ksi.bcolony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksi.bcolony.dao.impl.Usr;
import com.ksi.bcolony.util.SessionData;

@RestController
@RequestMapping("/usr")
public class UserController {

	@Autowired
	SessionData session;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean authenicate(@RequestBody Usr usr) {

		if ("admin".equals(usr.getUsrId()) && "admin".equals(usr.getPassword())) {
			System.out.println("customerId:"+session.getLoginId());
			session.setLoginId(usr.getUsrId());
			session.setCustomerId(110);
			return true;
		}
		return false;
	}
}
