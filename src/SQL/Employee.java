package SQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

        long id;
        String name;
        double salary;
        long managerID;
        //    int depNo;
        LocalDate hireDate;
        LocalDate bithdate;


        public void getEmployee(){
                Scanner sc=new Scanner(System.in);
                System.out.println("id ? =");
                this.id=sc.nextLong();
                System.out.println("name? =");
                sc.nextLine();
                this.name=sc.nextLine();
                System.out.println("Birthdate? =");
                String birthday=sc.next();
                //declaration de la format de la date
                String dateformat="yyyy-MM-dd";
                DateTimeFormatter format= DateTimeFormatter.ofPattern(dateformat);
                this.bithdate=LocalDate.parse(birthday,format);
                System.out.println("hiredate? =");
                String hiredate=sc.next();
                this.hireDate=LocalDate.parse(hiredate,format);
                System.out.println("salary?=");
                this.salary=sc.nextDouble();
                System.out.println("managerID?=");
                this.managerID=sc.nextLong();
        }
}
