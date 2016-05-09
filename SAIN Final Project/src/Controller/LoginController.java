package Controller;

import java.sql.ResultSet;
import java.sql.Statement;

import Class.DBHelper;

public class LoginController {
	DBHelper obj = new DBHelper();

	public LoginController() {
		obj.open();
	}

	public boolean VailidateStudent(String username, String password) {

		boolean flag = false;
		try {

			String str = "select * from student where username = '" + username + "' and password ='" + password + "'";
			Statement stmt = obj.GetStatement();
			ResultSet rs = stmt.executeQuery(str);
			if (rs.next()) {
				flag = true;
			}
			rs.close();

		} catch (Exception ex) {

			flag = false;
		}
		return flag;
	}
	
	public boolean VailidateStaff(String username, String password) {

		boolean flag = false;
		try {

			String str = "select * from staff where username = '" + username + "' and password ='" + password + "'";
			Statement stmt = obj.GetStatement();
			ResultSet rs = stmt.executeQuery(str);
			if (rs.next()) {
				flag = true;
			}
			

		} catch (Exception ex) {

			flag = false;
		}
		return flag;
	}
	
}
