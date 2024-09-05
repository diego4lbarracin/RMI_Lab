package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
// This interface defines the methods that can be invoked remotely in the RMI server
public interface ServerInterface extends Remote {
    // Method to get a student's name by their ID
    public String getStudentNameById(int idStudent) throws RemoteException;

    // Method to create a new student with the given parameters
    public void createStudent(String groupID, Integer idStudent, String studentName, Double gradeActivity1, Double gradeActivity2) throws RemoteException;

    // Method to get a student's average grade by their ID
    public Double gradeAvgById(int idStudent) throws RemoteException;

    // Method to find a student's group by their ID
    public String findGroupByIdStudent(int idStudent) throws RemoteException;

    // Method to find all members of a group by the group's ID
    public String findGroupMembersById(String idGroup) throws RemoteException;

    // Method to calculate the mean and standard deviation of grades for a given activity
    public String gradesMeanAndDeviation(int activityID) throws RemoteException;
}