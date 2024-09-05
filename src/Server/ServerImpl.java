package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

// This class implements the ServerInterface and extends UnicastRemoteObject to allow the server to receive remote method calls
public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    // List to store all the Student objects
    List<Student> studentList = new ArrayList<>();

    // Constructor of the ServerImpl class
    public ServerImpl() throws RemoteException {
        // Load the students from a file and store them in the studentList
        this.studentList = loadStudentsFromFile("DatosEstudiantes.txt");  //Change the path to the file if necessary.
    }

    // Method to get a student's name by their ID
    @Override
    public String getStudentNameById(int idStudent) throws RemoteException {
        // Iterate over the list of students
        for (Student student : studentList) {
            // If the student's ID matches the given ID, return the student's name
            if (student.getIdStudent() == idStudent) {
                return student.getStudentName();
            }
        }
        // If no student with the given ID is found, throw an exception
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    // Method to create a new student and add them to the list
    @Override
    public void createStudent(String groupID, Integer idStudent, String studentName, Double gradeActivity1, Double gradeActivity2) throws RemoteException {
        // Check if any of the parameters are null or empty, and if so, throw an exception
        if (groupID == null || groupID.isEmpty() ||
                idStudent == null ||
                studentName == null || studentName.isEmpty() ||
                gradeActivity1 == null ||
                gradeActivity2 == null) {
            throw new IllegalArgumentException("One or more parameters are empty");
        }
        // Create a new Student object and add it to the list
        Student newStudent = new Student(groupID,idStudent, studentName, gradeActivity1, gradeActivity2);
        studentList.add(newStudent);
        System.out.println("Student added successfully");
    }

    // Method to get a student's average grade by their ID
    @Override
    public Double gradeAvgById(int idStudent) throws RemoteException {
        // Iterate over the list of students
        for(Student student : studentList ) {
            // If the student's ID matches the given ID, calculate and return the average grade
            if( student.getIdStudent() == idStudent ) {
                return (student.getGradeActivity1()+student.getGradeActivity2())/2;
            }
        }
        // If no student with the given ID is found, throw an exception
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    // Method to find a student's group by their ID
    @Override
    public String findGroupByIdStudent(int idStudent) throws RemoteException {
        // Iterate over the list of students
        for(Student student : studentList ) {
            // If the student's ID matches the given ID, return the group ID
            if( student.getIdStudent() == idStudent ) {
                return student.getGroupID();
            }
        }
        // If no student with the given ID is found, throw an exception
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    // Method to find all members of a group by the group's ID
    @Override
    public String findGroupMembersById(String idGroup) throws RemoteException {
        StringBuilder groupMembers = new StringBuilder("Group => " + idGroup + "\n");
        boolean groupFound = false;
        // Iterate over the list of students
        for( Student student : studentList ) {
            // If the student's group ID matches the given group ID, add the student's name to the list of group members
            if( student.getGroupID().equals(idGroup)  ) {
                groupMembers.append("Member => ").append(student.getStudentName()).append("\n");
                groupFound = true;
            }
        }

        // If no members are found for the given group ID, throw an exception
        if (!groupFound) {
            throw new RemoteException("No members found for group " + idGroup);
        }

        // Return the list of group members
        return groupMembers.toString();
    }

    // Method to calculate the mean and standard deviation of grades for a given activity
    @Override
    public String gradesMeanAndDeviation(int activityID) throws RemoteException {
        double mean = getMean(activityID);
        double standardDeviation = 0.0;
        // Iterate over the list of students
        for(Student student : studentList) {
            // Depending on the activity ID, calculate the standard deviation
            if( activityID == 1 ){
                standardDeviation += Math.pow(student.getGradeActivity1() - mean, 2);
            } else if ( activityID == 2 ) {
                standardDeviation += Math.pow(student.getGradeActivity2() - mean, 2);
            }else{
                // If the activity ID is not 1 or 2, throw an exception
                throw new RemoteException("Activity "+ activityID + "not found");
            }
        }
        // Return the mean and standard deviation
        return String.format("The mean was %.2f and the standard deviation was %.2f", mean, standardDeviation);
    }

    // Private method to calculate the mean grade for a given activity
    private double getMean(int activityID) throws RemoteException {
        Double sumGrades = 0.0;
        // Iterate over the list of students
        for(Student student : studentList ){
            // Depending on the activity ID, add the grade to the sum of grades
            if(activityID == 1){
                sumGrades += student.getGradeActivity1();
            }else if (activityID == 2 ) {
                sumGrades += student.getGradeActivity1();
            }else{
                // If the activity ID is not 1 or 2, throw an exception
                throw new RemoteException("Activity "+ activityID + "not found");
            }

        }
        // Return the mean grade
        return  sumGrades / studentList.size();
    }

    // Method to load students from a file and return a list of Student objects
    public List<Student> loadStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        // Try to open the file and read its contents
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // Split the line into parts based on spaces
                String[] parts = line.split(" "); // assuming the file has space-separated values
                String groupID = parts[0];
                Integer idStudent = Integer.parseInt(parts[1]);
                String studentName = parts[2];
                Double gradeActivity1 = Double.parseDouble(parts[3]);
                Double gradeActivity2 = Double.parseDouble(parts[4]);
                // Create a new Student object and add it to the list
                Student student = new Student(groupID, idStudent, studentName, gradeActivity1, gradeActivity2);
                students.add(student);
            }
        } catch (IOException e) {
            // Print any exceptions that occur
            e.printStackTrace();
        }
        // Return the list of students
        return students;
    }
}
