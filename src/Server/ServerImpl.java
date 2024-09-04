package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
    List<Student> studentList = new ArrayList<>();

    public ServerImpl() throws RemoteException {
        this.studentList = loadStudentsFromFile("C:\\Users\\diego\\Desktop\\RMI_Lab\\src\\Server\\DatosEstudiantes.txt");
    }

    @Override
    public String getStudentNameById(int idStudent) throws RemoteException {
        for (Student student : studentList) {
            if (student.getIdStudent() == idStudent) {
                return student.getStudentName();
            }
        }
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    @Override
    public void createStudent(String groupID, Integer idStudent, String studentName, Double gradeActivity1, Double gradeActivity2) throws RemoteException {
        if (groupID == null || groupID.isEmpty() ||
                idStudent == null ||
                studentName == null || studentName.isEmpty() ||
                gradeActivity1 == null ||
                gradeActivity2 == null) {
            throw new IllegalArgumentException("One or more parameters are empty");
        }
        Student newStudent = new Student(groupID,idStudent, studentName, gradeActivity1, gradeActivity2);
        studentList.add(newStudent);
        System.out.println("Student added successfully");
    }

    @Override
    public Double gradeAvgById(int idStudent) throws RemoteException {
        for(Student student : studentList ) {
            if( student.getIdStudent() == idStudent ) {
                return (student.getGradeActivity1()+student.getGradeActivity2())/2;
            }
        }
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    @Override
    public String findGroupByIdStudent(int idStudent) throws RemoteException {
        for(Student student : studentList ) {
            if( student.getIdStudent() == idStudent ) {
                return student.getGroupID();
            }
        }
        throw new RemoteException("Student with id " + idStudent + " not found.");
    }

    @Override
    public String findGroupMembersById(String idGroup) throws RemoteException {
        StringBuilder groupMembers = new StringBuilder("Group => " + idGroup + "\n");
        boolean groupFound = false;
        for( Student student : studentList ) {
            if( student.getGroupID().equals(idGroup)  ) {
                groupMembers.append("Member => ").append(student.getStudentName()).append("\n");
                groupFound = true;
            }
        }

        if (!groupFound) {
            throw new RemoteException("No members found for group " + idGroup);
        }

        return groupMembers.toString();
    }

    @Override
    public String gradesMeanAndDeviation(int activityID) throws RemoteException {
        double mean = getMean(activityID);
        double standardDeviation = 0.0;
        for(Student student : studentList) {
            if( activityID == 1 ){
                    standardDeviation += Math.pow(student.getGradeActivity1() - mean, 2);
            } else if ( activityID == 2 ) {
                    standardDeviation += Math.pow(student.getGradeActivity2() - mean, 2);
            }else{
                throw new RemoteException("Activity "+ activityID + "not found");
            }
        }
        return String.format("The mean was %.2f and the standard deviation was %.2f", mean, standardDeviation);
    }

    private double getMean(int activityID) throws RemoteException {
        Double sumGrades = 0.0;
        for(Student student : studentList ){
            if(activityID == 1){
                sumGrades += student.getGradeActivity1();
            }else if (activityID == 2 ) {
                sumGrades += student.getGradeActivity1();
            }else{
                throw new RemoteException("Activity "+ activityID + "not found");
            }

        }
        return  sumGrades / studentList.size();
    }

    public List<Student> loadStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" "); // assuming the file has space-separated values
                String groupID = parts[0];
                Integer idStudent = Integer.parseInt(parts[1]);
                String studentName = parts[2];
                Double gradeActivity1 = Double.parseDouble(parts[3]);
                Double gradeActivity2 = Double.parseDouble(parts[4]);
                Student student = new Student(groupID, idStudent, studentName, gradeActivity1, gradeActivity2);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
