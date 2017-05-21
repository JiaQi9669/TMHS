package org.zqrc.tmhs.test;

import org.zqrc.tmhs.bean.user;
import org.zqrc.tmhs.service.loginCheck;

public class testlogin {
	public static void main(String[] args) {
		loginCheck l=new loginCheck();
		user u=new user();
		u.setId(1);
		u.setName("走姿");
		u.setPaw("男");
		u.setRole("10");
		System.out.println(l.login(u));
	}
}
