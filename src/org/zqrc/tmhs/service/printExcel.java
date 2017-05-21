package org.zqrc.tmhs.service;

import java.util.ArrayList;

import org.zqrc.tmhs.bean.cases;

/**
 *调用导出excel功能
 * @project TMHS
 * @DescList org.zqrc.tmhs.service
 * @author 李志飞
 *
 * @Date 2016-10-2
 * @UpDate 2016
 */
public class printExcel {
	
	/**
	 * 备份所有数据到默认路径
	 * 备份成功返回true
	 * @return
	 */
	public boolean updateAll(ArrayList<cases> c){
		
		return true;
	}
	
	
	
	/**
	 * 导出指定的单表到指定路径
	 * @return
	 */
	public boolean ImportExcelSignls(ArrayList<cases> c,String path){
		return true;
	}
	
	/**
	 * 导出指定病例数据到指定路径
	 * @return
	 */
	public boolean ImportExcelSignl(String ids,String path){
		return true;
	}
	
}
