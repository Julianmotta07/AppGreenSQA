package model;

public class OrganizationCapsule extends Capsule implements Publishable{

    private String standardName;

    public OrganizationCapsule(String lesson, String text, CapsuleType type, Collaborator collaborator, Stage stage, String standardName) {
        super(lesson, text, type, collaborator, stage);
        this.standardName = standardName;
    }

    @Override
    public String generateHTML() {
        return "<html>" + "\n" +
                "<body>" + "\n" +
                super.getText() + "\n" +
                "</body>" + "\n" +
                "</html>";
    }

    public String toString(){
        return super.toString() +
                "Standard name: " + standardName;
    }
}
