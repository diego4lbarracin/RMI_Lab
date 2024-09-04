package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    public String getStudentNameById(int idStudent) throws RemoteException;
    public void createStudent(String groupID, Integer idStudent, String studentName, Double gradeActivity1, Double gradeActivity2) throws RemoteException;
    public Double gradeAvgById(int idStudent) throws RemoteException;
    public String findGroupByIdStudent(int idStudent) throws RemoteException;
    public String findGroupMembersById(String idGroup) throws RemoteException;
    public String gradesMeanAndDeviation(int activityID) throws RemoteException;
}
