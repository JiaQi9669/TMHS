package org.zqrc.tmhs.dbo;

import java.io.File;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBO {
	private String URL;
	private String classname;
	
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	
	private String dbdir;//数据路径
	private String dbfile;//数据文件名
	private File backfile;//备份文件路径
	
	/**
	 * 默认构造
	 */
	public DBO(){
		classname="org.sqlite.JDBC";
		dbfile="tmhs.db";
		dbdir="D:"+File.separator+"TMHSystem_城乡医疗救助"+File.separator+"data"+File.separator+dbfile;
        URL = "jdbc:sqlite:"+dbdir;
        backfile=new File("D:"+File.separator+"TMHSystem"+File.separator+"backup");
        checkDir(dbdir);
	}
	
	/**
	 * 检测路径是否存在不存在，则创建
	 * @param file
	 */
	private void checkDir(String file){
		File f=new File(file);
		if  (!f.getParentFile().exists()  && !f.getParentFile().isDirectory()){
		    f.getParentFile().mkdirs();
		    if(!backfile.exists()){
		    	new File(backfile.getPath()+File.separator+"excels").mkdirs();
		    	new File(backfile.getPath()+File.separator+"docs").mkdirs();
		    }
		    init();
		}else{
			if(!backfile.exists()){
		    	new File(backfile.getPath()+File.separator+"excels").mkdirs();
		    	new File(backfile.getPath()+File.separator+"docs").mkdirs();
		    }
			init();
		}
	}
	
	/**
	 * 初始化数据表
	 * user表属性：id、name、pass、role、
	 * cases表属性：id、name、sex、age、towns、helptype、moneytype、date、byname
	 * bills表属性：id、case_id、money
	 */
	private void init(){
		createDBTable("CREATE TABLE if not exists cases" +
				"(id integer primary key, name text not null,sex text not null,age text not null,idcard text not null,towns text not null," +
				"helptype text not null,moneytype text not null,scale text not null,date text not null,byname text not null)");
		createDBTable("CREATE TABLE if not exists bills" +
				"(id integer,case_id text not null,money integer not null,other text)");
		createDBTable("CREATE TABLE if not exists user" +
				"(id integer primary key,name text not null unique,pass text not null,role text not null)");
	}
	
	/**
	 * 创建数据表
	 * @param sql
	 * as: "CREATE TABLE if not exists test_01(id integer primary key, name char(10))"
	 */
	public void createDBTable(String sql){
		if(sql==null){
			sql="";
		}
		getStm();
		try {
			stm.execute(sql);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "数据表创建失败！");
		}
	}
	
	/**
	 * start driver
	 */
	private void LoadDriver(){
		try{
			Class.forName(classname);
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "加载驱动程序失败！");
		}
	}
	
	/**
	 * get connection
	 * @return
	 */
	private Connection getCon(){
		LoadDriver();
		try{
			con=DriverManager.getConnection(URL);//username,password);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "创建数据库连接失败！");
		}
		return con;
	}
	
	/**
	 * creat connection statement
	 * @return
	 */
	private Statement getStm(){
		getCon();
		try{
			stm=con.createStatement();		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "创建Statement对象失败！");
		}
		return stm;
	}
	
	/**
	 * 数据库查询，需要手动关闭
	 * @param sql
	 * @return
	 */
	public ResultSet getRs(String sql){
		if(sql==null){
			sql="";
		}
		getStm();
		try{
			rs=stm.executeQuery(sql);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "DBO层查询数据库失败！");
		}
		return rs;		
	}
	
	/**
	 * 数据库更新，执行增删改操作，并返回受影响的行数。自动closed
	 * 返回1代表更新成功，-1代表失败
	 * @param sql
	 * @return
	 */
	public int update(String sql){
		if(sql==null){
			sql="";
		}
		getStm();
		int i=-1;
		try{
			i=stm.executeUpdate(sql);
		}catch(Exception e){
			i=-1;
		}finally{
			closed();
		}
		return i;
	}
	
	/**
	 * 关闭数据连接
	 */
	public void closed(){
		try{
			if(this.rs!=null)
				rs.close();
			if(this.stm!=null)
				stm.close();
			if(this.con!=null)
				con.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "数据流关闭异常！");
		}
	}
	
}
