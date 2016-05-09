package Fx;

import Class.Student;
import Class.clsCourseStudent;
import Controller.StudentController;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addStudentView extends Application {

    public BorderPane root;
    private TableView table = new TableView();
    ObservableList<clsCourseStudent> allData = FXCollections.observableArrayList();
    StudentController MyController = new StudentController();
    Student CurrentStudent = new Student();
    List selectedRow = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Final Project");
        root = new BorderPane();
        Scene scene = new Scene(root, 700, 500);
        //clsSample s1 = new clsSample();
        HBox top = new HBox(10);
        top.setAlignment(Pos.BASELINE_LEFT);
        root.setTop(top);
        top.setSpacing(5);
        top.getChildren().addAll(StudentPane(), CoursePane());

        HBox middle = new HBox(10);
        middle.setSpacing(5);
        middle.getChildren().addAll(CommandPane());
        root.setCenter(middle);

        HBox bottom = new HBox(10);
        bottom.setAlignment(Pos.BASELINE_LEFT);
        root.setBottom(bottom);
        stage.setScene(scene);
        stage.show();
    }

    private HBox CommandPane() {
        HBox commandPane = new HBox();
        commandPane.setPadding(new Insets(10, 10, 10, 10));
        Button Save = new Button("Save");
        Save.setOnAction(e -> {
            Student obj = new Student();
            obj.setAddress(address1.getText());
            obj.setGpa(0);
            obj.setMajorId(majorId1.getText());
            obj.setName(name1.getText());
            obj.setPassword(password1.getText());
            obj.setUserId(userId1.getText());
            MyController.SaveStudent(obj);
        });

        Button Close = new Button("Close");
        Button clearAll = new Button("Clear");
        clearAll.setOnAction(e -> {
            //Clear All
        });
        Close.setOnAction(e -> {
            System.exit(0);
        });
        commandPane.setSpacing(10);
        commandPane.setAlignment(Pos.BASELINE_LEFT);
        commandPane.getChildren().addAll(Save, clearAll, Close);
        return commandPane;
    }
    ComboBox<String> selectFile;
    TextField courseNumber;

    private HBox CoursePane() {
        HBox coursePane = new HBox();
        coursePane.setPadding(new Insets(10, 10, 10, 10));
        // coursePane.setMinWidth(650);

        Label course = new Label("Course Num");
        courseNumber = new TextField();
        courseNumber.setPromptText("Course Number");

        Label coursetype = new Label("Course Type");

        selectFile = new ComboBox<String>();
        selectFile.getItems().addAll("Taking", "Taken", "Needed", "Failed");

        final VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setAlignment(Pos.BASELINE_LEFT);
        vbox1.getChildren().addAll(course, courseNumber);

        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setAlignment(Pos.BASELINE_LEFT);
        vbox2.getChildren().addAll(coursetype, selectFile);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.BASELINE_LEFT);
        hb.getChildren().addAll(vbox1, vbox2);

        table.setEditable(true);
        TableColumn courseNum = new TableColumn("CourseNum");
        courseNum.setMinWidth(110);
        courseNum.setCellValueFactory(
                new PropertyValueFactory<clsCourseStudent, String>("courseno"));

        TableColumn courseTitle = new TableColumn("Type");
        courseTitle.setMinWidth(110);
        courseTitle.setCellValueFactory(
                new PropertyValueFactory<clsCourseStudent, String>("type"));

        table.getColumns().addAll(courseNum, courseTitle);
        table.setMaxHeight(200);
        table.setMaxWidth(430);
        table.setItems(allData);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (table.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = table.getSelectionModel();
                    selectedRow = selectionModel.getSelectedItems();
                }
            }
        });
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            allData.clear();
        });

        add.setOnAction(e -> {
            Student obj = new Student();
            obj.setAddress(address1.getText());
            obj.setGpa(0);
            obj.setMajorId(majorId1.getText());
            obj.setName(name1.getText());
            obj.setPassword(password1.getText());
            obj.setUserId(userId1.getText());
            MyController.AddCourse(obj, courseNumber.getText(), selectFile.getValue());
            allData.add(new clsCourseStudent(courseNumber.getText(), selectFile.getValue()));

        });
        delete.setOnAction(e -> {
            clsCourseStudent obj = (clsCourseStudent) selectedRow.get(0);
            System.out.println("Selected Value" + obj.courseno);
            MyController.DeleteCourse(CurrentStudent, obj);
            allData.remove(obj);
        });

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setAlignment(Pos.BASELINE_LEFT);
        hb1.getChildren().addAll(add, delete, clear);
        hb1.setAlignment(Pos.BASELINE_LEFT);
        /*
         Label courseLbl = new Label("CourseInfo:");
         TextField crn = new TextField();
         crn.setPromptText("CourseNum");
         TextField title = new TextField();
         title.setPromptText("CourseTitlre");
         TextField credits = new TextField();
         credits.setPromptText("Credits");
         TextField grade1 = new TextField();
         grade1.setPromptText("Course Num");

         HBox hb2 = new HBox();
         hb2.setSpacing(25);
         hb2.setAlignment(Pos.CENTER);
         hb2.getChildren().addAll(courseLbl, crn, title, credits, grade1, add);
         hb2.setAlignment(Pos.BOTTOM_CENTER);
         */
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(hb, hb1, table);
        vbox.setAlignment(Pos.CENTER);
        coursePane.getChildren().addAll(vbox);
        return coursePane;
    }
    TextField name1;
    TextField userId1;
    TextField password1;
    TextField address1, address2, address3;
    TextField city, state, zip, majorId1;

    private HBox StudentPane() {
        HBox studentPane = new HBox();
        studentPane.setPadding(new Insets(10, 10, 10, 10));
        studentPane.setMaxWidth(550);
//		studentPane.setAlignment(child, value);
        Label name = new Label();
        name.setText("Full Name:");
        name1 = new TextField();
        name1.setPromptText("Full Name");

        Label userId = new Label();
        userId.setText("ID:");
        userId1 = new TextField();
        userId1.setPromptText("ID");
        userId1.textProperty().addListener((observable, oldValue, newValue) -> {
            LoadData(newValue);
            /*LoadData*/        });

        final VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setAlignment(Pos.BASELINE_LEFT);
        vbox1.getChildren().addAll(name, name1);

        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setAlignment(Pos.BASELINE_LEFT);
        vbox2.getChildren().addAll(userId, userId1);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(vbox2, vbox1);

        Label password = new Label();
        password.setText("Password:");
        password1 = new TextField();
        password1.setPromptText("Password");

        Label majorId = new Label();
        majorId.setText("Major ID:");
        majorId1 = new TextField();
        majorId1.setPromptText("Major Id");

        final VBox vbox3 = new VBox();
        vbox1.setSpacing(5);
        vbox1.setAlignment(Pos.BASELINE_LEFT);
        vbox1.getChildren().addAll(password, password1);

        final VBox vbox4 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setAlignment(Pos.BASELINE_LEFT);
        vbox2.getChildren().addAll(majorId, majorId1);

        final HBox hb1 = new HBox();
        hb1.setSpacing(5);
        hb1.setAlignment(Pos.BASELINE_LEFT);
        hb1.getChildren().addAll(vbox3, vbox4);

        Label address = new Label();
        address.setText("Address:");
        address1 = new TextField();
        address1.setPromptText("Adress ");
        address1.setMaxWidth(100);

        /*
         address2 = new TextField();
         address2.setPromptText("Address Line 1");
         address2.setMaxWidth(250);
         address3 = new TextField();
         address3.setPromptText("Address Line 2");
         address3.setMaxWidth(250);
         city = new TextField();
         city.setPromptText("City");
         city.setMaxWidth(100);
         state = new TextField();
         state.setPromptText("State");
         state.setMaxWidth(100);
         zip = new TextField();
         zip.setPromptText("ZIP");
         zip.setMaxWidth(100);
         */
        final HBox hb2 = new HBox();
        hb2.setSpacing(5);
        hb2.setAlignment(Pos.BASELINE_LEFT);
        hb2.getChildren().addAll(address);

        final VBox vb1 = new VBox();
        vb1.setSpacing(5);
        vb1.setAlignment(Pos.BASELINE_LEFT);
        vb1.getChildren().addAll(address1);

        final VBox vb = new VBox();
        vb.setMaxWidth(500);
        vb.setSpacing(5);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().addAll(hb, hb1, hb2, vb1);
        studentPane.getChildren().addAll(vb);
        return studentPane;
    }

    private Node menuBar() {

        return null;
    }

    private void LoadData(String newValue) {
        try {
            CurrentStudent = MyController.LoadStudent(newValue);
            address1.setText(CurrentStudent.getAddress());
            password1.setText(CurrentStudent.getPassword());
            name1.setText(CurrentStudent.getName());
            majorId1.setText(CurrentStudent.getMajorId());
            allData.clear();
            allData.addAll(CurrentStudent.getCourses());
        } catch (Exception ex) {
        }
    }

}
