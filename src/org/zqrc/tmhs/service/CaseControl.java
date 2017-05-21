package org.zqrc.tmhs.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.zqrc.tmhs.bean.bills;
import org.zqrc.tmhs.bean.cases;
import org.zqrc.tmhs.dbo.DBO;

/**
 * 病例操作
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.service
 * @author 李志飞
 *
 * @Date 2016-10-1
 * @UpDate 2016
 */
public class CaseControl {
	private DBO db=new DBO();
	private BillControl bc=new BillControl();
	
	/**
	 * 创建病例
	 * 返回true为创建成功，否则为创建失败
	 * @param c
	 * @return
	 */
	public boolean add(cases c){
		if(db.update("insert into cases(id,name,sex,age,idcard,towns,helptype," +
				"moneytype,date,byname)values('"+c.getId()+"','"+
				c.getName()+"','"+c.getSex()+"','"+c.getAge()+"','"+c.getIdcard()+"','"+
				c.getTowns()+"','"+c.getHelptype()+"','"+
				c.getMoneytype()+"','"+c.getDate()+"','"+c.getByname()+"')")==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 检查指定身份证的使用金额
	 * @param idcard
	 * @return
	 */
	public double checkMoneyByIdCard(String idcard){
		double money=0;
		cases c=null;
		ArrayList<bills>b=new ArrayList<bills>();
		
		if((c=findByIDcard(idcard))!=null){
			b=bc.getBillsById(c.getId());
			for (bills bill : b) {
				money+=bill.getMoneys();
			}
			DecimalFormat df = new DecimalFormat("#.00");
			money = Double.parseDouble(df.format(money));
		}
		//省略小数点后两位
		return money;
	}
	/**
	 * 通过id查询病例
	 * cases表属性：id、name、sex、age、towns、helptype、moneytype、date、byname
	 */
	public cases findByIDcard(String ids){
		cases c=null;
		ResultSet rs=db.getRs("select * from cases where idcard ="+ids);
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String age=rs.getString("age");
				String idcard=rs.getString("idcard");
				String towns=rs.getString("towns");
				String helptype=rs.getString("helptype");
				String moneytype=rs.getString("moneytype");
				String date=rs.getString("date");
				String byname=rs.getString("byname");
				c=new cases(id, name, sex, age,idcard,towns, helptype, moneytype, byname, date);
			}
			if(c!=null){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	
	/**
	 * 用对象c替换相应的原信息,
	 * 替换成功返回true
	 * @param c
	 * @return
	 */
	public boolean change(cases c){
		boolean ishave=false;
		ResultSet rs=db.getRs("select * from cases where id ="+c.getId());
		try {
			if(rs.next()){
				ishave=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.closed();
		}
		/**
		 * id,name,sex,age,towns,helptype,moneytype,date,byname
		 */
		if(ishave){
			if(db.update("update cases set " +
					"name='"+c.getName()+"', sex='"+c.getSex()+
					"', age='"+c.getAge()+"', idcard='"+c.getIdcard()+"', towns='"+c.getTowns()+
					"', helptype='"+c.getHelptype()+
					"', moneytype='"+c.getMoneytype()+
					"', date='"+c.getDate()+
					"', byname='"+c.getByname()+"' where id = '"+c.getId()+"'")==1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除指定id病例及所属的单据
	 * 删除成功返回true
	 * @return
	 */
	public boolean del(String id){
		if(db.update("delete from cases where id='"+id+"'")==1){
			if(db.update("delete from bills where case_id='"+id+"'")>=0){//删除单据信息
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * 通过id查询病例
	 * cases表属性：id、name、sex、age、towns、helptype、moneytype、date、byname
	 */
	public cases find(String ids){
		cases c=null;
		ResultSet rs=db.getRs("select * from cases where id ="+ids);
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String age=rs.getString("age");
				String idcard=rs.getString("idcard");
				String towns=rs.getString("towns");
				String helptype=rs.getString("helptype");
				String moneytype=rs.getString("moneytype");
				String date=rs.getString("date");
				String byname=rs.getString("byname");
				c=new cases(id, name, sex, age,idcard,towns, helptype, moneytype, byname, date);
			}
			if(c!=null){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * 查询所有有效的数据
	 * 
	 * @return
	 */
	public ArrayList<cases> findAll(){
		ArrayList<cases> c=new ArrayList<cases>();
		ResultSet rs=db.getRs("select * from cases");
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String age=rs.getString("age");
				String idcard=rs.getString("idcard");
				String towns=rs.getString("towns");
				String helptype=rs.getString("helptype");
				String moneytype=rs.getString("moneytype");
				String date=rs.getString("date");
				String byname=rs.getString("byname");
				c.add(new cases(id, name, sex, age,idcard,towns, helptype, moneytype, byname, date));
			}
			if(c.size()!=0){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}
	
}
