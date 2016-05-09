package Fx;

import Class.Staff;
import Controller.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginView extends Application {

    Stage window;
    ComboBox<String> selectFile;
    LoginController MyController = new LoginController();
    SearchView sv = new SearchView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Login View");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label labelType = new Label("User Type:");
        GridPane.setConstraints(labelType, 0, 0);

        selectFile = new ComboBox<String>();
        selectFile.getItems().addAll("Staff", "User");
        GridPane.setConstraints(selectFile, 1, 0);

        Label label = new Label("UserName:");
        GridPane.setConstraints(label, 0, 1);

        TextField input = new TextField();
        input.setPromptText("Useranme");
        GridPane.setConstraints(input, 1, 1);

        Label label1 = new Label("Password:");
        GridPane.setConstraints(label1, 0, 2);

        TextField input1 = new TextField();
        input1.setPromptText("Password");
        GridPane.setConstraints(input1, 1, 2);

        selectFile = new ComboBox<String>();
        selectFile.getItems().addAll("Staff", "Student");
        
        

        Button login = new Button("Login");
        GridPane.setConstraints(login, 1, 3);
        login.setOnAction(e -> {
            boolean flag = false;
            if (selectFile.getValue().equals("Staff")) {
                flag = MyController.VailidateStaff(input.getText(), input1.getText());
            } else if (selectFile.getValue().equals("Student")) {
                flag = MyController.VailidateStudent(input.getText(), input1.getText());
            } else if (selectFile == null){
                	input.setText("Select SorF");
            }
            if (flag) {
            	try {
					sv.start(window);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            } else {
            	try {
					sv.start(window);
				} catch (Exception e1) {
					sv.input.setDisable(true);
					e1.printStackTrace();
				}
            	
            }
          
        });

        grid.getChildren().addAll(label, label1, input, input1, login, labelType, selectFile);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
    }

}
