package org.zqrc.tmhs.bean;

/**
 * 登录用户
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.bean
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public class user {
	private int id;//编号
	private String role;//用户类型
	private String name;//用户名
	private String paw;//用户密码
	
	public user() {
		super();
	}
	public user(int id, String name, String paw, String role) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.paw = paw;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPaw() {
		return paw;
	}
	public void setPaw(String paw) {
		this.paw = paw;
	}
}
