package org.zqrc.tmhs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 生成Excel工具
 * @project TMHS
 * @DescList org.zqrc.tmhs.view
 * @author 李志飞
 *
 * @Date 2016-10-2
 * @UpDate 2016
 */
public class ExcelFactory {
	public static void main(String[] args) {
		createSingleXLS(FilePathDialog.getPath(),new String[]{"2016100613041230412X","城乡医疗救助","2016","10","06","张三","100000","10000","90050","5000","11","120604.02"});
	}
	
	/**
	 * 导出指定id集的
	 * @param ids
	 * @return
	 */
//	public static boolean createAllXLS(String[] ids){
//		
//	}
	
	
	/**
	 * 打印单个凭据
	 * @param path
	 * @param String[] Date【0流水号，1城乡医疗救助，2年，3月，4日，5姓名，6合理费用，7补偿费用，8自付费用，9医疗救治费用，10票号，11金额】
	 * @return
	 */
	public static boolean createSingleXLS(String path,String[] data){//,String[]Date){
		try{
			//打开文件 
			WritableWorkbook book= Workbook.createWorkbook(new File(path+File.separator+"医疗救助信息表_"+data[5]+".xls"));
			//生成名为“第一页”的工作表，参数0表示这是第一页 
			WritableSheet sheet=book.createSheet("个人救助信息",0);
//			WritableSheet sheet1=book.createSheet("个人救助信息1",1);
			
			//一些临时变量，用于写到excel中  
			jxl.write.Label l = null;
			jxl.write.Number n = null;
			jxl.write.DateTime d = null;

			//大标题样式
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 15,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			headerFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE,jxl.format.Colour.BLACK); //BorderLineStyle边框
			
			//表头样式
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE,jxl.format.Colour.BLACK); //BorderLineStyle边框
			
//			其他对其方式
			WritableFont tempFont = new WritableFont(WritableFont.ARIAL, 11,WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			
			WritableCellFormat tempFormatL= new WritableCellFormat(tempFont);
			tempFormatL.setAlignment(Alignment.LEFT);
			tempFormatL.setVerticalAlignment(VerticalAlignment.CENTRE);
			tempFormatL.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE,jxl.format.Colour.BLACK); //BorderLineStyle边框
			
