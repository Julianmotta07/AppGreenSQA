package model;

import java.util.Calendar;

public class Collaborator extends Employee {

    private CollaboratorCharge charge;

    public Collaborator(String id, String fullName, String password, Calendar dateHire, int charge) {
        super(id, fullName, password, dateHire);
        switch (charge) {
            case 1 -> this.charge = CollaboratorCharge.TEST_ENGINEER;
            case 2 -> this.charge = CollaboratorCharge.AUTOMATION_ENGINEER;
        }
    }

    public void setCharge(int charge) {
        switch (charge) {
            case 1 -> this.charge = CollaboratorCharge.TEST_ENGINEER;
            case 2 -> this.charge = CollaboratorCharge.AUTOMATION_ENGINEER;
        }
    }

    public String toString(){
        return super.toString() +
                "Charge: " + charge.name();
    }
}
