package model;

public class ProjectCapsule extends Capsule{

    private boolean isPrivate;
    private RestrictionReason restrictionReason;

    public ProjectCapsule(String lesson, String text, CapsuleType type, Collaborator collaborator, Stage stage, boolean isPrivate) {
        super(lesson, text, type, collaborator, stage);
        this.isPrivate = isPrivate;
    }

    public boolean isPrivate(){
        return isPrivate;
    }

    public void setRestrictionReason (RestrictionReason restrictionReason){
        this.restrictionReason = restrictionReason;
    }

    public String toString(){
        return super.toString() +
                "Visibility: " + (isPrivate ? "Private" : "Public") +
                (restrictionReason != null ? "\nRestriction reason: " + restrictionReason.name() : "");
    }


}
