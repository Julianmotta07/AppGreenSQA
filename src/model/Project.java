package model;
import java.util.*;
import java.text.SimpleDateFormat;

public class Project {

    private String id;
    private String name;
    private Calendar plannedStartDate;
    private Calendar plannedEndDate;
    private Stage[] stages;
    private ServiceManager manager;
    private Client client;
    private ArrayList<Collaborator> collaborators;
    private ArrayList<Capsule> capsules;

    public Project(String name, ServiceManager manager, Client client, Calendar plannedStartDate, int[] monthsPerStage) {
        this.name = name;
        this.client = client;
        this.manager = manager;
        this.plannedStartDate = plannedStartDate;
        id = generateID();
        collaborators = new ArrayList<>();
        capsules = new ArrayList<>();
        stages = new Stage[6];
        createStages(monthsPerStage);
    }

    public String getId() {
        return id;
    }

    private String generateID(){
        Calendar operationDate = Calendar.getInstance();
        StringBuilder id = new StringBuilder("P-");
        int second = operationDate.get(Calendar.SECOND);
        int minute = operationDate.get(Calendar.MINUTE);
        int hour = operationDate.get(Calendar.HOUR_OF_DAY);
        int day = operationDate.get(Calendar.DAY_OF_MONTH);
        int month = operationDate.get(Calendar.MONTH) + 1;
        int year = operationDate.get(Calendar.YEAR);
        id.append(second).append(minute).append(hour).append("-").append(day).append(month).append(year);
        return id.toString();
    }

    public String getName(){
        return name;
    }

    public ServiceManager getManager(){
        return manager;
    }

    public Stage[] getStages(){
        return stages;
    }

    public ArrayList<Collaborator> getCollaborators() {
        return collaborators;
    }

    public ArrayList<Capsule> getCapsules(){ return capsules; }

    private void createStages(int[] monthsPerStage) {
        StageName[] stageNames = {StageName.START, StageName.ANALYSIS, StageName.DESIGN, StageName.EXECUTION, StageName.CLOSE, StageName.MONITORING};
        Calendar tempDate = (Calendar) plannedStartDate.clone();
        for (int i = 0; i < stages.length; i++) {
            boolean isFirstStage = (i == 0);
            stages[i] = new Stage(stageNames[i], isFirstStage ? plannedStartDate : null, null, isFirstStage);
            if (i > 0) {
                stages[i].setPlannedStartDate((Calendar) tempDate.clone());
            }
            tempDate.add(Calendar.MONTH, monthsPerStage[i]);
            stages[i].setPlannedEndDate((Calendar) tempDate.clone());
        }
        plannedEndDate = (Calendar) tempDate.clone();
        stages[0].setRealStartDate((Calendar) plannedStartDate.clone());
    }

    public boolean addCollaborator(Collaborator collaborator){
        boolean added = false;
        if (!collaborators.contains(collaborator)){
            collaborators.add(collaborator);
            added = true;
        }
        return added;
    }

    public boolean removeCollaborator(Collaborator collaborator){
        boolean removed = false;
        if (collaborators.contains(collaborator)){
            collaborators.remove(collaborator);
            removed = true;
        }
        return removed;
    }

    public void addCapsule(Capsule capsule){
        capsules.add(capsule);
    }

    public Capsule searchCapsule(String capsuleID){
        Capsule capsuleFound = null;
        for (Capsule capsule : capsules){
            if (capsule.getId().equals(capsuleID)){
                capsuleFound = capsule;
                break;
            }
        }
        return capsuleFound;
    }

    public String toString(){
        StringBuilder col = new StringBuilder();
        if (!collaborators.isEmpty()){
            for (Collaborator collaborator : collaborators){
                col.append(collaborator.getFullName()).append(", ");
            }
            if (col.length() > 2) {
                col.setLength(col.length() - 2);
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String pStartDate = dateFormat.format(plannedStartDate.getTime());
        String pEndDate = dateFormat.format(plannedEndDate.getTime());
        return "Name: " + name + "\n" +
                "Planned start date: " + pStartDate + "\n" +
                "Planned end date: " + pEndDate + "\n" +
                "Client: " + client.getFullName() + "\n" +
                "Collaborators: " + (collaborators.isEmpty() ? "N/A" : col);
    }
}
