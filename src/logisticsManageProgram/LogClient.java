package logisticsManageProgram;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class LogClient extends JFrame implements ActionListener{
	
	private String textTitle = "ǰ��\t����\t����\t�����\n";
	private JTextArea result = new JTextArea("");
	private JScrollPane jsp = new JScrollPane(result);
	LogDialog logdlg = new LogDialog(this, "�Է�");
	DatagramSocket ds = null;
	InetAddress ia = null;
	LogImplement li = null;
	ArrayList<Log_DTO> list = null; 
	Container con;
	JPanel jp = new JPanel();
	JButton jbt1 = new JButton("�Է�");
	JButton jbt2 = new JButton("ã��");
	JButton jbt3 = new JButton("������Ʈ");
	JButton jbt4 = new JButton("����");
	JButton jbt5 = new JButton("����");
	
	protected void init() {
		con = this.getContentPane();
		con.add("North", jp);
		jp.setLayout(new GridLayout(1,6));
		jp.add(jbt1);
		jp.add(jbt2);
		jp.add(jbt3);
		jp.add(jbt4);
		jp.add(jbt5);
		con.add(jsp);
	}
	
	protected void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jbt1.addActionListener(this);//�Է�ó��
		jbt2.addActionListener(this);//ã��ó��
		jbt3.addActionListener(this);//������Ʈó��
		jbt4.addActionListener(this);//����ó��
		jbt5.addActionListener(this);//����ó��
		logdlg.ok_bt.addActionListener(this);//�Է½� �Է¹�ưó��
	}
	
	public LogClient(String title) {
		super(title);
		try {
			ds = new DatagramSocket(12346);
			ia = InetAddress.getByName("localhost");
		}catch(Exception e) {}
		
		this.init();
		this.start();
		
		super.setSize(650, 500);
		Dimension screen = 
				Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jbt1) {				
			logdlg.setVisible(true);
		}else if (e.getSource()==jbt2) {		//ã��
			receiveData();
			view();
		}else if (e.getSource()==jbt3) {		//������Ʈ
			String item = JOptionPane.showInputDialog
					(this, "������ ǰ�� �Է� : ", "�Է�", 
							JOptionPane.QUESTION_MESSAGE);
			String quantity = JOptionPane.showInputDialog
					(this, "������ ���� �Է� : ", "�Է�", 
							JOptionPane.QUESTION_MESSAGE);
			sendUpdate(item, Integer.parseInt(quantity));
		}else if (e.getSource()==jbt4) {				//����
			String item = JOptionPane.showInputDialog
					(this, "������ ǰ�� �Է� : ", "�Է�", 
							JOptionPane.QUESTION_MESSAGE);
			sendDel(item);
			JOptionPane.showMessageDialog(this, "�����Ϸ�");
		}else if (e.getSource()==jbt5) {						//����
			JOptionPane.showMessageDialog(this, "���α׷� ����");
			sendExit();
			System.exit(0);
		}else if (e.getSource()==logdlg.ok_bt) {			//�Է�
			Log_DTO ld = logdlg.input();
			sendInsert(ld);
			JOptionPane.showMessageDialog(this, "�ڷ����۳�"); 
			logdlg.setClose();
		}else if (e.getSource()==logdlg.exit_bt) {
			
		}
	}
	public void sendUpdate(String item, int quantity) {
		Log_DTO ld = new Log_DTO(item,quantity);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(ld);
			oos.flush();
			byte[] data = baos.toByteArray();
			DatagramPacket dp = new DatagramPacket(data,
					data.length, ia, 12344);
			ds.send(dp);
		}catch(Exception ee) {}
	}
	
	public void view() {
		result.setText(textTitle);
		for(Log_DTO ld : list) {
			result.append(ld.getItem()+"\t");
			result.append(ld.getPrice()+"\t");
			result.append(ld.getQuantity()+"\t");
			result.append(ld.getIndate()+"\n");
			
		}
	}
	
	public void receiveData() {
		try {
			String data = "�ڷ��û";
			DatagramPacket dp = new DatagramPacket(data.getBytes(),
				data.getBytes().length, ia, 12342);
			ds.send(dp);
			System.out.println(data);
			dp = new DatagramPacket(new byte[65508], 65508);
			ds.receive(dp);
			System.out.println("�ڷ����");
			ByteArrayInputStream baos = 
					new ByteArrayInputStream(dp.getData());
				BufferedInputStream bos = 
					new BufferedInputStream(baos);
				ObjectInputStream oos = 
					new ObjectInputStream(bos);
			
				list = (ArrayList)oos.readObject();
		}catch(Exception e) {}
	}
	
	public void sendInsert(Log_DTO ld) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(ld);
			oos.flush();
			byte[] data = baos.toByteArray();
			DatagramPacket dp = new DatagramPacket(data,
					data.length, ia, 12341);
			ds.send(dp);
		}catch(Exception ee) {}
	}
	
	public void sendExit() {
		try {
			String data = "Exit";
			DatagramPacket dp = new DatagramPacket(data.getBytes(),
					data.getBytes().length, ia, 12342);
			ds.send(dp);
		}catch(Exception e) {}
	}
	
	public void sendDel(String item) {
		DatagramPacket dp = new DatagramPacket(item.getBytes(),
				item.getBytes().length, ia, 12343);
		try {
			ds.send(dp);
		}catch(Exception e) {}	
	}

	public static void main(String[] args) {
		LogClient lc = new LogClient("�����������α׷�");

	}

}
