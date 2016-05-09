package Controller;

import Class.Course;
import Class.DBHelper;
import Class.Student;
import Class.clsCourseStudent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentController {

    ArrayList<Student> StudentBag = new ArrayList<Student>();
    DBHelper obj = new DBHelper();

    public StudentController() {
        obj.open();
    }

    public void SaveStudent(Student std) {
        try {

            Student st = LoadStudent(std.getUserId());
            if (st == null) {
                String str = "INSERT INTO student(name,majorid,username,password,address,gpa) VALUES ('" + std.getName() + "'," + std.getMajorId() + ",'" + std.getUserId() + "','" + std.getPassword() + "','" + std.getAddress() + "'," + std.getGpa() + ")";
                Statement stmt = obj.GetStatement();
                stmt.executeUpdate(str);
                //
                StudentBag.add(std);
                //
            } else {
                String str = "UPDATE student SET name='" + std.getName() + "',majorid=" + std.getMajorId() + ",password='" + std.getPassword() + "',address='" + std.getAddress() + "',gpa=" + std.getGpa() + " WHERE username=" + std.getUserId() + "";
                Statement stmt = obj.GetStatement();
                stmt.executeUpdate(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddCourse(Student st, String course, String type) {

        try {
            String str = "INSERT INTO studentcourse(studentid,courseid, coursetype) VALUES(" + st.getUserId() + "," + course + ",'" + type + "')";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean DeleteCourse(Student st, clsCourseStudent cs) {

        try {
            String str = "DELETE FROM studentcourse WHERE studentid = " + st.getUserId() + " and courseid = " + cs.getCourseno() + "";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Student FindbyID(String id) {

        return null;
    }

    public Student FindbyName(String id) {

        return null;
    }

    public Student LoadStudent(String newValue) {
        Student st = null;
        try {
            String str = "select * from student where username = '" + newValue + "'";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                st = new Student();
                st.setAddress(rs.getString("address"));
                st.setGpa(rs.getInt("gpa"));
                st.setMajorId(rs.getString("majorid"));
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
