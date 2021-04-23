package Calendar;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class MyFrame11 extends Frame implements ActionListener{
	private Label lb = new Label("", Label.CENTER);
	private Button bt1 = new Button("◀◀");
	private Button bt2 = new Button(" ◀ ");
	private Button bt3 = new Button(" ▶ ");
	private Button bt4 = new Button("▶▶");
	
	private Button week_bt[] = new Button[7];
	private String week_str[] = new String[] {"SU", "MO", "TU", "WE", "TH", "FR", "ST"};
	private Button day_bt[] = new Button[42];
	
	private Panel p = new Panel();
	private Panel left_p = new Panel();
	private Panel right_p = new Panel();
	
	private Panel center_p = new Panel();
	
	private int year, month;
	private Calendar cal;
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("North", p);
		p.setLayout(new BorderLayout());
		p.add("West", left_p);
		left_p.setLayout(new GridLayout(1,2));
		left_p.add(bt1);		
		left_p.add(bt2);		
		lb.setFont(new Font("", Font.BOLD, 25));
		lb.setText(year+"�� " + month+"��");
		p.add("Center", lb);
		p.add("East", right_p);
		right_p.setLayout(new GridLayout(1,2));
		right_p.add(bt3);		
		right_p.add(bt4);
		
		this.add("Center", center_p);
		center_p.setLayout(new GridLayout(7,7));
		for(int i=0; i<week_bt.length; ++i) {
			week_bt[i] = new Button(week_str[i]);
			center_p.add(week_bt[i]);
			//week_bt[i].setEnabled(false);
		}
		for(int i=0; i<day_bt.length; ++i) {
			day_bt[i] = new Button("");
			center_p.add(day_bt[i]);
			day_bt[i].setEnabled(false);
		}
		buttonSetting();
	}
	public void buttonSetting() {
		cal.set(year, month-1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int co = 0;
		for(int i=1; i<week; ++i) {
			day_bt[co].setLabel("");
			day_bt[co].setEnabled(false);
			co++;
		}
		for(int i=1; i<=last_day; ++i) {
			day_bt[co].setLabel(String.valueOf(i));
			day_bt[co].setEnabled(true);
			co++;
		}
		for(int i=co; i<day_bt.length; ++i) {
			day_bt[i].setLabel("");
			day_bt[i].setEnabled(false);
		}
		
	}
	public void start() {
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
	}
	
	public MyFrame11(String title) {
		super(title);
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		
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
		if (e.getSource()==bt1) {
			year--;
		}else if (e.getSource()==bt2) {
			month--;
			if (month<1) {
				year--;
				month = 12;
			}
		}else if (e.getSource()==bt3) {
			month++;
			if (month>12) {
				year++;
				month = 1;
			}
		}else if (e.getSource()==bt4) {
			year++;
		}
		lb.setText(year+"년 " + month+"월");
		buttonSetting();
	}
	
}
public class CalendarProgram {
	public static void main(String[] args) {
		MyFrame11 frame = new MyFrame11("달력");
	}
}