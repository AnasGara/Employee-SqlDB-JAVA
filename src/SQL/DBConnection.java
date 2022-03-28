package SQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static String url="jdbc:postgresql://localhost:5432/companydb";
    private static String userName="companyuser";
    private static String password="companyuser";
    private static Connection conn;
    public static Connection getDbConnection()
    {

        try
        {
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection has been established");
            // conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTableEmployee()
    {
        try {
            //step 1 : establish connection
            Connection con=getDbConnection();
            //step 2-Write the sql request
            String request ="create table Employee ("+
                    "id bigint primary key,"+
                    "name varchar not null,"+
                    "birthdate date,"+
                    "salary real,"+
                    "hiredate date,"+
                    "mgr_id int null"+
                    ")";
            //step 3 : create Statement
            PreparedStatement ps=con.prepareStatement(request);
            //step 4 : Execute sql Query
            ps.executeUpdate();
            System.out.println("The Employee table has been successfully created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection() throws SQLException {
        conn.close();
    }


    public static boolean insertEmployee(Employee emp) throws SQLException {
        try{

            Connection con = getDbConnection();
            String req = "insert into Employee values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,emp.getId());
            ps.setString(2,emp.getName());
            ps.setObject(3,emp.getBirthdate());
            ps.setBigDecimal(5,emp.getSalary());
            ps.setObject(5,emp.getHiredate());
            ps.setInt(6,emp.getManagerId());
            ps.executeUpdate();
            ps.close();
            System.out.println("Exécuté");
            return true;

        }catch (SQLException  er){
            System.out.println(er.getErrorCode());
            return false;
        }
        finally{
            if(conn!=null){
                closeConnection();
            }
        }
    }


}