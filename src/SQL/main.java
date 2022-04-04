package SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static SQL.DBConnection.*;

public class main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Employee e= new Employee();
        int choice;
    do {
        System.out.println("1-Create table employé");
        System.out.println("2-Ajouter un employé");
        System.out.println("3-Afficher tous les employés");
        System.out.println("4-Modifier un employé");
        System.out.println("5-Quitter");
        choice = sc.nextInt();



        switch(choice) {
            case 1:
                DBConnection.createTableEmployee();
                DBConnection.closeConnection();
                break;
            case 2:
                Employee el = new Employee();
                el.getEmployee();
                DBConnection.insertEmployee(el);
                System.out.println("Employee with ID = "+el.getId()+"est ajouté");
                break;
            case 3:
                List<Employee> ls=DBConnection.getAllEmployee();
                System.out.println("List of employees gotten");
                for (Employee empl : ls){
                    System.out.println(empl);
                }
        }
    }while (choice!=5);
    }
}