package main.java.com.Brendan.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.com.Brendan.other.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static main.java.com.Brendan.controllers.LoginController.getSQLConnection;


public class FirstTimeLoginController implements Initializable
{
    @FXML
    private TextField firstName, middName, surname;
    @FXML
    private Button confirmButton;

    private Main app;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }



    public void setApp(Main app)
    {
        this.app = app;
    }
   // private Employee curr = app.getCurrent();
    private String iD= app.ID, firstN, middn, surn;

    @FXML public void handelConfrimPress(ActionEvent e)
    {
        firstN = firstName.getText();
        middn = middName.getText();
        surn = surname.getText();
        boolean isAlready = false;
        Connection con = getSQLConnection();

        try
        {
            Statement s = con.createStatement();
            String stateUp = "UPDATE";
            String stateIN = "insert into employeedetails (EmployeeID,FirstName,MiddleNames,Surname, employeedetailscol)" +
                    "values (:employeeid,:firstname,:middlenames,:surname,:employeedetailscol);";
            String stateCheck= "SELECT * from employeedetails;";
            ResultSet resChec = s.executeQuery(stateCheck);

            while (resChec.next()&& isAlready == false)
            {
                String EmployeeID = resChec.getString("EmployeeID");
                if(EmployeeID.equalsIgnoreCase(iD))
                {
                    isAlready = true;
                }
            }
            if(isAlready)
            {
                s.executeUpdate(stateUp);
            }
            else
            {
                s.execute(stateIN);
            }
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
