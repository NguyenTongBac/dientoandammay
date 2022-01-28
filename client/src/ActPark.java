import java.net.*;
import java.io.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import javax.swing.event.*;
import javax.swing.border.TitledBorder;
import java.util.Date;

public class ActPark extends JPanel {
	private JLabel lb_pos,lb_stg,lb_esp,lb_num,lb_clr,lb_time,lb_info,lb_type;
	private JTextField txt_stg,txt_esp,txt_num,txt_clr,txt_time,txt_info,txt_type;
	private JButton bt_in,bt_out,bt_reset;
	private Choice opt_stg,opt_clr;
	private JScrollPane jsp_info;
	private JTextArea txa_info;
	Socket client;
	DataOutputStream out;
	BufferedReader in;
	String message="",act,position="";

	public  ActPark() {

		setLayout(null);
		
		lb_pos = new JLabel();
		lb_pos.setBounds(new Rectangle(10, 20, 50, 25));
		lb_pos.setText("Vi tri:");
	
		lb_stg = new JLabel();
		lb_stg.setBounds(new Rectangle(70, 20, 50, 25));
		lb_stg.setText("Khu");
	
		lb_esp = new JLabel();
		lb_esp.setBounds(new Rectangle(210, 20, 50, 25));
		lb_esp.setText("lo so");
		
		lb_num = new JLabel();
		lb_num.setBounds(new Rectangle(10, 50, 50, 25));
		lb_num.setText("Bien so");
		
		lb_type = new JLabel();
		lb_type.setBounds(new Rectangle(10, 80, 50, 25));
		lb_type.setText("Hang xe");	
		
		lb_clr = new JLabel();
		lb_clr.setBounds(new Rectangle(210, 80, 50, 25));
		lb_clr.setText("Mau xe");
		
		lb_time = new JLabel();
		lb_time.setBounds(new Rectangle(10, 140, 70, 25));
		lb_time.setText("Thoi gian");
	
		lb_info = new JLabel();
		lb_info.setBounds(new Rectangle(165, 170, 70, 25));
		lb_info.setText("Thong tin");
	
		opt_stg = new Choice();
		opt_stg.setBounds(new Rectangle(110, 20, 90, 30));
		opt_stg.addItem("A");
		opt_stg.addItem("B");
		opt_stg.addItem("C");
		opt_stg.addItem("D");
		opt_stg.addItem("E");
	
		txt_esp = new JTextField();
		txt_esp.setBounds(new Rectangle(250, 20, 120, 25));
	
		txt_num = new JTextField();
		txt_num.setBounds(new Rectangle(120, 50, 160, 25));

		txt_time = new JTextField();
		txt_time.setBounds(new Rectangle(120, 140, 160, 25));
		new Thread(new Runnable(){
				public void run()   {
				while(true){
					try{
						txt_time.setText(getTime());
						Thread.sleep(1000);
					}
					catch(InterruptedException e){
					}
				}	
				}
			}).start();
	
		opt_clr = new Choice();
		opt_clr.setBounds(new Rectangle(210, 110, 170, 25));
		opt_clr.addItem("Khac");
		opt_clr.addItem("Bac");
		opt_clr.addItem("Den");
		opt_clr.addItem("Do");
		opt_clr.addItem("Ghi");
		opt_clr.addItem("Trang");
		opt_clr.addItem("Xanh duong");
		opt_clr.addItem("Xanh luc");
		opt_clr.addItem("Vang");

		txt_type = new JTextField();
		txt_type.setBounds(new Rectangle(10, 110, 170, 25));

		txa_info = new JTextArea();
		jsp_info = new JScrollPane();
		txa_info.setBounds(new Rectangle(10, 200, 370, 165));
		jsp_info.setViewportView(txa_info);
		jsp_info.setBounds(new Rectangle(10, 200, 370, 165));		
		
		bt_in = new JButton("Goi xe");
		bt_in.setBounds(new Rectangle(75, 370, 70, 25));
		bt_in.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			act="SET";
				runClient();
		}	
		});

		bt_out = new JButton("Tra xe");
		bt_out.setBounds(new Rectangle(165, 370, 70, 25));
		bt_out.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			act="DEL";
			runClient();
		}
		});
		
		bt_reset = new JButton("Xoa");
		bt_reset.setBounds(new Rectangle(255, 370, 70, 25));
		bt_reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				txt_esp.setText("");
				txt_num.setText("");
				txt_type.setText("");
				txa_info.setText("");
				opt_clr.select(0);
				opt_stg.select(0);
			}
		});
	
		add(lb_pos);
		add(lb_stg);
		add(lb_esp);
		add(lb_type);
		add(lb_num);
		add(lb_clr);
		add(lb_time);
		add(lb_info);
		add(opt_stg);
		add(txt_esp);
		add(txt_type);
		add(txt_num);
		add(txt_time);
		add(opt_clr);
		add(jsp_info);
		add(bt_in);
		add(bt_out);
		add(bt_reset);

	}
	
	public String getTime(){
		Date now = new Date();
		String time = now.toLocaleString();
		return time;
	}
		
	public String getMessage(){
			String khu = opt_stg.getSelectedItem();
			String lo = txt_esp.getText();
			String bs = txt_num.getText();
			String hieu = txt_type.getText();
			String mau = opt_clr.getSelectedItem();
			String gio = txt_time.getText();			
			String info = khu+lo+"|"+bs+"|"+hieu+"|"+mau+"|"+gio+"|"+act;
			return (info);
	}
	
	public void runClient(){
		message = getMessage();
		if(message.equalsIgnoreCase("")){
			txa_info.append("Loi: Vui long nhap vao thong diep! \n");
			return;
		}
		connect2Server("14.163.200.227",2001);
		shutdown();
	}

	public void connect2Server(String destination,int port){
  		try
  		{
  			client = new Socket(destination,port);
  			in = new BufferedReader(new	InputStreamReader(client.getInputStream()));
  			out= new DataOutputStream(client.getOutputStream());
  			txa_info.append("Da ket noi den Server tai cong "+port+".\n");
			message = getMessage();
			//message="TT|Nguyen Van Toan|50|Chuyen khoan";
        	out.writeBytes("@$0|00000|145|Client|Send|1|123$$"+message+"$@");
        	out.write(13);
        	out.write(10);
        	out.flush();
        	String inLine = in.readLine();
        	txa_info.append("Thong bao: " +inLine+ "\n\n");  			
  		}
  		catch(Exception e)
  		{
  			txa_info.append("Loi: Khong the ket noi den Server! Vui long thu lai.\n");
  		}
 	}

 	public void shutdown(){
  		try
  		{
    		client.close();
    	}
    	catch(IOException ex)
    	{
    		txa_info.append("Loi IO dong ket noi!!!\n");
   		}	
	}	

}