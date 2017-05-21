package org.zqrc.tmhs.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zqrc.tmhs.bean.bills;
import org.zqrc.tmhs.dbo.DBO;

/**
 *单据操作 
 * @project TMHS
 * @DescList org.zqrc.tmhs.service
 * @author 李志飞
 *
 * @Date 2016-10-2
 * @UpDate 2016
 */
public class BillControl {
	private DBO db=new DBO();
	
	/**
	 * 获取所有单据，不建议使用
	 * @return
	 */
	public ArrayList<bills> getBillAll(){
		ArrayList<bills> c=new ArrayList<bills>();
		ResultSet rs=db.getRs("select * from bills");
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String case_id=rs.getString("case_id");
				String money=rs.getString("money");
				String other=rs.getString("other");
				c.add(new bills(Integer.parseInt(id),case_id,Integer.parseInt(money),other));
			}
			if(c.size()>0){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.closed();
		}
	}
	
	/**
	 * 获取指定id的总金额
	 * @return
	 */
	public double getMoneyById(String ids){
		double money=0;
		ArrayList<bills>b=getBillsById(ids);
		for (bills bill : b) {
			money+=bill.getMoneys();
		}
		return money;
	}
	
	/**
	 * 获取指定ID号的病例的单据
	 * @param ids
	 * @return
	 */
	public ArrayList<bills> getBillsById(String ids){
		ArrayList<bills> c=new ArrayList<bills>();
		ResultSet rs=db.getRs("select * from bills where case_is ="+ids);
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String case_id=rs.getString("case_id");
				String money=rs.getString("money");
				String other=rs.getString("other");
				c.add(new bills(Integer.parseInt(id),case_id,Integer.parseInt(money),other));
			}
			if(c.size()>0){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.closed();
		}
	}
	
	/**
	 * 获取指定ID号的病例的指定num单据
	 * @param ids
	 * @return
	 */
	public bills getBillById(String ids,String num){
		bills c=null;
		ResultSet rs=db.getRs("select * from bills where case_is ="+ids+" and id='"+num+"'");
		try {
			while(rs.next()){
				String id=rs.getString("id");
				String case_id=rs.getString("case_id");
				String money=rs.getString("money");
				String other=rs.getString("other");
				c=new bills(Integer.parseInt(id),case_id,Integer.parseInt(money),other);
			}
			if(c!=null){
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.closed();
		}
	}
	
	
	/**
	 * 将单据添加到指定ID的病例中
	 * @param ids
	 * @param b
	 * @return
	 */
	public boolean add(String ids,bills b){
		int num=getBillsById(ids).size()+1;
		if(db.update("insert into bills(id,case_id,money,other)values('"+num+"','"+ids+"','"+b.getMoneys()+"','"+b.getOther()+"')")==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 将指定ids病例的编号为num的单据修改为b
	 * 修改成功返回true
	 * @param ids
	 * @param num
	 * @return
	 */
	public boolean changeById(String ids,String num,bills b){
		
		if(db.update("update bills(money,other)values('"+b.getMoneys()+"','"+b.getOther()+"') where case_id ='"+ids+"' and id ='"+num+"'")==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除指定ID病例的指定id单据
	 */
	public boolean delByID(String ids,String id){
		if(db.update("delete from bills where case_id='"+ids+"' and id ='"+id+"'")==1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除指定ID病例的所有单据
	 */
	public boolean delAll(String ids){
		if(db.update("delete from bills where case_id='"+ids+"'")>=0){
			return true;
		}else{
			return false;
		}
	}
	
}
