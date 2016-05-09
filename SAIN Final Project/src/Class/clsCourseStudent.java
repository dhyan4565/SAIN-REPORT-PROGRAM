package Class;

public class clsCourseStudent {

    public String courseno;
    public String coursetitle;
    public String type;

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

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

    public clsCourseStudent(String courseno, String type) {

        this.courseno = courseno;
        this.type = type;

    }

    public clsCourseStudent(String courseno, String type, String coursetitle) {
        this.courseno = courseno;
        this.type = type;
        this.coursetitle = coursetitle;
    }
}
