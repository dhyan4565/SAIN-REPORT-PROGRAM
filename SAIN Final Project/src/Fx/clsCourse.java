/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fx;

import Class.Course;
import Class.Major;
import Class.Student;
import Class.clsCourseMajor;
import Class.clsCourseStudent;
import Controller.CourseController;
import Controller.MajorController;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class clsCourse extends Application {

	public BorderPane root;
    private TableView table = new TableView();
    ObservableList<Course> allData = FXCollections.observableArrayList();
    CourseController MyController = new CourseController();
    Course CurrentCourse = new Course();
    List selectedRow = null;
    TextField crn;
    TextField title;
    TextField credits;
    TextField grade1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Final Project");
        root = new BorderPane();
        Scene scene = new Scene(root, 980, 250);
//        clsSample s1 = new clsSample();
        VBox top = new VBox(10);
        top.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(top);
        top.getChildren().addAll(CoursePane());

        HBox bottom = new HBox(10);
        bottom.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(bottom);

//        bottom.getChildren().addAll(menuBar());
        //set scene to stage & show
        stage.setScene(scene);
        stage.show();
    }

    private HBox CoursePane() {
        HBox coursePane = new HBox();
        coursePane.setPadding(new Insets(10, 10, 10, 10));
        coursePane.setMinWidth(650);
        /*
         Label course = new Label("CourseNum");
         TextField courseNumber = new TextField();
         courseNumber.setPromptText("Course Number");
         final HBox hb = new HBox();
         hb.setSpacing(25);
         hb.setAlignment(Pos.CENTER);
         ComboBox<String> selectFile = new ComboBox<String>();
         selectFile.getItems().addAll("Course Taking", "Course Taken", "Course Needed", "Course Failed");
         hb.getChildren().addAll(course, courseNumber, selectFile);
         */
        table.setEditable(true);
        TableColumn courseNum = new TableColumn("CourseNum");
        courseNum.setCellValueFactory(
                new PropertyValueFactory<Course, String>("courseNum"));
        courseNum.setMinWidth(110);
        TableColumn courseTitle = new TableColumn("CourseTitle");
        courseTitle.setCellValueFactory(
                new PropertyValueFactory<Course, String>("courseTitle"));
        courseTitle.setMinWidth(110);
        TableColumn courseCredit = new TableColumn("Credit");
        courseCredit.setCellValueFactory(
                new PropertyValueFactory<Course, String>("credits"));
        courseCredit.setMinWidth(110);
        TableColumn grade = new TableColumn("Grade");
        grade.setCellValueFactory(
                new PropertyValueFactory<Course, String>("grade"));
        grade.setMinWidth(110);
        table.getColumns().addAll(courseNum, courseTitle, courseCredit, grade);
        table.setMaxHeight(200);
        table.setMaxWidth(430);
        table.setItems(allData);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (table.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
                    selectedRow = selectionModel.getSelectedItems();
                }
            }
        });

        Button add = new Button("Add");
        Button delete = new Button("Delete");
        add.setOnAction(e -> {
            Course obj = new Course();
            obj.setCourseNum(crn.getText());
            obj.setCourseTitle(title.getText());
            obj.setGrade(grade1.getText());
            obj.setCredits(credits.getText());
            MyController.SaveCourse(obj);
            allData.add(obj);
        });
        delete.setOnAction(e -> {
            Course obj = (Course) selectedRow.get(0);
            System.out.println("Selected Value" + obj.getCourseNum());
            MyController.DeleteCourse(obj);
            allData.remove(obj);
        });
        /*
         HBox hb1 = new HBox();
         hb1.setSpacing(25);
         hb1.setAlignment(Pos.CENTER);
         hb1.getChildren().addAll(delete, edit);
         hb1.setAlignment(Pos.CENTER);
         */
        Label courseLbl = new Label("CourseInfo:");
        crn = new TextField();
        crn.setPromptText("CourseNum");
        title = new TextField();
        title.setPromptText("CourseTitlre");
        credits = new TextField();
        credits.setPromptText("Credits");
        grade1 = new TextField();
        grade1.setPromptText("Course Num");

        HBox hb2 = new HBox();
        hb2.setSpacing(25);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(courseLbl, crn, title, credits, grade1, add, delete);
        hb2.setAlignment(Pos.BOTTOM_CENTER);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hb2, table);
        vbox.setAlignment(Pos.CENTER);
        coursePane.getChildren().addAll(vbox);
        //
        allData.addAll(MyController.GetCourse());
        //
        return coursePane;
    }

}
