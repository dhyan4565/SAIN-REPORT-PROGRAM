package Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student {

    String name;
    String userId;
    String password;
    String majorId;
    String majorName;
    double gpa;
    String address;
    ArrayList<clsCourseStudent> coursesTaking = new ArrayList<>();
    ArrayList<clsCourseStudent> coursesTaken = new ArrayList<>();
    ArrayList<clsCourseStudent> coursesNeeded = new ArrayList<>();
    ArrayList<clsCourseStudent> coursesFailed = new ArrayList<>();
    ArrayList<clsCourseStudent> courses = new ArrayList<>();


    public Student() {

    }
    public Student(String userid) {

        this.userId = userid;
    }

    public void LoadCourses() {
        DBHelper obj = new DBHelper();

        try {
            obj.open();
            String str = "select studentcourse.*,course.* from studentcourse inner join course on studentcourse.courseid = course.courseno having studentid = " + userId + "";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                clsCourseStudent cs = new clsCourseStudent(Integer.toString(rs.getInt("courseid")), rs.getString("coursetype"),rs.getString("coursetitle"));
                courses.add(cs);
                if (rs.getString("coursetype").equals("Taking")) {
                    coursesTaking.add(cs);
                } else if (rs.getString("coursetype").equals("Taken")) {
                    coursesTaken.add(cs);
                } else if (rs.getString("coursetype").equals("Needed")) {
                    coursesNeeded.add(cs);
                } else if (rs.getString("coursetype").equals("Failed")) {
                    coursesFailed.add(cs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<clsCourseStudent> getCourses() {
        return courses;
    }

    public ArrayList<clsCourseStudent> getCoursesTaken() {
        return coursesTaken;
    }

    public ArrayList<clsCourseStudent> getCoursesTaking() {
        return coursesTaking;
    }

    public ArrayList<clsCourseStudent> getCoursesFailed() {
        return coursesFailed;
    }

    public ArrayList<clsCourseStudent> getCoursesNeeded() {
        return coursesNeeded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String name) {
        this.majorName = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", userId=" + userId + ", password=" + password + ", majorId=" + majorId
                + ", gpa=" + gpa + ", address=" + address + "]";
    }

    public void calculateGPA() {

    }

}
