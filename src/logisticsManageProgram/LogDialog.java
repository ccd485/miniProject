package logisticsManageProgram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LogDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	JLabel title_lb = new JLabel("", JLabel.CENTER);

	JLabel item_lb = new JLabel("품명", JLabel.RIGHT);
	JTextField item_tf = new JTextField();
	JPanel item_p = new JPanel();
	JLabel price_lb = new JLabel("가격", JLabel.RIGHT);
	JTextField price_tf = new JTextField();
	JPanel price_p = new JPanel();
	JLabel quantity_lb = new JLabel("수량", JLabel.RIGHT);
	JTextField quantity_tf = new JTextField();
	JPanel quantity_p = new JPanel();
	JPanel center_p = new JPanel();

	JButton ok_bt = new JButton("입력");
	JButton exit_bt = new JButton("종료");
	JPanel south_p = new JPanel();

	public LogDialog(Frame owner, String title) {
		super(owner, title);
		init();
		super.setSize(150, 180);
		Dimension screen = 
				Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2 - 235);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2 - 50);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(false);
	}

	protected void init() {
		center_setting();
		south_setting();
		this.setLayout(new BorderLayout());
		title_lb.setFont(new Font("", Font.BOLD, 20));
		title_lb.setText(getTitle());
		this.add("North", title_lb);
		this.add("Center", center_p);
		this.add("South", south_p);
	}

	protected void center_setting() {
		center_p.setLayout(new GridLayout(3, 1));
		center_p.add(item_p);
		item_p.setLayout(new BorderLayout());
		item_p.add("West", item_lb);
		item_p.add(item_tf);
		center_p.add(price_p);
		price_p.setLayout(new BorderLayout());
		price_p.add("West", price_lb);
		price_p.add(price_tf);
		center_p.add(quantity_p);
		quantity_p.setLayout(new BorderLayout());
		quantity_p.add("West", quantity_lb);
		quantity_p.add(quantity_tf);
		center_p.add(quantity_p);
	}

	protected void south_setting() {
		south_p.setLayout(new FlowLayout());
		south_p.add(ok_bt);
		south_p.add(exit_bt);
	}

	public void setClose() {
		item_tf.setText("");
		price_tf.setText("");
		quantity_tf.setText("");
		super.setVisible(false);
	}
	public Log_DTO input() {
		String item = item_tf.getText().trim();
		String price = price_tf.getText().trim();
		String quantity = quantity_tf.getText().trim();
		Log_DTO ld = new Log_DTO(item, Integer.parseInt(price), Integer.parseInt(quantity));
		return ld;
	}

}
