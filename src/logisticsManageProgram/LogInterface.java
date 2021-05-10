package logisticsManageProgram;

import java.util.ArrayList;

public interface LogInterface {
	public void insert(String item, int price, int quantity);
	public ArrayList<Log_DTO> search(String search, String searchString);
	public void update(String item, int quantity);
	public void delete(String item);
	public void exit();
}
