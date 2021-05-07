package logisticsManageProgram;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





public class LogServer_DAO {
	DatagramSocket dsIn, dsOut, dsDel, dsUp = null;
	ArrayList<Log_DTO> list = null;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	String url, user, pass;

	public LogServer_DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			dsIn = new DatagramSocket(12341);
			dsOut = new DatagramSocket(12342);
			dsDel = new DatagramSocket(12343);
			dsUp = new DatagramSocket(12344);
		}catch(Exception e) {}
		
		new MyThreadIn().start();
		new MyThreadOut().start();
		new MyThreadDel().start();
		new MyThreadUp().start();
		
		list = new ArrayList<>();
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "bigdata3";
		pass = "bigdata3";
	}

	public int insertLog(String item, int price, int quantity, String indate) {
		int res = 0;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into logistics values(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, item);
			ps.setInt(2, price);
			ps.setInt(3, quantity);
			ps.setString(4, indate);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Log_DTO> listLog() {
		ArrayList<Log_DTO> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from logistics";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Log_DTO dto = new Log_DTO();
				dto.setItem(rs.getString("item"));
				dto.setPrice(rs.getInt("price"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setIndate(rs.getString("indate"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Log_DTO> searchLog(String search, String searchString) {
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from logistics where " + search + " like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + searchString + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Log_DTO dto = new Log_DTO();
				dto.setItem(rs.getString("item"));
				dto.setPrice(rs.getInt("price"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setIndate(rs.getString("indate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateLog(String item, int quantity) {
		int res = 0;
		try {
			con = DriverManager.getConnection(url,user,pass);
			String sql = "update logistics set quantity=? where item = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setString(2, item);
			res = ps.executeUpdate();
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return res;
	}
	
	public int deleteLog(String item) {
		int res = 0;
		try {
			con = DriverManager.getConnection(url, user, pass);
			String sql = "delete from logistics where item=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, item);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	class MyThreadDel extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramPacket dp = 
					new DatagramPacket(new byte[65508], 65508);
					dsDel.receive(dp);
					String item = new String(dp.getData()).trim();
					deleteLog(item);
				}catch(Exception e) {}
			}
		}
	}
	class MyThreadOut extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramPacket dp = new DatagramPacket(new byte[65508], 65508);
					dsOut.receive(dp);
					
					System.out.println(new String(dp.getData()));
					if(new String(dp.getData())=="Exit") {
						System.exit(0);
					}
					
					InetAddress ia = dp.getAddress();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					BufferedOutputStream bos = new BufferedOutputStream(baos);
					ObjectOutputStream oos = new ObjectOutputStream(bos);
					
					oos.writeObject(listLog());
					oos.flush();
					byte[] data = baos.toByteArray();
					dp = new DatagramPacket(data, data.length, ia, 12346);
					dsOut.send(dp);
					dp = new DatagramPacket(data, data.length, ia, 12345);
					dsOut.send(dp);
				}catch(Exception e) {}
			}
		}
	}
	
	class MyThreadUp extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramPacket dp = 
					new DatagramPacket(new byte[65508], 65508);
					dsUp.receive(dp);
					
					ByteArrayInputStream baos = new ByteArrayInputStream(dp.getData());
					BufferedInputStream bos = new BufferedInputStream(baos);
					ObjectInputStream oos = new ObjectInputStream(bos);
					
					Log_DTO ld = (Log_DTO)oos.readObject();
					updateLog(ld.getItem(),ld.getQuantity());
				}catch(Exception e) {}
			}
		}
	}
	
	class MyThreadIn extends Thread{
		public void run() {
			while(true) {
				try {
					DatagramPacket dp = 
					new DatagramPacket(new byte[65508], 65508);
					dsIn.receive(dp);
					
					ByteArrayInputStream baos = new ByteArrayInputStream(dp.getData());
					BufferedInputStream bos = new BufferedInputStream(baos);
					ObjectInputStream oos = new ObjectInputStream(bos);
					
					Log_DTO ld = (Log_DTO)oos.readObject();
					insertLog(ld.getItem(),ld.getPrice(),ld.getQuantity(),ld.getIndate());
				}catch(Exception e) {}
			}
		}
	}
	public static void main(String[] args) {
		LogServer_DAO lsd = new LogServer_DAO();

	}

}
