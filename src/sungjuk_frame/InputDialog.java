package sungjuk_frame;
import java.awt.*;
import javax.swing.*;

public class InputDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private JLabel name_lb = new JLabel("이름", Label.RIGHT);
	private JTextField name_jtf = new JTextField();
	private JPanel name_jp = new JPanel();
	private JLabel kor_lb = new JLabel("국어", Label.RIGHT);
	private JTextField kor_jtf = new JTextField();
	private JPanel kor_jp = new JPanel();
	private JLabel eng_lb = new JLabel("영어", Label.RIGHT);
	private JTextField eng_jtf = new JTextField();
	private JPanel eng_jp = new JPanel();
	
	public JButton jbt = new JButton("입력");
	
	public void init() {
		this.setLayout(new GridLayout(4,1));
		this.add(name_jp);
		name_jp.setLayout(new BorderLayout());
		name_jp.add("West", name_lb);
		name_jp.add("Center", name_jtf);
		this.add(kor_jp);
		kor_jp.setLayout(new BorderLayout());
		kor_jp.add("West", kor_lb);
		kor_jp.add("Center", kor_jtf);
		this.add(eng_jp);
		eng_jp.setLayout(new BorderLayout());
		eng_jp.add("West", eng_lb);
		eng_jp.add("Center", eng_jtf);
		this.add(jbt);
		
	}
	
	public String getName() {
		return name_jtf.getText();
	}
	public int getKor() {
		return Integer.parseInt(kor_jtf.getText());
	}
	public int getEng() {
		return Integer.parseInt(eng_jtf.getText());
	}
	
	public void clear() {
		name_jtf.setText("");
		kor_jtf.setText("");
		eng_jtf.setText("");
	}
	
	public InputDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		
		this.init();
		
		this.setSize(120, 160);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth() - 210;
		int ypos = (int)(screen.getHeight()/2) - this.getHeight() + 15;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
	}
}
