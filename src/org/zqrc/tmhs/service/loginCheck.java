package org.zqrc.tmhs.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.zqrc.tmhs.bean.user;
import org.zqrc.tmhs.dbo.DBO;

/**
 * 检查登录
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.service
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public class loginCheck {
	private DBO db=new DBO();
	/**
	 * 登录检测
	 * 登录用户信息正确返回true
	 * 否则返回false
	 * @param u
	 * @return
	 */
	public user login(user u){
		ResultSet rs=db.getRs("select * from user where id ="+u.getId());
		user temp=null;
		try {
			while (rs.next()){
			    String id = rs.getString("id");
			    String name = rs.getString("name");
			    String pass=rs.getString("pass");
			    String role=rs.getString("role");
			    temp=new user(Integer.parseInt(id), name, pass,role);
			    System.out.println("id=" + id + "，name=" + name+",pass="+pass+",role="+role);
			}
			if(temp!=null){
				if(temp.getId()==u.getId()&&temp.getPaw().equals(u.getPaw())){
					return temp;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.closed();
		}
	}
}
