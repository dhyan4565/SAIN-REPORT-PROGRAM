package Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Major {

    String id;
    String title;
    String majorid;
    String totalcredits;

    ArrayList<clsCourseMajor> majorCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> sciCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> engCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> matCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> sscCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> humCourses = new ArrayList<>();
    ArrayList<clsCourseMajor> AllMajorCourses = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }

    public String getTotalcredits() {
        return totalcredits;
    }

    public void setTotalcredits(String totalcredits) {
        this.totalcredits = totalcredits;
    }

    public ArrayList<clsCourseMajor> getMajorCourses() {

        return AllMajorCourses;
    }

    public void setMajorCourses(ArrayList<clsCourseMajor> majorCourses) {
        this.majorCourses = majorCourses;
    }

    public ArrayList<clsCourseMajor> getSciCourses() {
        return sciCourses;
    }

    public void setSciCourses(ArrayList<clsCourseMajor> sciCourses) {
        this.sciCourses = sciCourses;
    }

    public ArrayList<clsCourseMajor> getEngCourses() {
        return engCourses;
    }

    public void setEngCourses(ArrayList<clsCourseMajor> engCourses) {
        this.engCourses = engCourses;
    }

    public ArrayList<clsCourseMajor> getMatCourses() {
        return matCourses;
    }

    public void setMatCourses(ArrayList<clsCourseMajor> matCourses) {
        this.matCourses = matCourses;
    }

    public ArrayList<clsCourseMajor> getSscCourses() {
        return sscCourses;
    }

    public void setSscCourses(ArrayList<clsCourseMajor> sscCourses) {
        this.sscCourses = sscCourses;
    }

    public ArrayList<clsCourseMajor> getHumCourses() {
        return humCourses;
    }

    public void setHumCourses(ArrayList<clsCourseMajor> humCourses) {
        this.humCourses = humCourses;
    }

    public ArrayList<clsCourseMajor> getAllMajorCourses() {
        return AllMajorCourses;
    }

    public void setAllMajorCourses(ArrayList<clsCourseMajor> AllMajorCourses) {
        this.AllMajorCourses = AllMajorCourses;
    }

    public void getTotalCreditsNeeded() {

    }

    public void LoadCourses() {
          DBHelper obj = new DBHelper();

        try {
            obj.open();
            String str = "select * from majorcourses where majorid = " + majorid + "";
            Statement stmt = obj.GetStatement();
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                clsCourseMajor cs = new clsCourseMajor(Integer.toString(rs.getInt("courseid")), rs.getString("coursetypeid"));
                AllMajorCourses.add(cs);
                /*
                 if (rs.getString("coursetype").equals("Taking")) {
                 coursesTaking.add(cs);
                 } else if (rs.getString("coursetype").equals("Taken")) {
                 coursesTaken.add(cs);
                 } else if (rs.getString("coursetype").equals("Needed")) {
                 coursesNeeded.add(cs);
                 } else if (rs.getString("coursetype").equals("Failed")) {
                 coursesFailed.add(cs);
                 }
                 */
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
