package domain;

public class StudentData {

    private int id;
    private String firsName, lastName, address, department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public StudentData(int id, String firsName, String lastName, String address, String department) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.address = address;
        this.department = department;
    }

}
