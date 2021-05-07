package logisticsManageProgram;

public interface LogInterface {
	public void insert(String item, int price, int quantity, String indate);
	public void search(String search, String searchString);
	public void update(String item, int quantity);
	public void delete(String item);
	public void exit();
}
