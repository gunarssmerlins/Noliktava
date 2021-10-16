package pack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Collections;

public class dbConnect {
    public dbConnect(ObservableList list) {
        this.list = list;
    }

    public void setList(ObservableList list) {
        this.list = list;
    }

    private ObservableList<String> list = FXCollections.observableArrayList();

    public dbConnect() {

    }

    //ielasam comboboksī kolonnu
    public ObservableList getObservableListFromColumn(String dbQuery, String column) throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost/noliktava";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Magnum41285127");
        System.out.println("Ir savienojums");
        Statement stmt = con.createStatement();

        //Ielasa ierakstus no db
        ResultSet rs = stmt.executeQuery(dbQuery);
        while(rs.next()) {
            list.add(rs.getString(column));
            Collections.sort(list);
        }

        System.out.println(list);

        rs.close();
        stmt.close();
        con.close();
        return list;
    }


}
