package Fx;

import java.util.List;

import Class.Major;
import Class.Student;
import Class.clsCourseMajor;
import Class.clsCourseStudent;
import Controller.MajorController;
import Controller.StudentController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class clsMajor extends Application {

    ObservableList<clsCourseMajor> allData = FXCollections.observableArrayList();
    MajorController MyController = new MajorController();
    Major CurrentMajor = new Major();
    List selectedRow = null;
    SearchView sview;
    public LoginView lv = new LoginView();

    boolean isStudent = false;
    public String username = null;

    private BorderPane root;
    private TableView table = new TableView();
    TextField title1;
    TextField id1;
    TextField totalCredit1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Final Project");
        root = new BorderPane();
        Scene scene = new Scene(root, 1000, 400);
        VBox top = new VBox(10);
        top.setAlignment(Pos.BASELINE_CENTER);
        root.setTop(top);
        top.getChildren().addAll(majorPane());

        HBox bottom = new HBox(10);
        bottom.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(bottom);

        stage.setScene(scene);
        stage.show();
    }

    public HBox majorPane() {
        HBox majorPane = new HBox();
        majorPane.setPadding(new Insets(10, 10, 10, 10));
        majorPane.setMinWidth(650);
        //		        table.setEditable(true);
        //		        TableColumn majorCrs = new TableColumn("MajorCrs");
        //		        majorCrs.setMinWidth(110);
        //		        TableColumn engCrs = new TableColumn("EngCrs");
        //		        engCrs.setMinWidth(110);
        //		        TableColumn sciCrs = new TableColumn("ScienceCrs");
        //		        sciCrs.setMinWidth(110);
        //		        TableColumn soSciCrs = new TableColumn("SocialSciCrs");
        //		        soSciCrs.setMinWidth(110);
        //		        TableColumn humCrs = new TableColumn("HumCrs");
        //		        humCrs.setMinWidth(110);
        //		        TableColumn pedCrs = new TableColumn("PedCrs");
        //		        pedCrs.setMinWidth(110);
        //		        TableColumn matCrs = new TableColumn("MathCrs");
        //		        matCrs.setMinWidth(110);
        //		        table.getColumns().addAll(majorCrs, engCrs, sciCrs, soSciCrs, humCrs, pedCrs, matCrs);
        //		        table.setMaxHeight(200);
        //		        table.setMaxWidth(650);

        table.setEditable(true);
        TableColumn courseNum2 = new TableColumn("CourseNum");
        courseNum2.setMinWidth(110);
        courseNum2.setCellValueFactory(
                new PropertyValueFactory<clsCourseMajor, String>("courseno"));

        TableColumn courseTitle = new TableColumn("Type");
        courseTitle.setMinWidth(110);
        courseTitle.setCellValueFactory(
                new PropertyValueFactory<clsCourseMajor, String>("type"));

        table.getColumns().addAll(courseNum2, courseTitle);
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

        ComboBox<String> selectFile = new ComboBox<String>();
        selectFile.getItems().addAll("MajorCrs", "EngCrs", "ScienceCrs", "SocialSciCrs", "HumCrs", "PedCrs", "MathCrs");

        Label courseNum = new Label("CourseNum: ");
        TextField courseNum1 = new TextField();
        courseNum1.setPromptText("Course Num");
        HBox hb4 = new HBox();
        hb4.setSpacing(25);
        hb4.setAlignment(Pos.CENTER);
        hb4.getChildren().addAll(courseNum, courseNum1, selectFile);

        Button add = new Button("Add");
        Button delete = new Button("Delete");
        Button edit = new Button("Edit");

        Label title = new Label("Title:");
        title1 = new TextField();
        title1.setPromptText("Title");
        Label id = new Label("ID:");
        id1 = new TextField();
        id1.setPromptText("MajorId");

        id1.textProperty().addListener((observable, oldValue, newValue) -> {
            LoadData(newValue);
            /*LoadData*/        });

        Label totalCredit = new Label("Total Credit:");
        totalCredit1 = new TextField();
        totalCredit1.setPromptText("Total Credit");

        add.setOnAction((ActionEvent e) -> {
            Major obj = new Major();
            obj.setTitle(title1.getText());
            obj.setMajorid(id1.getText());
            obj.setTotalcredits(totalCredit1.getText());
            MyController.AddCourse(obj, courseNum1.getText(), selectFile.getValue());
            allData.add(new clsCourseMajor(courseNum1.getText(), selectFile.getValue()));

        });
        delete.setOnAction(e -> {
            clsCourseMajor obj = (clsCourseMajor) selectedRow.get(0);
            System.out.println("Selected Value" + obj.getCourseno());
            MyController.DeleteCourse(CurrentMajor, obj);
            allData.remove(obj);
        });

        HBox hb2 = new HBox();
        hb2.setSpacing(25);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(title, title1, id, id1, totalCredit, totalCredit1, add, delete, edit);
        hb2.setAlignment(Pos.BOTTOM_CENTER);

        Button Save = new Button("Save");
        Button exit = new Button("Exit");

        Save.setOnAction(e -> {
            Major obj = new Major();
            obj.setTitle(title1.getText());
            obj.setMajorid(id1.getText());
            obj.setTotalcredits(totalCredit1.getText());
            MyController.SaveMajor(obj);
        });

        exit.setOnAction(e -> {
//        	lv.start(primaryStage);
            System.exit(0);
        });
        
        if (isStudent) {
            id1.setText(username);
            LoadData(id1.getText());
            Save.setDisable(true);
            add.setDisable(true);
            delete.setDisable(true);
            edit.setDisable(true);
        }
        
        HBox hb3 = new HBox();
        hb3.setSpacing(25);
        hb3.setAlignment(Pos.BOTTOM_RIGHT);
        hb3.getChildren().addAll(Save, exit);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hb2, hb4, table, hb3);
        vbox.setAlignment(Pos.CENTER);
        majorPane.getChildren().addAll(vbox);
        return majorPane;
    }

    private void LoadData(String newValue) {
        try {
            CurrentMajor = MyController.LoadMajor(newValue);
            title1.setText(CurrentMajor.getTitle());
            id1.setText(CurrentMajor.getMajorid());
            totalCredit1.setText(CurrentMajor.getTotalcredits());
            allData.clear();
            allData.addAll(CurrentMajor.getMajorCourses());
        } catch (Exception ex) {
        }
    }

}
