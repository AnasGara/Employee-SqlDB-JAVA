package SQL;

import java.sql.SQLException;

import static SQL.DBConnection.*;

public class main {

    public static void main(String[] args) throws SQLException {
        DBConnection.createTableEmployee();
        DBConnection.closeConnection();

        Employee e= new Employee();
        //create Employe
        //affectih lil e

        boolean db = DBConnection.insertEmployee(e);
    }
}