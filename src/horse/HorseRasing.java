package horse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Horse extends Canvas implements Runnable {
	private Image ho_run, ho_stop, ho_curent;
	private int num;
	private int xpos = 5;
	public static int rank = 1;
	
	public Horse(Image ho_run, Image ho_stop, int num) {
		this.ho_run = ho_run;
		this.ho_stop = ho_stop;
		this.num = num;
		this.ho_curent = ho_stop;		
	}
	public void paint(Graphics g) {		
		Dimension di = this.getSize();		
		g.drawImage(ho_curent, xpos, 10, 20, (int)(di.getHeight() - di.getHeight() * 0.1), this);
		g.drawLine(5, (int)(di.getHeight() - di.getHeight() * 0.1), (int)(di.getWidth() - 5), (int)(di.getHeight() - di.getHeight() * 0.1));
	}
	public void run() {
		ho_curent = ho_run;
		Dimension di = this.getSize();
		for(int i = 5; i <= di.getWidth() - 25; i += ((Math.random() * 5) + 2)) {
			xpos = i;
			this.repaint();
			try {
				Thread.sleep((int)(Math.random() * 300) + 100);
			}catch(InterruptedException e) {}
		}
		ho_curent = ho_stop;
		this.repaint();
		HorseRasing.rank_ta.append(rank++ + "등 말 번호 = " + num + "\n");
	}
}

public class HorseRasing extends JFrame implements ActionListener {
	private JPanel p = new JPanel();
	private JLabel ho_num_lb = new JLabel("말의  수 : ", JLabel.RIGHT);
	private JTextField ho_num_tf = new JTextField(5);
	private JButton set_bt = new JButton("설정");
	private JButton reset_bt = new JButton("초기화");
	private GridLayout gl = new GridLayout(1,4);//첫줄화면 설정
	
	private JPanel sta_p = new JPanel();//중앙 말이 들어갈 위치
	
	private JButton start_bt = new JButton("출발");
	private JPanel p2 = new JPanel();
	private FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);
	public static JTextArea rank_ta = new JTextArea(5, 20); //화면에 하단에 순위가 나타나는 곳을 만들어 JTextArea로 만들었다.
	
	private JPanel p1 = new JPanel();
	private BorderLayout bl = new BorderLayout(10, 10);//p1에 들어갈 Layout
	private BorderLayout bl1 = new BorderLayout();//전체 Layout
	
	private Horse[] ho;//말의 갯수를 받아서 배열로 만들자
	
	private Container con;
	public HorseRasing(String title){
		super(title);
		this.init();
		this.start();
		super.setSize(600,400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
	}
	
	public void init() {
		con = this.getContentPane();
		con.setLayout(bl);
		p.setLayout(gl);
		p.add(ho_num_lb);
		p.add(ho_num_tf);
		p.add(set_bt);
		p.add(reset_bt);
		con.add("North", p);		
		con.add("Center", sta_p);
		p1.setLayout(bl1);
		p2.setLayout(fl);
		p2.add(start_bt);
		p1.add("North", p2);
		p1.add("Center", rank_ta);
		con.add("South", p1);
	}
	public void start() {
		ho_num_tf.addActionListener(this);
		set_bt.addActionListener(this);
		start_bt.addActionListener(this);
		reset_bt.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ho_num_tf || e.getSource() == set_bt) {
			String c = ho_num_tf.getText();
			if(c == null || c.trim().length() == 0) return;
			int num = Integer.parseInt(c.trim());			
			Image aa = Toolkit.getDefaultToolkit().getImage("src/horse/1.gif");
			Image bb = Toolkit.getDefaultToolkit().getImage("src/horse/2.gif");
			
			con.remove(sta_p);//기존 JPanel 내용 삭제
			
			sta_p = new JPanel();
			sta_p.setLayout(new GridLayout(num, 1));
			
			ho = new Horse[num];
			for(int i = 0; i < num; ++i) {
				ho[i] = new Horse(aa, bb, i + 1);
				sta_p.add(ho[i]);				
			}
			
			con.add("Center", sta_p);
			con.validate();//컨테이너에 다시 그 서브 컴퍼넌트를 배치시킨다.
		}
		else if(e.getSource() == start_bt) {
			//Thread th[] = new Thread[ho.length];
			for(int i = 0; i < ho.length; ++i) {
				//th[i] = new Thread(ho[i]);
				//th[i].start();
				new Thread(ho[i]).start();	//말들을 뛰게 한다
			}
		}
		else if(e.getSource() == reset_bt) {
			rank_ta.setText("");
			ho_num_tf.setText("");			
			con.remove(sta_p);
			sta_p = new JPanel();
			con.add("Center", sta_p);
			con.validate();
			Horse.rank = 1;
			ho_num_tf.requestFocus();//포커스를 말의 갯수 넣는 곳으로 이동
		}
	}
	public static void main(String[] args) {
		new HorseRasing("경마게임");
	}
}
