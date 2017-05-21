package org.zqrc.tmhs.view.login;

import org.zqrc.tmhs.bean.user;
import org.zqrc.tmhs.service.loginCheck;

/*
 * By Gorden @ 2016-10-2
 * 此类用于和逻辑层代码进行沟通
 */
public class Login {
	private String userName,passWord,name,role;
	private Boolean b=false;
	public Login(String userName,String passWord){
		this.userName=userName;
		this.passWord=passWord;
	}
	public String getName(){
		return name;
	}
	public String getRole(){
		return role;
	}
	public void auth(){
		/*
		 * 在此处进行通信
		 */
		loginCheck lc=new loginCheck();
		user u=lc.login(new user(Integer.parseInt(userName), name, passWord, role));
		if(u!=null){
			userName=String.valueOf(u.getId());
			name=u.getName();
			role=u.getRole();
			b=true;
		}
	}
	public Boolean isTure(){
		auth();
		return b;
	}
}
