package logisticsManageProgram;

import java.io.Serializable;

/*
 * create table logistics(
item varchar2(100),
price number(10),
quantity number(20)
indate varchar2(10));
*/
public class Log_DTO implements Serializable{
	private String item;
	private int price;
	private int quantity;
	private String indate;
	
	public Log_DTO() {
		
	}
	
	public Log_DTO(String item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public Log_DTO(String item, int price, int quantity, String indate) {
		this.item = item;
		this.price = price;
		this.quantity = quantity;
		this.indate = indate;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
}
