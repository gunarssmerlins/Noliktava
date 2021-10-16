package pack;

import javafx.collections.ObservableList;

import java.sql.*;

public class mysqlConnection {

    public Connection connection;

    //nestrādā
//    public ObservableList listColumn (String sqlquerry, String col) throws SQLException {
//        ObservableList list = null;
//
//        Connection e = getConnection();
//
//        Statement pst = null;
//        ResultSet rs = null;
//        try {
//            pst = connection.prepareStatement(sqlquerry);
//            rs = pst.executeQuery(sqlquerry);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        while (true) {
//            try {
//                if (!rs.next()) break;
//                list.add(rs.getString(col));
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//        try {
//            pst.close();
//            rs.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        e.close();
//        return list;
//    }


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


    // jaunā funkcija
    public String sqlQuerry, column;

    public ObservableList executeQueryForList (String sqlQuerry, String column) throws SQLException {

        ObservableList list = null;

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/noliktava";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Magnum41285127");
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();

        //Retrieving data
        ResultSet rs = stmt.executeQuery(sqlQuerry);

        while (true) {
            try {
                if (!rs.next()) break;
                list.add(rs.getString("user"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
