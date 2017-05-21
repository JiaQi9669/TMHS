package org.zqrc.tmhs.bean;

/**
 * 单据，用来存放消费金额
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.bean
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public class bills {
	private int id;//单据号
	private String case_id;//所属病例编号
	private double moneys;//消费金额
	private String other;//备注信息

	public bills() {
		super();
	}
	
	public bills(int id, String case_id, double moneys,String other) {
		super();
		this.id = id;
		this.case_id = case_id;
		this.moneys = moneys;
		this.other=other;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	public double getMoneys() {
		return moneys;
	}
	public void setMoneys(int moneys) {
		this.moneys = moneys;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
}
