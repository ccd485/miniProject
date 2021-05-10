package sungjuk_frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SungFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JToolBar jtb = new JToolBar();
	private JButton input_jb = new JButton("�Է�");
	private JButton delete_jb = new JButton("����");
	private JButton exit_jb = new JButton("����");
	
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel name_jp = new JPanel();
	private JPanel kor_jp = new JPanel();
	private JPanel eng_jp = new JPanel();
	private JPanel tot_jp = new JPanel();
	
	private String str = new String("��  ��\t����\t����\t����\t����\n");
	
	private JTextArea name_jta = new JTextArea();
	private JTextArea kor_jta = new JTextArea();
	private JTextArea eng_jta = new JTextArea();
	private JTextArea tot_jta = new JTextArea();
	
	
	private InputDialog idlg = new InputDialog(this, "�Է�", true);
	
	private SungPro pro = new SungProImpl();
	
	public void init() {
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout());
		con.add("North", jtb);
		jtb.add(input_jb);	
		jtb.add(delete_jb);
		jtb.addSeparator();
		jtb.add(exit_jb);
		con.add("Center", jtp);
		jtp.addTab("�̸�", name_jp);
		name_jp.setLayout(new BorderLayout());
		name_jp.add("Center", name_jta);
		jtp.addTab("����", kor_jp);
		kor_jp.setLayout(new BorderLayout());
		kor_jp.add("Center", kor_jta);
		jtp.addTab("����", eng_jp);
		eng_jp.setLayout(new BorderLayout());
		eng_jp.add("Center", eng_jta);
		jtp.addTab("����", tot_jp);
		tot_jp.setLayout(new BorderLayout());
		tot_jp.add("Center", tot_jta);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paneSetting() {
		ArrayList<Student> list = pro.view("�̸�");
		name_jta.setText(str);
		for(Student name : list) {
			name_jta.append(name.disp()+"\n");
		}
		list = pro.view("����");
		kor_jta.setText(str);
		for(Student kor : list) {
			kor_jta.append(kor.disp()+"\n");
		}
		list = pro.view("����");
		eng_jta.setText(str);
		for(Student eng : list) {
			eng_jta.append(eng.disp()+"\n");
		}
		list = pro.view("����");
		tot_jta.setText(str);
		for(Student tot : list) {
			tot_jta.append(tot.disp()+"\n");
		}
	}
	
	public void start() {
		input_jb.addActionListener(this);
		delete_jb.addActionListener(this);
		exit_jb.addActionListener(this);
		idlg.jbt.addActionListener(this);
	}
	
	public SungFrame(String title) {
		super(title);
		
		this.init();
		this.start();
		
		this.setSize(400, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth()/2;
		int ypos = (int)(screen.getHeight()/2) - this.getHeight()/2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==input_jb) {
			idlg.setVisible(true);
		}else if (e.getSource()==idlg.jbt) {
			pro.input(idlg.getName(), idlg.getKor(), idlg.getEng());
			idlg.clear();
			idlg.setVisible(false);
			paneSetting();
		}else if (e.getSource()==delete_jb) {
			String name = JOptionPane.showInputDialog(this, "������ �л��� �̸��� �Է�",
							"�Է�", JOptionPane.QUESTION_MESSAGE);
			pro.delete(name);
			paneSetting();
			
		}else if (e.getSource()==exit_jb) {
			System.exit(0);
		}
	}
}