			WritableCellFormat tempFormatR = new WritableCellFormat(tempFont);
			tempFormatR.setAlignment(Alignment.RIGHT);
			tempFormatR.setVerticalAlignment(VerticalAlignment.CENTRE);
			tempFormatR.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.NONE,jxl.format.Colour.BLACK); //BorderLineStyle边框
			
			//内容样式
			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			
			WritableCellFormat detFormat = new WritableCellFormat(detFont);
			detFormat.setAlignment(Alignment.CENTRE);
			detFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			detFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
			
			//右对其有边框样式
			WritableCellFormat detFormatR = new WritableCellFormat(detFont);
			detFormatR.setAlignment(Alignment.RIGHT);
			detFormatR.setVerticalAlignment(VerticalAlignment.CENTRE);
			detFormatR.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//左对其有边框样式
			WritableCellFormat detFormatL = new WritableCellFormat(detFont);
			detFormatL.setAlignment(Alignment.LEFT);
			detFormatL.setVerticalAlignment(VerticalAlignment.CENTRE);
			detFormatL.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			
			//各种格式
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00000"); //用于Number的格式  
			WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

			DateFormat df = new DateFormat("yyyy-MM-dd");//用于日期的  
			WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);
			
			dateFormat.setAlignment(Alignment.CENTRE);
			dateFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			dateFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
			
			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0) 
			/**数据载区**/
			
		    // 设置第一行的高度
			sheet.setRowView(0, 400, false);
		    sheet.setRowView(1, 500, false);
		    sheet.setRowView(2, 450, false);
		    sheet.setRowView(3, 480, false);
		    sheet.setRowView(4, 600, false);
		    sheet.setRowView(5, 1000, false);
		    sheet.setRowView(6, 900, false);
		    sheet.setRowView(7, 550, false);
		    
			//设置列宽
			int col=14;
		    sheet.setColumnView(0, col);
		    sheet.setColumnView(1, col);
		    sheet.setColumnView(2, col);
		    sheet.setColumnView(3, col);
		    sheet.setColumnView(4, 16);
		    sheet.setColumnView(5, col);
		    
		    //流水号
			l = new Label(0, 0, "单号:"+data[0], tempFormatR);
			sheet.addCell(l);
			sheet.mergeCells(0, 0, 5, 0);
			
		    //标题
			String[] titles={"郸 城 县 城 乡 医 疗 救 助 办 公 室","医 疗 救 助 申 报 凭 证"};
			l = new Label(0, 1, titles[0], headerFormat);
		    // 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示  
			sheet.addCell(l);//WritableFont bold = new WritableFont(WritableFont.createFont("宋体"),16, WritableFont.BOLD);  
			// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行  
			sheet.mergeCells(0, 1, 5, 1);
			
			l = new Label(0, 2, titles[1], headerFormat);
			sheet.addCell(l);//WritableFont bold = new WritableFont(WritableFont.createFont("宋体"),16, WritableFont.BOLD);  
			// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行  
		    sheet.mergeCells(0, 2, 5, 2);
		    
		    //顶部信息
		    String[] heads={"凭证名称:"+data[1],data[2]+" 年 "+data[3]+" 月 "+data[4]+" 日"};

		    l = new Label(0, 3, heads[0], tempFormatL);
			sheet.addCell(l);
			sheet.mergeCells(0, 3, 3, 3);
			
			l = new Label(4, 3, heads[1], tempFormatR);
			sheet.addCell(l);
			sheet.mergeCells(4,3, 5, 3);
			
			//内容区
			String[] body1={"内        容","合理费用","补偿费用","自付费用","医疗救助费用","票	      号"};
			String[] body2={data[5],data[6],data[7],data[8],data[9],data[10]};
			for(int i=0;i<body1.length;i++){
				l = new Label(i,4,body1[i], detFormat);
//				sheet.setColumnView(0, 6);
				sheet.addCell(l);
			}
			for(int i=0;i<body2.length;i++){
				l = new Label(i,5,body2[i], detFormat);
//				sheet.setColumnView(0, 6);
				sheet.addCell(l);
			}
			//金额填区
			String[]moneystr={"大写(金额)","         小写(金额):"+data[11]+"   "};
			l = new Label(0,6,moneystr[0], detFormat);
//			sheet.setColumnView(0, 6);
			sheet.addCell(l);
			
			l = new Label(1,6,RMBTransForm.toUP(data[11])+"      "+moneystr[1], detFormatR);
			sheet.addCell(l);
			sheet.mergeCells(1, 6, 5, 6);
			
			//底部显示
			String[]ends={"原始凭证存放单位签章:","经办人：            发款人：             审批：            "};
			
			l = new Label(0,7,ends[0], tempFormatL);
			sheet.addCell(l);
			sheet.mergeCells(0, 7, 1, 7);
			
			l = new Label(2,7,ends[1], tempFormatR);
			sheet.addCell(l);
			sheet.mergeCells(2, 7, 5, 7);
			
			
			//写入数据并关闭文件 
			book.write();
			book.close();
			
//		}catch(Exception e){
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "文件正被使用！");
//			return false;
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "打印已取消");
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "打印异常！");
			return false;
		} catch (WriteException e) {
			JOptionPane.showMessageDialog(null, "写入异常！");
			return false;
		}
		return true;
	}
	
	/**
	 * 
	
	// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示  
    WritableFont bold = new WritableFont(WritableFont.createFont("宋体"),16, WritableFont.BOLD);  
    // 生成一个单元格样式控制对象  
    WritableCellFormat titleFormate = new WritableCellFormat(bold);  
    // 单元格中的内容水平方向居中  
    titleFormate.setAlignment(jxl.format.Alignment.CENTRE);  
    // 单元格的内容垂直方向居中  
    titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);  
    Label title = new Label(0, 0, "中国法律图书有限公司订单", titleFormate);  
    sheet.addCell(title);  
	 
	l = new Label(column++, i + 3, "标题   " + i, detFormat);
	sheet.addCell(l);
	d = new DateTime(column++, i + 3, new java.util.Date(), dateFormat);
	sheet.addCell(d);
	l = new Label(column++, i + 3, "SGD", detFormat);
	sheet.addCell(l);
	n = new jxl.write.Number(column++, i + 3, 98832, priceFormat);
	sheet.addCell(n);

	设置行高
	sheet.setColumnView(column++, 20);
	 */
	
}
