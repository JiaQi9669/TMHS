package org.zqrc.tmhs.interfaces;

import java.util.ArrayList;

import org.zqrc.tmhs.bean.cases;

/**
 * 城乡医疗救助系统接口
 * 实现该接口的对象进行事务处理
 * @project TMHS
 * @apply TODO
 * @Desc 
 * @DescList org.zqrc.tmhs.interfaces
 * @author 李志飞
 *
 * @Date 2016-9-29
 * @UpDate 2016
 */
public interface TmhsInterface {
	
	/**
	 * 获取所有病例（病人）
	 * @return
	 */
	ArrayList<cases> getAll();
	
	/**
	 * 获取该用户名下的所有病例
	 * @param name
	 * @return
	 */
	ArrayList<cases> getByName(String name);
	
	/**
	 * 获取该ID下的病例
	 * @param id
	 * @return
	 */
	cases getById(String id);
	
	/**
	 * 添加病例，添加成功返回true
	 * @param c
	 * @return
	 */
	boolean addCase(cases c);
	
	/**
	 * 修改病例，修改成功返回true
	 * @param c
	 * @return
	 */
	boolean changeCase(cases c);
	
	/**
	 * 删除病例，通过ID，删除成功返回true
	 * @param c
	 * @return
	 */
	boolean delCaseById(int id);
}
