package SQL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
        Scanner s = new Scanner(System.in);
        private int id;
        private String name;
        private LocalDate birthdate;
        private BigDecimal salary;
        private LocalDate hiredate;
        private int managerId;
        //declaration du format du date
        String dateFormat="yyyy-MM-dd";
        //private int deptNo;

        //get employee's attribute values from the keyboard
        public void getEmployee(){

                System.out.println("Donner l'ID de l'employee");
                int id = s.nextInt();
                this.id = id;

                System.out.println("Donner les nom de employee");
                String nom = s.nextLine();
                this.name=nom;

                System.out.println("Donner la date de naissance de l'employee");
                String date = s.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);  //format de la date
                this.birthdate= LocalDate.parse(date, formatter);

                System.out.println("Donner le salaire de l'employee");
                BigDecimal salaire = s.nextBigDecimal();
                this.salary=salaire;

                System.out.println("Donner la date d'embauche de l'employee");
                String date2 = s.next();
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(dateFormat);  //format de la date
                this.hiredate= LocalDate.parse(date2, formatter2);

                System.out.println("Donner l'ID du manager de l'employee");
                int managerId = s.nextInt();
                this.managerId=managerId;
        }


}