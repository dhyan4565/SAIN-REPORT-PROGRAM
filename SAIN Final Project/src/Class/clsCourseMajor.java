package Class;

public class clsCourseMajor {

    public String courseno;
    public String type;

    public String getCourseno() {
        return courseno;
    }

    public void setCourseno(String courseno) {
        this.courseno = courseno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public clsCourseMajor(String courseno, String type) {

        this.courseno = courseno;
        this.type= type;

    }

}
