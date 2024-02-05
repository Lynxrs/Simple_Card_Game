package org.Lynx.main;

import java.sql.*;
import java.util.Scanner;

import static org.Lynx.main.Main.print;

public class SQL {
        Connection connection = null;


    public void registerUser(){
        Connection connection = null;
        Scanner sc = new Scanner(System.in);

        print("Enter a username.");
        String Username = sc.next();
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM Player where name = "+Username);
            if (Username.equals(resultSet.getString("name"))){

                print("This name is already taken.");
                registerUser();
            }else{
                statement.executeUpdate("INSERT INTO Player (name,passwrd,level) VALUES ('Exemple','test',1)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
