package org.zqrc.tmhs.view.main.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Printable;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.tools.JavaCompiler;

import org.zqrc.tmhs.view.method.*;

/*
 * By Gorden @ 2016-10-3
 * 此类用于生成帐单录入页面
 */
public class InputBill extends JPanel implements ActionListener,MouseListener,FocusListener,KeyListener{
	
	private JComboBox xzjd_cb,cblx_cb,jzlx_cb,jzbl_cb;
	private JTextField name_tf,IDCard_tf,delRow_tf;
	private JTable table;
	private JButton addRow_btn,print_btn,delRow_btn;
	private DefaultTableModel model;
	private int row=1;
	private Object colname[]={"序号","费用合计（参与补偿费用、合理费用）","医保记帐（补偿费用）","自费费用","大病保险(大额、大额记帐）"};
	private JLabel money1_l,money2_l;
	private JPopupMenu jpm;
	private JScrollPane sp;
	private JMenuItem[] mi;
	private double money1=0.0;
	private double money2=0.0;
	private int message=0;
	private int message1=0;
	private double money=0;
	private InputBillGetData getData;
	private double pay1=0;
	private double pay2=0;
	private double pay3=0;
	private double pay4=0;
	private String userName;
	public InputBill(String userName){
		super();
		this.setBackground(null);
		this.userName=userName;
		this.setLayout(new BorderLayout());
		this.add(setInfoPanel(),BorderLayout.NORTH);
		this.add(setTablePanel(),BorderLayout.CENTER);
		this.add(setResultPanel(),BorderLayout.SOUTH);
	}
	/*
	 * 用以生成人员信息面板
	 */
	private JPanel setInfoPanel(){
		JPanel infoJPanel=new JPanel();
		infoJPanel.setLayout(new FlowLayout());
		infoJPanel.setBackground(null);
		infoJPanel.setPreferredSize(new Dimension(700,85));
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.setBackground(null);
		
		JLabel l1=new JLabel("乡镇/街道:");
		l1.setFont(new Font("微软雅黑",0,16));
		p1.add(l1);
		
		String[] xzjd={"洛北街道","新城街道","吴台街道","南丰镇","白马镇","宁平镇","宜路镇","钱店镇","汲冢镇","城郊乡","虎岗乡","汲水乡","张完乡","丁村乡","双楼乡","秋渠乡","东风乡","石槽镇","巴集乡","李楼乡","胡集乡"};
		xzjd_cb=new JComboBox(xzjd);
		setCB(xzjd_cb);
		p1.add(xzjd_cb);
		
		JLabel l2=new JLabel("参保类型:");
		l2.setFont(new Font("微软雅黑",0,16));
		p1.add(l2);
		
		String cblx[]={"农合","医保","未参保(合)"};
		cblx_cb=new JComboBox(cblx);
		setCB(cblx_cb);
		p1.add(cblx_cb);
		
		JLabel l3=new JLabel("救助类型:");
		l3.setFont(new Font("微软雅黑",0,16));
		p1.add(l3);
		
		String jzlx[]={"五保","低保","因病致贫"};
		jzlx_cb=new JComboBox(jzlx);
		setCB(jzlx_cb);
		jzlx_cb.addActionListener(this);
		p1.add(jzlx_cb);
		
		JLabel bl=new JLabel("救助比例");
		bl.setFont(new Font("微软雅黑",0,16));
		p1.add(bl);
		
		String jzbl[]={"80%","90%","100%"};
		jzbl_cb=new JComboBox(jzbl);
		jzbl_cb.addActionListener(this);
		setCB(jzbl_cb);
		p1.add(jzbl_cb);
		
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.setBackground(null);
		
		JLabel l4=new JLabel("姓名:");
		l4.setFont(new Font("微软雅黑",0,16));
		p2.add(l4);
		
		name_tf=new JTextField(6);
		name_tf.setBackground(Color.white);
		name_tf.setSize(100,24);
		name_tf.setFont(new Font("微软雅黑",0,18));
		p2.add(name_tf);
		
		JLabel l5=new JLabel("身份证号:");
		l5.setFont(new Font("微软雅黑",0,16));
		p2.add(l5);
		
		IDCard_tf=new JTextField(12);
		IDCard_tf.setBackground(Color.white);
		IDCard_tf.setSize(100,24);
		IDCard_tf.setFont(new Font("微软雅黑",0,18));
		IDCard_tf.addFocusListener(this);
		IDCard_tf.addMouseListener(this);
		p2.add(IDCard_tf);
		
		JLabel l6=new JLabel("           ");
		l6.setFont(new Font("微软雅黑",0,16));
		p2.add(l6);
		
		print_btn=new JButton("保存单据");
		print_btn.setBackground(new Color(255,153,204));
		print_btn.setFont(new Font("微软雅黑",1,12));
		print_btn.setForeground(Color.white);
		print_btn.addActionListener(this);
		p2.add(print_btn);
		
		infoJPanel.add(p1);
		infoJPanel.add(p2);
		return infoJPanel;
	}
	/*
	 * 用以设置下拉选项类型
	 */
	private void setCB(JComboBox cb){
		cb.setSize(80,24);
		cb.setFont(new Font("微软雅黑",0,14));
		cb.setBackground(Color.white);
	}
	/*
	 * 用以生成右键菜单
	 */
	private JPopupMenu setPopuMenu(){
		jpm=new JPopupMenu();
		JMenuItem[] mi={new JMenuItem("添加票据"),new JMenuItem("删除选定票据"),new JMenuItem("打印票据")};
		this.mi=mi;
		for(int i=0;i<3;i++){
			jpm.add(mi[i]);
			mi[i].setFont(new Font("微软雅黑",0,14));
			mi[i].setBackground(new Color(255,250,250));
			mi[i].addActionListener(this);
		}
		return jpm;
	}
	/*
	 * 用以生成表格部分
	 */
	private JScrollPane setTablePanel(){
		
		JPanel tablePanel=new JPanel();
		tablePanel.setBackground(new Color(255,250,250));
		tablePanel.setLayout(new FlowLayout());
		
		String [][] data=new String[1][5];
		data[0][0]="票据1";
		model=new DefaultTableModel(data,colname);
		table=new JTable(model);
		table.setDefaultRenderer(Object.class,new TableStyle());
		table.getTableHeader().setFont(new Font("微软雅黑",0,14));
		table.setSelectionBackground(new Color(255,250,250));
		table.getTableHeader().setBackground(new Color(255,231,255));
		table.setFont(new Font("微软雅黑",0,14));
		table.setRowHeight(25);
		table.setBackground(Color.white);
		table.addMouseListener(this);
		table.setEnabled(false);
		table.addKeyListener(this);
		tablePanel.add(table);
		
		sp=new JScrollPane(table);
		sp.setBorder(null);
		sp.addMouseListener(this);
		return sp;
	}
	/*
	 * 返回表格中的数据
	 */
	private String[][] getValue(){
		String data[][]=new String[row][5];
		for(int i=0;i<row;i++){
			for(int j=0;j<5;j++){
				if(j==0)
					data[i][j]="票据"+(i+1);
				else
					data[i][j]=(String)table.getValueAt(i,j);
			}
		}
		return data;
	}
	/*
	 * 添加票据
	 */
	private void addRow(){
		model.addRow(new Vector());
		row++;
		table.setValueAt("票据"+row, row-1, 0);
		setMoney();
	}
	/*
	 * 删除票据
	 */
	private void delRow(int r){
		model.removeRow(r-1);
		row--;
		String s[][]=getValue();
		model.setDataVector(s,colname);
		
		setMoney();
	}
	/*
	 * 计算结果显示及增加删除行数
	 */
	private JPanel setResultPanel(){
		JPanel resultPanel=new JPanel();
		resultPanel.setPreferredSize(new Dimension(700,40));
		resultPanel.setBackground(null);
		resultPanel.setLayout(new FlowLayout());
		
		JLabel l1=new JLabel("合理支出："); 
		l1.setFont(new Font("微软雅黑",0,16));
		resultPanel.add(l1);
		
		money1_l=new JLabel("￥"+money1);
		money1_l.setForeground(Color.red);
		money1_l.setFont(new Font("微软雅黑",1,16));
		resultPanel.add(money1_l);
		
		JLabel l3=new JLabel("救助金额："); 
		l3.setFont(new Font("微软雅黑",0,16));
		resultPanel.add(l3);
		
		money2_l=new JLabel("￥"+money2);
		money2_l.setForeground(Color.red);
		money2_l.setFont(new Font("微软雅黑",1,16));
		resultPanel.add(money2_l);
		
		addRow_btn=new JButton("添加票据");
		addRow_btn.setBackground(new Color(255,153,204));
		addRow_btn.setFont(new Font("微软雅黑",1,12));
		addRow_btn.setForeground(Color.white);
		addRow_btn.addActionListener(this);
		resultPanel.add(addRow_btn);
		
		JLabel l5=new JLabel("删除第"); 
		l5.setFont(new Font("微软雅黑",0,14));
		resultPanel.add(l5);
		
		delRow_tf=new JTextField(2);
		delRow_tf.setFont(new Font("微软雅黑",0,14));
		resultPanel.add(delRow_tf);
		
		JLabel l6=new JLabel("行"); 
		l6.setFont(new Font("微软雅黑",0,16));
		resultPanel.add(l6);
		
		delRow_btn=new JButton("删除票据");
		delRow_btn.setBackground(new Color(255,153,204));
		delRow_btn.setFont(new Font("微软雅黑",1,12));
		delRow_btn.setForeground(Color.white);
		delRow_btn.addActionListener(this);
		resultPanel.add(delRow_btn);
		
		return resultPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addRow_btn){
			addRow();
		}
		if(e.getSource()==delRow_btn){
			int r=0;
			try{
				r=Integer.parseInt(delRow_tf.getText());
			}catch(Exception ex){
				System.err.println(ex);
				ex.printStackTrace();
			}
			if(0<r&&r<=row){
				delRow(r);
				delRow_tf.setText("");
			}else{
				JOptionPane.showMessageDialog(this, "您输入值不合法","系统提示",JOptionPane.WARNING_MESSAGE);
				delRow_tf.setText("");
			}
		}
		try{
			if(e.getSource()==mi[0]){
				addRow();
			}
			if(e.getSource()==mi[1]){
				int r=table.getSelectedRow()+1;
				if(r>0){
					delRow(r);
				}else{
					JOptionPane.showMessageDialog(this, "您未选定任何行","系统提示",JOptionPane.WARNING_MESSAGE);
				}
			}
			if(e.getSource()==mi[2]){
				getData.printTable(name_tf.getText(),(String)xzjd_cb.getSelectedItem(),(String)cblx_cb.getSelectedItem(),(String)jzlx_cb.getSelectedItem(),(String)jzbl_cb.getSelectedItem(),pay1, pay2, money1, money2, row, userName, getValue());
			}
		}catch(Exception ex){
			
		}
		if(e.getSource()==jzlx_cb){//救助类型
			setMoney();
			String jzlx=(String)jzlx_cb.getSelectedItem();
			if(jzlx.equals("五保")){
				String s[]={"80%","90%","100%"};
				jzbl_cb.setModel(new DefaultComboBoxModel(s));
			}
			if(jzlx.equals("低保")){
				String s[]={"70%","80%","90%","100%"};
				jzbl_cb.setModel(new DefaultComboBoxModel(s));
			}
			if(jzlx.equals("因病致贫")){
				String s[]={"10%","20%","30%","40%","50%"};
				jzbl_cb.setModel(new DefaultComboBoxModel(s));
			}
		}
		if(e.getSource()==jzbl_cb){//救助比例
			setMoney();
		}
		if(e.getSource()==print_btn){
				try{
					getData.printTable(name_tf.getText(),(String)xzjd_cb.getSelectedItem(),(String)cblx_cb.getSelectedItem(),(String)jzlx_cb.getSelectedItem(),(String)jzbl_cb.getSelectedItem(),pay1, pay2, money1, money2, row, userName, getValue());
				}catch(NullPointerException e1){
					JOptionPane.showMessageDialog(null, "请输入有效的数据！");
				}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int mods=e.getModifiers();
		if(e.getSource()==table){
			if((mods&InputEvent.BUTTON1_MASK)!=0){
				IDCard_tf.setFocusable(false);
				setMoney();
			}else{
				setPopuMenu();
				jpm.show(e.getComponent(),e.getX(),e.getY());
			}
		}
		if(e.getSource()==sp){
			if((mods&InputEvent.BUTTON1_MASK)!=0){
				IDCard_tf.setFocusable(false);
				setMoney();
			}else{
				setPopuMenu();
				jpm.show(e.getComponent(),e.getX(),e.getY());
			}
		}
		if(e.getSource()==IDCard_tf){
			IDCard_tf.setFocusable(true);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==IDCard_tf){
			String IDCardNum=IDCard_tf.getText();
			if(!(IDCardNum.equals(""))){
				if(!(IDCard.IDCardValidate(IDCardNum).equals("YES"))){
					JOptionPane.showMessageDialog(this, "您输入的身份证号不合法","系统提示",JOptionPane.WARNING_MESSAGE);
					table.setEnabled(false);
					message1=0;
				}else{
					getData=new InputBillGetData(IDCardNum);
					money=getData.getMoney();
					if((money!=0&&(!((String)jzlx_cb.getSelectedItem()).equals("五保")))||((((String)jzlx_cb.getSelectedItem()).equals("五保"))&&(money>=10000))){
						JOptionPane.showMessageDialog(this, "该人员不符合救助条件！","系统提示",JOptionPane.WARNING_MESSAGE);
						table.setEnabled(false);
						message1=0;
					}else{
						table.setEnabled(true);
						message1=1;
					}
				}
			}
		}
	}
	private void setMoney(){
		hlzc();
		jzje();
		DecimalFormat df=new DecimalFormat("#.00");
		
		money1_l.setText("￥"+Double.parseDouble(df.format(money1)));
		money2_l.setText("￥"+Double.parseDouble(df.format(money2)));
		
		if(message!=0){
			money1_l.setText("ERROR");
			money2_l.setText("ERROR");
			money1=-1;
			money2=-1;
		}
	}
	/*
	 * 计算合理支出
	 */
	private void hlzc(){
		message=0;
		String[][]s=getValue();
		pay1=getColSum(1, s);
		pay2=getColSum(2, s);
		pay3=getColSum(3, s);
		pay4=getColSum(4, s);
		if(pay1>=pay2+pay3+pay4){
			money1=pay1-pay2-pay3-pay4;
		}else{
			message=1;
		}
		checkRow(s);
	}
	/*
	 * 计算表格每列的和
	 */
	private double getColSum(int r,String s[][]){
		int sum=0;
		for(int i=0;i<row;i++){
			double m=0;
			String str=s[i][r];
			if(null==str||"".equals(str)){
				str="0";
			}
			try{
				m=Double.parseDouble(str);
			}catch(Exception e){
				message=1;
			}
			if(m<0){
				message=1;
			}
			sum+=m;
		}
		return sum;
	}
	/*
	 * 检查每行
	 */
	private void checkRow(String[][] s){
		for(int i=0;i<row;i++){
			double m1,m2,m3,m4;
			m1=Double.parseDouble(changeNullZero(s[i][1]));
			m2=Double.parseDouble(changeNullZero(s[i][2]));
			m3=Double.parseDouble(changeNullZero(s[i][3]));
			m4=Double.parseDouble(changeNullZero(s[i][4]));
			if(m1<m2+m3+m4){
				message=1;
			}
		}
	}
	private String changeNullZero(String st){
		String str="";
		if(null==st||"".equals(st)){
			str="0";
		}
		else{
			str=st;
		}
		return str;
	}
	/*
	 * 计算救助金额
	 */
	private void jzje(){
		double bl=0;
		String st=(String)jzbl_cb.getSelectedItem();
		if(st.equals("10%")){
			bl=0.1;
		}
		if(st.equals("20%")){
			bl=0.2;
		}
		if(st.equals("30%")){
			bl=0.3;
		}
		if(st.equals("40%")){
			bl=0.4;
		}
		if(st.equals("50%")){
			bl=0.5;
		}
		if(st.equals("60%")){
			bl=0.6;
		}
		if(st.equals("70%")){
			bl=0.7;
		}
		if(st.equals("80%")){
			bl=0.8;
		}
		if(st.equals("90%")){
			bl=0.9;
		}
		if(st.equals("100%")){
			bl=1.0;
		}
		String str=(String)jzlx_cb.getSelectedItem();
		money2=money1*bl;
		if(money2>10000){
			money2=10000;
		}
		if(str.equals("因病致贫")&&money2>5000){
			money2=5000;
		}
		if(str.equals("五保")&&money2>(10000-money)){
			money2=10000-money;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		if(e.getKeyCode()==10||e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_TAB){
			setMoney();
		}
	}
}
/*
 * 用以使表格居中显示
 */
class TableStyle extends DefaultTableCellRenderer{
	public TableStyle(){
		setHorizontalAlignment(CENTER);
	}
}