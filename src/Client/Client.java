package Client;

import Server.ServerInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLOutput;
import java.util.Scanner;

/*
*   Remote Method Invocation
*   Integrantes:
*   -Paula Malagon.
*   -Simon Marquez.
*   -Diego Albarracin.
* */
public class Client {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner sc = new Scanner(System.in);
        int option = 0;
        int value= 0;
        String valueString = "";

        try {
            // Connect to the RMI registry on the specified IP address and port
            Registry myRegistry = LocateRegistry.getRegistry("0.0.0.0", 9999);
            // Look up the remote object in the RMI registry
            ServerInterface serverIn = (ServerInterface) myRegistry.lookup("ServerInterface");

            // Main loop of the client application
            do {
                // Print the menu options
                System.out.println("Welcome to the Interface");
                System.out.println("Choose a correct option : ");
                System.out.println("(1) Get a student's name by its id.");
                System.out.println("(2) Create a new student.");
                System.out.println("(3) Get a student's grade average by its id.");
                System.out.println("(4) Get a student's group by its id.");
                System.out.println("(5) Get group members by its id.");
                System.out.println("(6) Get the mean and standard deviation in grades related to an activity.");
                System.out.println("(7) Exit.");
                System.out.print("=> ");

                // Read the user's choice
                option = sc.nextInt();

                // Perform the chosen action
                switch (option){
                    case 1 -> {
                        // Option 1: Get a student's name by its id
                        System.out.println("*******************************************************************");
                        System.out.print("Type the student's id => ");
                        value = sc.nextInt();
                        System.out.println("The student's name whose id = " + value + " is = " + serverIn.getStudentNameById(value));
                        System.out.println("*******************************************************************");
                    }
                    case 2 -> {
                        // Option 2: Create a new student
                        System.out.println("*******************************************************************");
                        String groupID;
                        int idStudent;
                        String studentName;
                        double gradeActivity1;
                        double gradeActivity2;
                        System.out.println("Enter the required information for creating new student");
                        System.out.println("Group ID => ");
                        groupID = sc.next();
                        System.out.println("Student ID => ");
                        idStudent = sc.nextInt();
                        System.out.println("Student Name => ");
                        studentName = sc.next();
                        System.out.println("Grade Obtained in the Activity 1 => ");
                        gradeActivity1 = sc.nextDouble();
                        System.out.println("Grade Obtained in the Activity 2 => ");
                        gradeActivity2 = sc.nextDouble();
                        serverIn.createStudent(groupID, idStudent, studentName, gradeActivity1, gradeActivity2);
                        System.out.println("*******************************************************************");
                    }
                    case 3 -> {
                        // Option 3: Get a student's grade average by its id
                        System.out.println("*******************************************************************");
                        System.out.print("Type the student's id => ");
                        value = sc.nextInt();
                        System.out.println("The student's current GPA based on its current grades is = " + serverIn.gradeAvgById(value) );
                        System.out.println("*******************************************************************");
                    }
                    case 4 -> {
                        // Option 4: Get a student's group by its id
                        System.out.println("*******************************************************************");
                        System.out.print("Type the student's id => ");
                        value = sc.nextInt();
                        System.out.println("The student = " + value + " group identifier is = " + serverIn.findGroupByIdStudent(value) );
                        System.out.println("*******************************************************************");
                    }
                    case 5 -> {
                        // Option 5: Get group members by its id
                        System.out.println("*******************************************************************");
                        System.out.print("Type the group's id => ");
                        valueString = sc.next();
                        System.out.println("The following are the current members");
                        System.out.println(serverIn.findGroupMembersById(valueString));
                        System.out.println("*******************************************************************");
                    }
                    case 6 -> {
                        // Option 6: Get the mean and standard deviation in grades related to an activity
                        System.out.println("*******************************************************************");
                        System.out.print("Type the activity id (Taller1=1 or Taller2=2) => " );
                        value = sc.nextInt();
                        System.out.println(serverIn.gradesMeanAndDeviation(value));
                        System.out.println("*******************************************************************");
                    }
                    case 7 -> {
                        // Option 7: Exit the application
                        System.out.println("*******************************************************************");
                        System.out.println("You have exited!");
                        System.out.println("*******************************************************************");
                    }
                    default -> {
                        // Invalid option
                        System.out.println("*******************************************************************");
                        System.out.println("Enter a valid option.");
                        System.out.println("*******************************************************************");
                    }
                }
            }while (option!=7); // Continue until the user chooses to exit
        } catch (Exception e){
            // Print any exceptions that occur
            System.out.println("Error "+ e);
        }
    }
}
