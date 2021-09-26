package pack;

import javafx.collections.ObservableList;

import java.sql.*;

public class mysqlConnection {

    public Connection connection;

    //nestrādā
    public ObservableList listColumn (String sqlquerry){
        ObservableList list = null;

        Connection e = getConnection();

        Statement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(sqlquerry);
            rs = pst.executeQuery(sqlquerry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while (true) {
            try {
                if (!rs.next()) break;
                list.add(rs.getString("user"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public Connection getConnection(){

        String dbName = "noliktava";
        String userName = "root";
        String password = "Magnum41285127";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
