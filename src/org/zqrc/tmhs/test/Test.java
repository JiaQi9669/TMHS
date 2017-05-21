package org.zqrc.tmhs.test;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.zqrc.tmhs.dbo.DBO;
import org.zqrc.tmhs.view.login.LoginGUI;

/**
 * 测试数据
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.util
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public class Test extends JFrame{
	/**
	 * 初始化数据表
	 * cases表属性：id、name、sex、age、towns、helptype、moneytype、date、byname
	 * bills表属性：id、case_id、money
	 */
	
	public static void main(String[] args) {
		DBO db=new DBO();
		db.update("insert into user(id,name,pass,role)values('"+1234+"','管理员','"+1234+"','"+0+"')");
		db.update("insert into user(id,name,pass,role)values('"+4567+"','操作员','"+4567+"','"+1+"')");
		
		new LoginGUI();
//		ResultSet rss=db.getRs("SELECT * FROM user");//查询功能
//		try {
//			while (rss.next()){
//			    String id = rss.getString("id");  
//			    String name = rss.getString("name");  
//			    System.out.println("id is " + id + " name is " + name);  
//			}
//			rss.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
		
	/**
	 * public static void main(String[] args) {
		int i=50;
		DBO dbo=new DBO();
		ResultSet rss=dbo.getRs("SELECT * FROM test_01");//查询功能
		try {
			while (rss.next()){
			    String id = rss.getString("id");  
			    String name = rss.getString("name");  
			    System.out.println("id is " + id + " name is " + name);  
			}
			rss.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("=================================");
		dbo.update("INSERT INTO test_01(id, name) VALUES("+ i++ +", '1')");
		rss=dbo.getRs("SELECT * FROM test");//查询功能
		try {
			while(rss.next()){
			    String id = rss.getString("id");  
			    String name = rss.getString("name");  
			    System.out.println("id is " + id + " name is " + name);  
			}
			rss.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("=================================");
		dbo.update("delete from test where id='"+50+"'");//删除
		rss=dbo.getRs("SELECT * FROM test");
		try {
			while(rss.next()){
			    String id = rss.getString("id");  
			    String name = rss.getString("name");  
			    System.out.println("id is " + id + " name is " + name);  
			}
			rss.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("=================================");
		dbo.update("update test set name='"+"狗子"+"' where id = '"+"100"+"'");
		
		rss=dbo.getRs("SELECT * FROM test");
		try {
			while(rss.next()){
			    String id = rss.getString("id");  
			    String name = rss.getString("name");  
			    System.out.println("id is " + id + " name is " + name);  
			}
			rss.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 */
	
	
}
