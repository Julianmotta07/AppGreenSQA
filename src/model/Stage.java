package model;
import java.util.*;
import java.text.SimpleDateFormat;

public class Stage {

    private StageName name;
    private Calendar plannedStartDate;
    private Calendar plannedEndDate;
    private Calendar realStartDate;
    private Calendar realEndDate;
    private boolean status;

    public Stage(StageName name, Calendar plannedStartDate, Calendar plannedEndDate, boolean status) {
        this.name = name;
        this.plannedStartDate = plannedStartDate;
        this.plannedEndDate = plannedEndDate;
        this.status = status;
    }

    public StageName getName(){
        return name;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public void setPlannedStartDate(Calendar plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public void setPlannedEndDate(Calendar plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public void setRealStartDate(Calendar realStartDate) {
        this.realStartDate = realStartDate;
    }

    public void setRealEndDate(Calendar realEndDate) {
        this.realEndDate = realEndDate;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pStartDate = dateFormat.format(plannedStartDate.getTime());
        String pEndDate = dateFormat.format(plannedEndDate.getTime());
        String rStartDate = null, rEndDate = null;
        if (realStartDate != null){
            rStartDate = dateFormat.format(realStartDate.getTime());
        }
        if (realEndDate != null){
            rEndDate = dateFormat.format(realEndDate.getTime());
        }
        return "Name: " +  name.name() + "\n" +
                "Planned start date: " + pStartDate + "\n" +
                "Planned end date: " + pEndDate + "\n" +
                "Real start date: " + (rStartDate != null ? rStartDate : "N/A") + "\n" +
                "Real end date: " + (rEndDate != null ? rEndDate : "N/A") + "\n" +
                "Status: " + (status ? "Active" : "Inactive");
    }
}
