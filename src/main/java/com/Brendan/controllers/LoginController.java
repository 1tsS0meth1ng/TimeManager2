package main.java.com.Brendan.controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.com.Brendan.other.Employee;
import main.java.com.Brendan.other.Main;

import java.io.IOException;
import java.sql.*;


public class LoginController extends GridPane {
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginID;
    @FXML
    private PasswordField loginPass;
    @FXML
    private Label dialog;
    @FXML
    private AnchorPane loading;
    @FXML
    private GridPane loginScreen;

    private static boolean isadmin;

    private Main app;
    private String iD;
    public int compLogin;

    @FXML
    public void handleSubmitButtonAction(ActionEvent e) throws InterruptedException {
        loading.toFront();
        loading.setVisible(true);

        compLogin = compareLogin(loginID.getText(), loginPass.getText());
        Stage main = new Stage();
        dialog.setText("Attempting to log in");
        Stage stage = (Stage) loginButton.getScene().getWindow();

        switch (compLogin) {
            case 0:
                loading.toBack();
                loading.setVisible(false);
                dialog.setText("Sorry that is not a valid login");
                break;
            case 1:
                //dialog.setText("Welcome");

                Connection con = getSQLConnection();
                try {
                    Statement s = con.createStatement();
                    String getUser = "SELECT * FROM employeedetails WHERE EmployeeID ='" + iD + "';";
                    ResultSet row = s.executeQuery(getUser);

                    String fName = null, mName = null, sName = null, adress = null, SDate = null, Edate = null, role = null, position = null;

                    while (row.next()) {
                        fName = row.getString("FirstName");
                        mName = row.getString("MiddleNames");
                        sName = row.getString("Surname");
                        adress = row.getString("Address");
                        SDate = row.getString("StartDate");
                        Edate = row.getString("EndDate");
                        role = row.getString("Role");
                        position = row.getString("Position");
                    }

                    app.current = new Employee(iD, fName, mName, sName, adress, SDate, Edate, role, position, isadmin);


                    try {
                        Parent here = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                        Scene scene = new Scene(here);
                        main.setScene(scene);
                        main.show();
                        stage.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } catch (SQLException exep) {
                    exep.printStackTrace();
                }




                break;
            case 2:
                dialog.setText(null);
                try {
                    app.ID = iD;

                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/firstTimeLogin.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                break;
            case 3:
                loading.toBack();
                loading.setVisible(false);
                dialog.setText("HAHAH you have no power here");
                break;
            case 4:
                loading.toBack();
                loading.setVisible(false);
                dialog.setText("got an sql exception.My bad");
                break;

        }

    }

    protected static Connection getSQLConnection() {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/pat";
            String user = "Brendan";
            String password = "02Ruffies";
            con = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e2) {
            e2.getException();
            System.out.println(e2.getMessage() + " failed");
            System.exit(0);
        }
        catch (SQLException e1) {

            System.out.println(e1.getMessage());
            System.exit(0);
        }
        return con;
    }

    private static int compareLogin(String userlog, String userpass) {

        Connection con = getSQLConnection();
        boolean login = false;
        int returnValue = 0;
        try
        {
            Statement s = con.createStatement();
            String select = "SELECT * FROM EMPLOYEES;";
            ResultSet rows;
            rows = s.executeQuery(select);

            while (rows.next()) {
                if (userlog.equalsIgnoreCase(null) || userpass.equalsIgnoreCase(null))
                {
                        login = false;
                        break;
                }

                String EmployeeID = rows.getString("EmployeeID");
                boolean loginBefore = Boolean.parseBoolean(rows.getString("LoginBefore"));
                isadmin = Boolean.parseBoolean(rows.getString("Admin"));

                if (EmployeeID.equalsIgnoreCase(userlog))
                {
                    String Password = rows.getString("Password");
                    if (Password.equals(userpass))
                    {
                        String Status = rows.getString("Status");

                        if (!loginBefore)
                        {
                            returnValue = 2;
                            login = true;
                            break;
                        }

                        if (Status.equalsIgnoreCase("terminated")) {
                            returnValue = 3;
                            login = true;
                            break;
                        }
                        login = true;
                        returnValue = 1;
                        break;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getErrorCode());
            System.exit(0);
            return 4;
        }

        if (!login)
        {
            returnValue = 0;
        }
        return returnValue;


    }
}

