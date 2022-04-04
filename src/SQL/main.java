package SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Select a choise");
        System.out.println("1-Create Employee Table");
        System.out.println("2-Add new Employee");
        System.out.println("3-show all employees");
        System.out.println("4-Exist application");

        do {
            choice = sc.nextInt();
        }while (choice < 1 || choice>4);

        switch (choice) {
            case 1:
                DBConnection.createTableEmployee();
                DBConnection.closeConnection();
                break;
            case 2:
                Employee e1 = new Employee();
                e1.getEmployee();
                DBConnection.AjoutEmployee(e1);
                System.out.println("employee with id =" + e1.getId() + "has been successefully added");
                break;
            case 3:
                List<Employee> lstres=DBConnection.getAllEmployee();
                System.out.println("list of employees :");
                for(Employee e : lstres)
                    System.out.println(e);
                break;

        }while (choice != 4);



    }
}
