package main.java.com.Brendan.other;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static Employee current = new Employee();
    public static String ID;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        String title= "TTMS";
        primaryStage.setTitle(title);
        Stage popUp = new Stage();
        popUp.setTitle(title);
        popUp.centerOnScreen();
        popUp.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent popup;

        popup = fxmlLoader.load(getClass().getResource("/fxml/login2.fxml").openStream());

        popUp.setScene(new Scene(popup));

        popUp.show();

    }

}
