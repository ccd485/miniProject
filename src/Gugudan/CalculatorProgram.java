package Gugudan;
import java.awt.*;
import java.awt.event.*;

class MyFrame10 extends Frame implements ActionListener{
	private TextField tf = new TextField("");
	
	private Button screen = new Button();
	private Button bt[] = new Button[16];
	private String str[] = new String[] {
			"7","8","9","+","4","5","6","-","1","2","3","*","0","=","%","/"};
	private Panel p1 = new Panel();
	private Font font = new Font("", Font.BOLD, 25);
	private Color color = new Color(250, 250, 250);
	private Cursor cur = new Cursor(Cursor.HAND_CURSOR);
	
	private char op=0;
	private boolean isOp = false; //��ȣ��ư�� �������� �ƴ��� Ȯ���ϴ� ����
	private String firstSu;
	
	public void init() {
		
		this.setLayout(new BorderLayout());
		this.add("North", tf);
		tf.setFont(new Font("", Font.PLAIN, 25));
		tf.setEditable(false);
		this.setCursor(cur);
		p1.setLayout(new GridLayout(4, 4, 2, 2));
		p1.setBackground(Color.WHITE);
		p1.setForeground(Color.WHITE);
		screen.setBackground(Color.LIGHT_GRAY);
		this.add("Center", screen);
		this.add("South", p1);
		for(int i=0; i<bt.length; ++i) {
			bt[i] = new Button(str[i]);
			p1.add(bt[i]);
			bt[i].setFont(new Font("", Font.PLAIN, 20));
			bt[i].setBackground(Color.DARK_GRAY);
			bt[i].setFont(font);
			bt[i].addActionListener(this);
		}	
	}
	public MyFrame10(String title) {
		super(title);
		this.init();
		
		this.setSize(500, 400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2) - this.getWidth() / 2;
		int ypos = (int) (screen.getHeight() / 2) - this.getHeight() / 2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(e.getSource()==bt[13]) {
			calculator();
			isOp=true;
			op=0;
		}else if(e.getSource()==bt[3]||e.getSource()==bt[7]||e.getSource()==bt[11]
				||e.getSource()==bt[14]||e.getSource()==bt[15]){
			if(op!=0) {
				calculator();
			}
			isOp=true;	
			op=str.charAt(0);
				
		}else {
			if(isOp) {
				firstSu = tf.getText();
				tf.setText("");
				isOp=false;
			}
			tf.setText(tf.getText()+str.trim());
			
		}
	}
	public void calculator() {
		int res=0;
		switch(op) {
		case '+' :
			res=Integer.parseInt(firstSu)+Integer.parseInt(tf.getText());break;
		case '-' :
			res=Integer.parseInt(firstSu)-Integer.parseInt(tf.getText());break;
		case '*' :
			res=Integer.parseInt(firstSu)*Integer.parseInt(tf.getText());break;
		case '/' :
			res=Integer.parseInt(firstSu)/Integer.parseInt(tf.getText());break;
		case '%' :
			res=Integer.parseInt(firstSu)%Integer.parseInt(tf.getText());break;
		}
		tf.setText(String.valueOf(res));
	}
}

public class CalculatorProgram {
	public static void main(String[] args) {
		MyFrame10 mf = new MyFrame10("계산기");

	}
}