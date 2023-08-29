package model;
import java.util.*;
import java.text.SimpleDateFormat;

public abstract class Capsule implements Searchable{
    private String id;
    private String lesson;
    private String text;
    private Calendar classificationDate;
    private String[] keywords;
    private CapsuleType type;
    private RejectionReason rejectionReason;
    private Collaborator collaborator;
    private ServiceManager manager;
    private Stage stage;

    public Capsule(String lesson, String text, CapsuleType type, Collaborator collaborator, Stage stage) {
        this.lesson = lesson;
        this.text = text;
        this.type = type;
        this.collaborator = collaborator;
        this.stage = stage;
        keywords = text.split("#");
        id = generateID(stage);

    }

    private String generateID(Stage stage){
        StringBuilder id = new StringBuilder();
        id.append(stage.getName().name().charAt(0));
        id.append(stage.getName().name().charAt(1));
        Calendar operationDate = Calendar.getInstance();
        int second = operationDate.get(Calendar.SECOND);
        int minute = operationDate.get(Calendar.MINUTE);
        int hour = operationDate.get(Calendar.HOUR_OF_DAY);
        int day = operationDate.get(Calendar.DAY_OF_MONTH);
        int month = operationDate.get(Calendar.MONTH) + 1;
        int year = operationDate.get(Calendar.YEAR);
        id.append(second).append(minute).append(hour).append(day).append(month).append(year);
        return id.toString();
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public Stage getStage() { return stage; }
    public String[] getKeywords() { return keywords; }
    public Collaborator getCollaborator() { return collaborator; }
    public Calendar getClassificationDate() { return classificationDate; }
    public CapsuleType getType() { return type; }
    public void setClassificationDate(Calendar classificationDate) { this.classificationDate = classificationDate; }
    public RejectionReason getRejectionReason() { return rejectionReason; }
    public void setRejectionReason (RejectionReason rejectionReason) { this.rejectionReason = rejectionReason; }
    public void addServiceManager(ServiceManager manager) { this.manager = manager; }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String clDate = null;
        if (classificationDate != null ){
            clDate = dateFormat.format(classificationDate.getTime());
        }
        return "ID: " +  id + "\n" +
                "Type: " + type.name() + "\n" +
                "Stage: " + stage.getName().name() + "\n" +
                "Lesson: " + lesson + "\n" +
                "Text: " + text + "\n" +
                "Collaborator: " + collaborator.getFullName() +
                (clDate != null ? "\nClassification Date: " + clDate + "\n" +
                 "Manager: " + manager.getFullName() + (rejectionReason != null ?
                 "\nRejection reason: " + rejectionReason.name() : "" ) : "" ) + "\n";
    }
}
