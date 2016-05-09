
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Class.DBHelper;
import Class.Major;
import Class.Student;
import Class.clsCourseMajor;
import Class.clsCourseStudent;

public class MajorController {

    ArrayList<Major> MajorBag = new ArrayList<Major>();
    DBHelper obj = new DBHelper();

    public MajorController() {
        obj.open();
    }

    public void SaveMajor(Major mj) {
        try {

            Major mj1 = LoadMajor(mj.getId());
            if (mj1 == null) {
                String str = "INSERT INTO major(majorid, title, totalcredits) VALUES (" + mj.getMajorid() + ",'" + mj.getTitle() + "'," + mj.getTotalcredits() + ")";
                Statement stmt = obj.GetStatement();
                stmt.executeUpdate(str);
                //
                MajorBag.add(mj);
                //
            } else {
                String str = "UPDATE major SET title='" + mj.getTitle() + "',totalcredits=" + mj.getTotalcredits() + " WHERE majorid=" + mj.getMajorid() + "";
                Statement stmt = obj.GetStatement();
                stmt.executeUpdate(str);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Major.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddCourse(Major mj1, String course, String type) {

        try {
            String str = "INSERT INTO majorcourses(majorid, courseid, coursetypeid) VALUES (" + mj1.getMajorid() + "," + course + ",'" + type + "')";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
        } catch (SQLException ex) {
            Logger.getLogger(Major.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean DeleteCourse(Major mj1, clsCourseMajor cs) {

        try {
            String str = "DELETE FROM majorcourses WHERE majorid= " + mj1.getMajorid() + " and courseid = " + cs.getCourseno() + "";
            Statement stmt = obj.GetStatement();
            stmt.executeUpdate(str);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Major LoadMajor(String newValue) {
        Major mj1 = null;
        try {
            String str = "select * from major where majorid = " + newValue + "";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                mj1 = new Major();
                mj1.setMajorid(rs.getString("majorid"));
                mj1.setTitle(rs.getString("title"));
                mj1.setTotalcredits(rs.getString("totalcredits"));
                mj1.LoadCourses();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Major.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mj1;
    }
}
