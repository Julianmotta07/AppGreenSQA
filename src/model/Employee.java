package model;
import java.util.Calendar;

public class Employee extends User{

    private String password;
    private Calendar dateHire;

    public Employee(String id, String fullName, String password, Calendar dateHire) {
        super(id, fullName);
        this.password = password;
        this.dateHire = dateHire;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateHire(Calendar dateHire) {
        this.dateHire = dateHire;
    }

    public String toString() {
        return super.toString() +
                "Date hire: " + dateHire.get(Calendar.DAY_OF_MONTH) + "/" +
                (dateHire.get(Calendar.MONTH) + 1) + "/" +
                dateHire.get(Calendar.YEAR) + "\n";
    }
}
