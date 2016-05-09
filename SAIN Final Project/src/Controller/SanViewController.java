
package Controller;

import Class.DBHelper;
import Class.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class SanViewController {

    DBHelper obj = new DBHelper();

    public SanViewController() {
        obj.open();
    }

    public Student LoadStudent(String newValue) {
        Student st = null;
        try {
            String str = "SELECT student.*,major.title as majorname FROM student inner join major on student.majorid = major.majorid having username= '" + newValue + "'";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                st = new Student();
                st.setAddress(rs.getString("address"));
                st.setGpa(rs.getInt("gpa"));
                st.setMajorId(rs.getString("majorid"));
                st.setMajorName(rs.getString("majorname"));
                st.setName(rs.getString("name"));
                st.setPassword(rs.getString("password"));
                st.setUserId(rs.getString("username"));
                st.LoadCourses();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

}
