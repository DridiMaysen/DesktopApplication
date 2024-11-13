package dao;

import java.sql.ResultSet;

public interface Crud {
	public ResultSet All();
	
	public ResultSet search(int id);

	public int insert(Object o);
	
	public int update(Object o);
	
	public int delete(Object o);


}
