package mineSweeper;
import java.awt.*;
import java.awt.event.*;

class MyFrame02 extends Frame implements ActionListener, MouseListener{
	private Button bt[][] = new Button[9][9];
	private Panel center_p = new Panel();
	
	private Button start_bt = new Button("시작");
	private Panel north_p = new Panel();
	
	private int boom[][] = new int[9][9];
	private boolean checkBoom[][] = new boolean[9][9];
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add("North", north_p);
		north_p.setLayout(new BorderLayout());
		north_p.add("East", start_bt);
		start_bt.addActionListener(this);
		this.add("Center", center_p);
		center_p.setLayout(new GridLayout(9, 9));
		for(int i=0; i<9; ++i) {
			for(int j=0; j<9; ++j) {
				bt[i][j] = new Button("");
				center_p.add(bt[i][j]);
				bt[i][j].addMouseListener(this);
			}
		}
	}
	
	public MyFrame02(String title) {
		super(title);
		
		this.init();
		
		this.setSize(800, 700);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2) - this.getWidth()/2;
		int ypos = (int)(screen.getHeight()/2) - this.getHeight()/2;
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void buttonSetting() {
		int count = 1;
		while(true) {
			int x = (int)(Math.random()*9);
			int y = (int)(Math.random()*9);
			if (boom[x][y] == 0) {
				boom[x][y] = 10;
				boomCount(x, y);
				count++;
			}
			if (count>10) break; 
		}
	}
	
	public void boomCount(int x, int y) {
		int minI = x-1; if (minI<0) minI = 0;
		int maxI = x+1; if (maxI>8) maxI = 8;
		int minJ = y-1; if (minJ<0) minJ = 0;
		int maxJ = y+1; if (maxJ>8) maxJ = 8;
		
		for(int i=minI; i<=maxI; ++i) {
			for(int j=minJ; j<=maxJ; ++j) {
				if (x==i && y==j) continue;
				boom[i][j]++;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==start_bt) {
			buttonSetting();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {//마우스 클릭했을때
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {//마우스 눌렀을때
		// TODO Auto-generated method stub
		
	}
	public void check(int a, int b) {
		int minI = a-1; if (minI<0) minI = 0;
		int maxI = a+1; if (maxI>8) maxI = 8;
		int minJ = b-1; if (minJ<0) minJ = 0;
		int maxJ = b+1; if (maxJ>8) maxJ = 8;
		for(int i=minI; i<=maxI; ++i) {
			for(int j=minJ; j<=maxJ; ++j) {
				if (i==a && b==j) continue;
				if (checkBoom[i][j]) continue;
				checkBoom[i][j] = true;
				if (boom[i][j] == 0) {
					bt[i][j].setLabel(String.valueOf(boom[i][j])); 
					check(i, j); 
				}
				else bt[i][j].setLabel(boom[i][j]==10 ? "폭탄" : String.valueOf(boom[i][j]));
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {//마우스 떼었을때
		
		for(int i=0; i<9; ++i) {
			for(int j=0; j<9; ++j) {
				if (e.getSource()==bt[i][j]) {
					if (boom[i][j] == 0) {
						bt[i][j].setLabel(String.valueOf(boom[i][j]));
						checkBoom[i][j] = true;
						check(i, j);
					}else {
						bt[i][j].setLabel(boom[i][j]==10 ? "폭탄" : String.valueOf(boom[i][j]));
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {//해당 컴포넌트에 마우스 커서가 있을때
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {//해당 컴포넌트에 마우스 커서가 없을때
		// TODO Auto-generated method stub
		
	}
}
public class Test02 {
	public static void main(String[] args) {
		MyFrame02 mf = new MyFrame02("지뢰찾기");
	}
	
}




