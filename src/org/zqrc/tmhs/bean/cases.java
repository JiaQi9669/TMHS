package org.zqrc.tmhs.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * 病例，用来记录病人对象，接受病人单据
 * @project TMHS
 * @DescList org.zqrc.tmhs.bean
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public class cases {
	private String id;//病例编号//20161424
	private String name;//病人姓名
	private String sex;//性别
	private String age;//年龄
	private String idcard;//身份证号
	private String towns;//乡镇
	private String helptype;//参保类型
	private String moneytype;//经济类型
	private String byname;//经办人
	private String date;//日期
	private String money_all;//费用合计
	private String money_help;//救助金额
	
	private ArrayList<bills>bill;//病人单据
	
	public cases() {
		super();
	}
	
	public cases(String id, String name, String sex, String age,String idcard,String towns,
			String helptype, String moneytype, String byname, String date) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.idcard=idcard;
		this.towns = towns;
		this.helptype = helptype;
		this.moneytype = moneytype;
		this.byname = byname;
		this.date = date;
	}

	
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMoney_all() {
		return money_all;
	}

	public void setMoney_all(String money_all) {
		this.money_all = money_all;
	}

	public String getMoney_help() {
		return money_help;
	}

	public void setMoney_help(String money_help) {
		this.money_help = money_help;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<bills> getBill() {
		return bill;
	}
	public void setBill(ArrayList<bills> bill) {
		this.bill = bill;
	}
	/**
	 * 添加单据
	 * @param bill
	 * @return
	 */
	public boolean addBill(bills bill) {
		if(bill!=null&&!this.bill.contains(bill)){
			bill.setId(this.bill.size()+1);
			this.bill.add(bill);
			return true;
		}else{
			return false; 
		}
	}
	
	/**
	 * 删除单据
	 * @param bill
	 * @return
	 */
	public boolean delBill(bills bill){
		if(bill!=null&&this.bill.contains(bill)){
			this.bill.remove(bill);
			return true;
		}else{
			return false; 
		}
	}
	
	/**
	 * 修改单据
	 * @param bill
	 * @return
	 */
	public boolean changeBill(bills bill) {
		if(bill!=null&&this.bill.contains(bill)){
			for (bills b : this.bill) {
				if(b.getId()==bill.getId()){
					b=bill;
				}
			}
			return true;
		}else{
			return false; 
		}
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTowns() {
		return towns;
	}
	public void setTowns(String towns) {
		this.towns = towns;
	}
	public String getHelptype() {
		return helptype;
	}
	public void setHelptype(String helptype) {
		this.helptype = helptype;
	}
	public String getMoneytype() {
		return moneytype;
	}
	public void setMoneytype(String moneytype) {
		this.moneytype = moneytype;
	}
	public String getByname() {
		return byname;
	}
	public void setByname(String byname) {
		this.byname = byname;
	}
}
