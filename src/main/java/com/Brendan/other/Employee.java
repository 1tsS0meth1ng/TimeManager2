package main.java.com.Brendan.other;



public class Employee
{

    private String iD;
    private String firstName, surName, middleNames, Address, StartDate, EndDate,Role, Position;
    private boolean admin;
    public Employee(){}

    public Employee(String id, String fName, String mNames, String sName,String Address, String StartDate, String EndDate,
                    String Role, String Position, boolean admin)
    {
        this.iD = id;
        this.firstName = fName;
        this.middleNames = mNames;
        this.surName = sName;
        this.Address = Address;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Role =Role;
        this.Position = Position;
        this.admin = admin;
    }


    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public boolean isAdmin() {
        return admin;
    }

}
