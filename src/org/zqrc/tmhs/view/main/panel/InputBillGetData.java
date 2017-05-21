package org.zqrc.tmhs.view.main.panel;

import javax.swing.JOptionPane;

import org.zqrc.tmhs.service.CaseControl;
import org.zqrc.tmhs.util.DateUtil;
import org.zqrc.tmhs.util.ExcelFactory;
import org.zqrc.tmhs.util.FilePathDialog;
import org.zqrc.tmhs.util.KeyUtil;
import org.zqrc.tmhs.view.method.IDCard;

/*
 * By Gorden @ 2016-10-4
 * 此类用以和逻辑层进行通信
 */
public class InputBillGetData {
	private String IDCardNum;
	private String[][] tableValue;
	private double money=0; 
	public InputBillGetData(String IDCardNum){
		this.IDCardNum=IDCardNum;
		this.tableValue=tableValue;
	}
	
	private void setMoney(){
		/*
		 * 获取钱数
		 */
		money=new CaseControl().checkMoneyByIdCard(IDCardNum);
	}
	public double getMoney(){
		return money;
	}
	
	/**
	 * 打印个人申报救助表
	 * @param name
	 * @param sumMoney
	 * @param buMoney
	 * @param payMoney
	 * @param helpMoney
	 * @param row
	 * @param userName
	 * @param tableValue
	 */
	public void printTable(String name,String town,String helpType,String moneyType,String scale,double sumMoney,double buMoney,double payMoney,double helpMoney,int row,String userName,String[][] tableValue){//userName为用户ID
		/*
		 * 打印表格
		 */
		if(sumMoney!=0.0){
			if(JOptionPane.showConfirmDialog(null, "保存成功,是否导出凭据？")==JOptionPane.YES_OPTION){
//				System.out.println("name="+name+",sunmoney="+sumMoney+",bumoney="+buMoney+",paymoney="+payMoney+",helpmoney+"+helpMoney+",row="+row+",usename="+userName);
//				ExcelFactory.createSingleXLS(FilePathDialog.getPath(),new String[]{"2016100613041230412X","城乡医疗救助","2016","10","06","张三","100000","10000","90050","5000","11","120604.02"});
				ExcelFactory.createSingleXLS(FilePathDialog.getPath(),
				new String[]{KeyUtil.getKey(DateUtil.getDateUUID()+userName),
					"城乡医疗救助",
					DateUtil.getDateFormat("yyyy"),
					DateUtil.getDateFormat("MM"),
					DateUtil.getDateFormat("dd"),
					name,
					String.valueOf(sumMoney),
					String.valueOf(buMoney),
					String.valueOf(payMoney),
					String.valueOf(helpMoney),
					String.valueOf(row),
					String.valueOf(helpMoney)});
			}
		}else{
			JOptionPane.showMessageDialog(null, "请输入有效单据");
		}
	}
}
