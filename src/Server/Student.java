package Server;

public class Student {
    private String groupID;
    private Integer idStudent;
    private String studentName;
    private Double gradeActivity1;
    private Double gradeActivity2;

    public Student(String groupID, Integer idStudent, String studentName, Double gradeActivity1, Double gradeActivity2) {
        this.groupID = groupID;
        this.idStudent = idStudent;
        this.studentName = studentName;
        this.gradeActivity1 = gradeActivity1;
        this.gradeActivity2 = gradeActivity2;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getGradeActivity1() {
        return gradeActivity1;
    }

    public void setGradeActivity1(Double gradeActivity1) {
        this.gradeActivity1 = gradeActivity1;
    }

    public Double getGradeActivity2() {
        return gradeActivity2;
    }

    public void setGradeActivity2(Double gradeActivity2) {
        this.gradeActivity2 = gradeActivity2;
    }
}
