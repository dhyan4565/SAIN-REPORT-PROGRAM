package Controller;

import Class.Course;
import Class.DBHelper;
import Class.Major;
import Class.Student;
import Class.clsCourseStudent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseController {

    ArrayList<Course> CourseBag = new ArrayList<Course>();
    ArrayList<Major> MajorBag = new ArrayList<Major>();
    DBHelper obj = new DBHelper();

    public CourseController() {
        obj.open();
    }

    public ArrayList<Course> GetCourse() {

        try {
            obj.open();
            String str = "select * from course";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                Course cs = new Course();
                cs.setCourseNum(rs.getString("courseno"));
                cs.setCourseTitle(rs.getString("coursetitle"));
                cs.setCredits(rs.getString("credits"));
                cs.setGrade(rs.getString("grade"));
                CourseBag.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CourseBag;
    }

    public void AddCourse(Course cs) {

    }

    public void DeleteCourse(Course cs) {
        try {
            //
            String str = "delete from course where courseno = '" + cs.getCourseNum() + "'";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
            //
            CourseBag.remove(cs);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SaveCourse(Course crs) {
        try {
            //
            String str = "INSERT INTO course(courseno, coursetitle, credits, grade) VALUES ('" + crs.getCourseNum() + "','" + crs.getCourseTitle() + "'," + crs.getCredits() + ",'" + crs.getGrade() + "')";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
            //
            CourseBag.add(crs);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
