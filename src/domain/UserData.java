package domain;



public class UserData {

    private String userName, userPassword, userDepartement;

    public UserData(String userName, String userPassword, String userDepartement) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDepartement = userDepartement;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserDepartement() {
        return userDepartement;
    }

    public void setUserDepartement(String userDepartement) {
        this.userDepartement = userDepartement;
    }

}
