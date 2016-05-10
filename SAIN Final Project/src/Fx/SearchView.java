package Fx;

import Class.Student;
import Class.clsCourseStudent;
import Controller.SanViewController;
import Controller.StudentController;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchView extends Application {

    clsMajor major;
    addStudentView edit1;
    clsCourse crs1;
    public Stage window;
    Label txtName;
    Label txtAddress;
    Label txtGpa;
    Label txtMajor;
    TextField input;
    private TableView table = new TableView();
    ObservableList<clsCourseStudent> allData = FXCollections.observableArrayList();
    FilteredList<clsCourseStudent> filteredList = new FilteredList<>(allData);
    ComboBox<String> selectFile;
    Student CurrentStudent = new Student();
    SanViewController MyController = new SanViewController();

    public static void main(String[] args) {
        launch(args);
    }
    boolean isStudent = false;
    String username = null;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Search View");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label label = new Label("ID:");
        GridPane.setConstraints(label, 0, 0);

        input = new TextField();
        input.setPromptText("ID");
        GridPane.setConstraints(input, 1, 0);

        Button ok = new Button("Find");
        GridPane.setConstraints(ok, 1, 1);

        ok.setOnAction(e -> {
            LoadData(input.getText());
        });

        Label lblName = new Label("Name:");
        txtName = new Label("Name");
        txtName.setText("Name");
        Label lblAddress = new Label("Address:");
        txtAddress = new Label("Address");
        txtAddress.setText("Address");
        Label lblGpa = new Label("Gpa:");
        txtGpa = new Label("GPA");
        txtGpa.setText("GPA");
        Label lblMajor = new Label("Major:");
        txtMajor = new Label("Major Name");

        table.setEditable(true);
        TableColumn courseNum = new TableColumn("Course");
        courseNum.setMinWidth(110);
        courseNum.setCellValueFactory(
                new PropertyValueFactory<clsCourseStudent, String>("coursetitle"));

        edit1 = new addStudentView();
        Button edit = new Button("Edit");
        edit.setOnAction(e -> {
        	 try {
                 if (!isStudent) {
                     edit1.start(primaryStage);
                 } else {
                     edit1.isStudent = true;
                     edit1.username = input.getText();
                     edit1.start(primaryStage);
                 }

             } catch (Exception e1) {
                 e1.printStackTrace();
             }
        });

        major = new clsMajor();
        Button addMajor = new Button("Add Major");
        addMajor.setOnAction(e -> {
            try {
                if (!isStudent) {
                    major.start(primaryStage);
                } else {
                    major.isStudent = true;
                    major.username = input.getText();
                    major.start(primaryStage);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        crs1 = new clsCourse();
        Button courseButton = new Button("Add Course");
        courseButton.setOnAction(e -> {
        	 try {
                 if (!isStudent) {
                     crs1.start(primaryStage);
                 } else {
                     crs1.isStudent = true;
                     crs1.start(primaryStage);
                 }

             } catch (Exception e1) {
                 e1.printStackTrace();
             }
        });

        TableColumn courseTitle = new TableColumn("Type");
        courseTitle.setMinWidth(110);
        courseTitle.setCellValueFactory(
                new PropertyValueFactory<clsCourseStudent, String>("type"));

        table.getColumns().addAll(courseNum, courseTitle);
        table.setMaxHeight(200);
        table.setMaxWidth(430);
        table.setItems(filteredList);

        Label lblType = new Label("Course:");
        selectFile = new ComboBox<String>();
        selectFile.getItems().addAll("Taking", "Taken", "Needed", "Failed");
        selectFile.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                filteredList.setPredicate(
                        new Predicate<clsCourseStudent>() {
                            public boolean test(clsCourseStudent t) {
                                if (t.getType().equalsIgnoreCase(t1)) {
                                    return true;
                                }
                                return false;
                            }
                        }
                );
            }
        });
        GridPane.setConstraints(lblName, 0, 2);
        GridPane.setConstraints(txtName, 1, 2);
        GridPane.setConstraints(lblAddress, 0, 3);
        GridPane.setConstraints(txtAddress, 1, 3);
        GridPane.setConstraints(lblGpa, 0, 4);
        GridPane.setConstraints(txtGpa, 1, 4);
        GridPane.setConstraints(lblMajor, 0, 5);
        GridPane.setConstraints(txtMajor, 1, 5);
        GridPane.setConstraints(lblType, 0, 6);
        GridPane.setConstraints(selectFile, 1, 6);
        GridPane.setConstraints(courseButton, 1, 8);
        GridPane.setConstraints(addMajor, 2, 8);
        GridPane.setConstraints(table, 1, 9);
        GridPane.setConstraints(edit, 1, 7);

        grid.getChildren().addAll(label, input, ok, lblName, lblAddress, lblGpa, txtAddress, txtGpa, txtName, lblMajor,
                txtMajor, table, lblType, selectFile, edit, courseButton, addMajor);

        if (isStudent) {
            input.setText(username);
            LoadData(input.getText());
            ok.setDisable(true);
        }
        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);
        window.show();
    }

    private void LoadData(String text) {
        try {
            CurrentStudent = MyController.LoadStudent(text);
            txtAddress.setText(CurrentStudent.getAddress());
            txtGpa.setText(String.valueOf(CurrentStudent.getGpa()));
            txtName.setText(CurrentStudent.getName());
            txtMajor.setText(CurrentStudent.getMajorName());
            allData.clear();
            allData.addAll(CurrentStudent.getCourses());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
