package SQL;

import SQL.Exception.EmployeeNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    static String url="jdbc:postgresql://localhost:5432/companydb";
    static String userName="companyuser";
    static String password="companyuser";
    private static Connection conn;
    //1ere etape cree la connexion

    public static Connection getDbConnecion() {

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/companydb", "companyuser", "companyuser");
            System.out.println("connection has been established");
//            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
//        try(Connection conn=DriverManager.getConnection(url,userNmae,password))
//        {
//            System.out.println("Connection established");
//        }
//        catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//        return conn;
//    }

    public static void createTableEmployee(){
        try{
            //1ere etape : establish connection
            Connection con = getDbConnecion();
            //2eme etape :ecrire la requete
            String request="create table employee("+
                    "id bigint primary key,"+
                    "name varchar not null,"+
                    "salary real,"+
                    "manager_id int null,"+
                    "hiredate date,"+
                    "birthdate date"+
                    ")";
            //foreign key not not
            //3eme etape cree statement
            PreparedStatement ps=con.prepareStatement(request);
            //4eme etape : execute sql query
            ps.executeUpdate();
            System.out.println("The employee table has been successfully created");


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static void closeConnection()throws SQLException
    {
        conn.close();
    }
    public static boolean AjoutEmployee(Employee e) throws SQLException {
        //n'est pas static car il va modifier les attribut

        try {
            Connection con = getDbConnecion();
            //methode 1:
//            String request="insert into Employee " +
//                    "values("+5+","+"Ahmed"+","+1800+","+3+","+"14/10/1990"+","+"15/11/2012"+")";
            //methode 2:
            String request="insert into Employee " +
                    "values(?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(request);
            ps.setLong(1,e.getId());
            ps.setString(2,e.getName());
            ps.setDouble(3,e.getSalary());
            ps.setInt(4,e.getManagerID());
            ps.setObject(5,e.getHireDate());
            ps.setObject(6,e.getBithdate());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            closeConnection();
            return false;
        }

    }
    public void suppEmployee(){

    }
    public static List<Employee>getAllEmployee()throws SQLException {
        List<Employee> lstEmployee = new ArrayList<>();
        try {
            Connection con = getDbConnecion();
            String request = "select * from employee";
            PreparedStatement ps = con.prepareStatement(request);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //create Employee
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                //cast pour la date
                employee.setBithdate(rs.getObject("birthdate", LocalDate.class));
                employee.setHireDate(rs.getObject("hiredate", LocalDate.class));
                employee.setSalary(rs.getDouble("salary"));
                employee.setManagerID(rs.getInt("manager_id"));
                lstEmployee.add(employee);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return lstEmployee;
    }

    public static void deleteEmployee(int id)throws SQLException {
    try{
        Connection con= getDbConnecion();
        String request = "Select id from where id = ?";
        //ken je string naamlou "'" + "'"
        PreparedStatement ps = con.prepareStatement(request);
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();
        boolean r = rs.next();
        if(!r){
            throw new EmployeeNotFoundException("There's no employee with that ID");
        }
        ps.close();
            request = "delete from employee where id = "+id;
            ps = con.prepareStatement(request);
            ps.executeUpdate();
            System.out.println("Employee with id = " + id +" has been successfully deleted.");
            ps.close();
    }
    catch(SQLException e){
        System.err.format("SQL State: " + e.getSQLState());
    }
    catch (EmployeeNotFoundException e){
        System.out.println(e.getMessage());
    }
    finally {
        closeConnection();
    }
    }


    }
