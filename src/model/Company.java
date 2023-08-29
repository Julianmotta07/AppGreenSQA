package model;
import java.util.*;

public class Company {
    private String name;
    private ArrayList<User> users;
    private ArrayList <Project> projects;
    private ArrayList <Publishable> publishableCapsules;
    private ArrayList <Searchable> searchableCapsules;
    private Employee currentUser;

    public Company(String name) {
        this.name = name;
        users = new ArrayList<>();
        projects = new ArrayList<>();
        publishableCapsules = new ArrayList<>();
        searchableCapsules = new ArrayList<>();
    }

    public int login(String id, String password){
        int userType = 0;
        User user = searchUser(id);
        if (user != null && !(user instanceof Client)){
            Employee employee = (Employee) user;
            if (employee.getPassword().equals(password)){
                currentUser = employee;
                if (employee instanceof ServiceManager){
                    userType = 2;
                } else if (employee instanceof Collaborator){
                    userType = 3;
                } else {
                    userType = 1;
                }
            }
        }
        return userType;
    }

    public void addUser(String id, String password){
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee(id, "Admin", password, date);
        users.add(employee);
    }

    public String addUser(String id, String fullName, int sector){
        String message = "New client successfully registered!.";
        if (searchUser(id) != null){
            message = "Error: A user with the entered ID already exist.";
        } else {
            Client client = new Client (id, fullName, sector);
            users.add(client);
        }
        return message;
    }

    public String addUser(String id, String fullName, Calendar hireDate){
        String message = "New service manager successfully registered!.";
        if (searchUser(id) != null ){
            message = "Error: A user with the entered ID already exists!.";
        } else {
            ServiceManager serviceManager = new ServiceManager(id, fullName, id, hireDate);
            users.add(serviceManager);
        }
        return message;
    }

    public String addUser(String id, String fullName, Calendar hireDate, int charge){
        String message = "New collaborator successfully registered!.";
        if (searchUser(id) != null ){
            message = "Error: A user with the entered ID already exists!.";
        } else {
            Collaborator collaborator = new Collaborator(id, fullName, id, hireDate, charge);
            users.add(collaborator);
        }
        return message;
    }

    public String consultUser(String id){
        StringBuilder message = new StringBuilder("Error: A user with the entered ID does not exist.");
        User user = searchUser(id);
        if (user != null){
            message = new StringBuilder("User information:\n").append(user);
        }
        return message.toString();
    }

    public String editUser(String id, String newID, String newFullName, int newSector, int[] items){
        String message = "Client information successfully updated!.";
        User user = searchUser(id);
        if (user == null || user instanceof Employee) {
            message = "The user to edit does not exist.";
        } else if (searchUser(newID) != null){
            message = "Error: A user with the entered ID already exist.";
        } else {
            Client client = (Client) user;
            for (int item : items) {
                switch (item) {
                    case 1 -> client.setId(newID);
                    case 2 -> client.setFullName(newFullName);
                    case 3 -> client.setSector(newSector);
                }
            }
        }
        return message;
    }

    public String editUser(String id, String newID, String newFullName, Calendar newHireDate, int[] items){
        String message = "Service manager information successfully updated!.";
        User user = searchUser(id);
        if (user == null) {
            message = "The user to edit does not exist.";
        } else if (searchUser(newID) != null){
            message = "Error: A user with the entered ID already exist.";
        } else {
            Employee employee = (Employee) user;
            for (int item : items) {
                switch (item) {
                    case 1 -> employee.setId(newID);
                    case 2 -> employee.setFullName(newFullName);
                    case 3 -> employee.setDateHire(newHireDate);
                }
            }
            if (user == currentUser){
                message = "Admin information successfully updated!.";
            }
        }
        return message;
    }

    public String editUser(String id, String newID, String newFullName, Calendar newHireDate, int newCharge, int[] items){
        String message = "Collaborator information successfully updated!.";
        User user = searchUser(id);
        if (user == null) {
            message = "The user to edit does not exist.";
        } else if (searchUser(newID) != null){
            message = "Error: A user with the entered ID already exist.";
        } else {
            Collaborator collaborator = (Collaborator) user;
            for (int item : items) {
                switch (item) {
                    case 1 -> collaborator.setId(newID);
                    case 2 -> collaborator.setFullName(newFullName);
                    case 3 -> collaborator.setDateHire(newHireDate);
                    case 4 -> collaborator.setCharge(newCharge);
                }
            }
        }
        return message;
    }

    public String deleteUser(String id){
        String message = "User deleted successfully!";
        User user = searchUser(id);
        if (user == null || user == currentUser){
            message = "Error: A user with the entered ID does not exist.";
        } else {
            users.remove(user);
        }
        return message;
    }

    public String addProject(String name, String managerID, String clientID, Calendar pStartDate, int[] monthPerStage){
        String message = "Project successfully registered!.";
        User user1 = searchUser(managerID);
        if (user1 instanceof ServiceManager manager){
           User user2 = searchUser(clientID);
           if (user2 instanceof Client client){
               Project project = new Project(name, manager, client, pStartDate, monthPerStage);
               projects.add(project);
           } else {
               message = "Error: A client with the entered ID does not exist.";
           }
        } else {
            message = "Error: A manager with the entered ID does not exist.";
        }
        return message;
    }

    public String consultProject(String projectID){
        StringBuilder message = new StringBuilder("Project information: \n");
        Project project = searchProject(projectID);
        if (project == null || !(project.getManager().equals(currentUser))){
            message = new StringBuilder("Error: Invalid ID.");
        } else {
            message.append(project);
        }
        return message.toString();
    }

    public String consultStages(String projectID){
        StringBuilder message = new StringBuilder("Stages information: \n");
        Project project = searchProject(projectID);
        if (project == null || !(project.getManager().equals(currentUser))){
            message = new StringBuilder("Error: Invalid ID.");
        } else {
            for (int i = 0; i < 6; i++){
                message.append("\n").append(project.getStages()[i]).append("\n");
            }
        }
        return message.toString();
    }

    public String assignCollaboratorToProject(String projectID, String collaboratorID){
        String message = "Collaborator successfully assigned!.";
        User user = searchUser(collaboratorID);
        if (user instanceof Collaborator collaborator){
            Project project = searchProject(projectID);
            if (project == null || !(project.getManager().equals(currentUser))){
                message = "Error: Invalid project ID.";
            } else if (!project.addCollaborator(collaborator)){
                message = "The collaborator has already been assigned to this project.";
            }
        } else {
            message = "Error: Invalid collaborator ID.";
        }
        return message;
    }

    public String changeCollaboratorFromProject(String currentProjectID, String newProjectID, String collaboratorID){
        StringBuilder message = new StringBuilder("Collaborators working in this project: \n");
        Project currentProject = searchProject(currentProjectID);
        if (collaboratorID == null) {
            if (currentProject != null && currentProject.getManager().equals(currentUser)) {
                boolean atLeastOneCol = false;
                for (int i = 0; i < currentProject.getCollaborators().size(); i++){
                    Collaborator collaborator = currentProject.getCollaborators().get(i);
                    message.append("\nName: ").append(collaborator.getFullName()).append("\nID: ");
                    message.append(collaborator.getId()).append("\n");
                    atLeastOneCol = true;
                }
                if (!atLeastOneCol){
                    message = new StringBuilder("Error: There are no collaborators in this project yet.");
                }
            } else {
                message = new StringBuilder("Error: Invalid project ID.");
            }
        } else {
            User user = searchUser(collaboratorID);
            if (user instanceof Collaborator collaborator && currentProject.getManager().equals(currentUser) && currentProject.removeCollaborator(collaborator)){
                message = new StringBuilder("Collaborator removed from ").append(currentProject.getName());
                if (newProjectID != null){
                   Project newProject = searchProject(newProjectID);
                   if (newProject != null && newProject.getManager().equals(currentUser) && newProject.addCollaborator(collaborator)){
                       if (!currentProject.equals(newProject)){
                           message.append(" and added to ").append(newProject.getName());
                       } else {
                           message = new StringBuilder("The collaborator is already in this project.");
                       }
                   } else {
                       message = new StringBuilder("Error: Invalid project ID.");
                   }
                }
            } else {
                message = new StringBuilder("Error: Invalid collaborator ID.");
            }
        }
        return message.toString();
    }

    public String finishStage(String projectID, boolean finish){
        StringBuilder message = new StringBuilder("Error: Invalid project ID.");
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)){
            boolean exit = false;
            for (int i = 0; i < project.getStages().length && !exit; i++){
                if (project.getStages()[i].getStatus()){
                    Stage currentStage = project.getStages()[i];
                    if (!finish){
                        message = new StringBuilder("Current active stage:\n\n").append(currentStage);
                    } else {
                        currentStage.setStatus(false);
                        Calendar currentDate = Calendar.getInstance();
                        currentStage.setRealEndDate(currentDate);
                        message = new StringBuilder("Stage ").append(currentStage.getName().name()).append(" finished and stage ");
                        if (i < project.getStages().length - 1){
                            Stage nextStage = project.getStages()[i + 1];
                            nextStage.setStatus(true);
                            nextStage.setRealStartDate(currentDate);
                            message.append(nextStage.getName().name()).append(" activated successfully!.");
                        } else {
                            message = new StringBuilder("Last stage ").append(currentStage.getName().name()).append(" finished successfully!.");
                        }
                    }
                    exit = true;
                }
            }

        }
        return message.toString();
    }

    public String registerCapsule(String projectID, int stageOpt, int type, String lesson, String text, String standardName){
        String message = "Error: Invalid project ID.";
        Project project = searchProject(projectID);
        Collaborator collaborator = (Collaborator) currentUser;
        if (project != null && project.getCollaborators().contains(collaborator)){
            Stage stage = project.getStages()[stageOpt];
            CapsuleType[] types = {CapsuleType.TECHNICAL, CapsuleType.MANAGEMENT, CapsuleType.DOMAIN, CapsuleType.EXPERIENCE};
            OrganizationCapsule capsule = new OrganizationCapsule(lesson, text, types[type], collaborator, stage, standardName);
            searchableCapsules.add(capsule);
            project.addCapsule(capsule);
            message = "Organization capsule successfully registered!";
        }
        return message;
    }

    public String registerCapsule(String projectID, int stageOpt, int type, String lesson, String text, boolean isPrivate){
        String message = "Error: Invalid project ID.";
        Project project = searchProject(projectID);
        Collaborator collaborator = (Collaborator) currentUser;
        if (project != null && project.getCollaborators().contains(collaborator)){
            Stage stage = project.getStages()[stageOpt];
            CapsuleType[] types = {CapsuleType.TECHNICAL, CapsuleType.MANAGEMENT, CapsuleType.DOMAIN, CapsuleType.EXPERIENCE};
            ProjectCapsule capsule = new ProjectCapsule(lesson, text, types[type], collaborator, stage, isPrivate);
            searchableCapsules.add(capsule);
            project.addCapsule(capsule);
            message = "Project capsule successfully registered!";
        }
        return message;
    }

    public String approveCapsule(String projectID, String capsuleID, int restrictionReason){
        String message = "Error: Invalid capsule ID.";
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)){
            Capsule capsule = project.searchCapsule(capsuleID);
            if (capsule != null && capsule.getClassificationDate() == null){
                ServiceManager manager = (ServiceManager) currentUser;
                Calendar currentDate = Calendar.getInstance();
                capsule.setClassificationDate(currentDate);
                capsule.addServiceManager(manager);
                if (capsule instanceof ProjectCapsule projectCapsule){
                    message = "Project capsule successfully approved!.";
                    if (projectCapsule.isPrivate()){
                        if (restrictionReason == -1){
                            projectCapsule.setClassificationDate(null);
                            searchableCapsules.remove(projectCapsule);
                            message = "Select restriction reason:";
                        } else {
                            RestrictionReason[] reasons = {RestrictionReason.CONFIDENTIAL,
                                    RestrictionReason.CONTRACTUAL,
                                    RestrictionReason.BUSINESS_SECRECY,
                                    RestrictionReason.TRADE_SECRET};
                            projectCapsule.setRestrictionReason(reasons[restrictionReason - 1]);
                        }
                    }
                } else {
                    OrganizationCapsule orgCapsule = (OrganizationCapsule) capsule;
                    if (orgCapsule.getRejectionReason() == null){
                        publishableCapsules.add(orgCapsule);
                    }
                    message = "Organization capsule successfully approved!.";
                }
            }
        }
        return message;
    }

    public String rejectCapsule(String projectID, String capsuleID, int rejectionReason){
        String message = "Error: Invalid capsule ID.";
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)){
            Capsule capsule = project.searchCapsule(capsuleID);
            if (capsule != null && capsule.getClassificationDate() == null){
                ServiceManager manager = (ServiceManager) currentUser;
                Calendar currentDate = Calendar.getInstance();
                capsule.setClassificationDate(currentDate);
                capsule.addServiceManager(manager);
                RejectionReason[] reasons = {RejectionReason.INCOMPLETE,
                RejectionReason.AMBIGUOUS,
                RejectionReason.INCONSISTENT,
                RejectionReason.NOT_RELEVANT};
                capsule.setRejectionReason(reasons[rejectionReason - 1]);
                message = "Capsule successfully rejected!.";
            }
        }
        return message;
    }

    public String publishCapsule(String capsuleID){
        StringBuilder message = new StringBuilder("Error: There are no publishable capsules yet.");
        if (!publishableCapsules.isEmpty()){
            message = new StringBuilder("\nPublishable capsules:\n\n");
            for (Publishable publishable : publishableCapsules){
                if (publishable instanceof Capsule capsule){
                    if (capsuleID == null) {
                        message.append(capsule.getId()).append("\n");
                    } else if (capsule.getId().equals(capsuleID)){
                        OrganizationCapsule orgCapsule = (OrganizationCapsule) capsule;
                        message = new StringBuilder("HTML ready to publish:\n\n");
                        message.append(orgCapsule.generateHTML()).append("\n");
                    } else {
                        message = new StringBuilder("Error: Invalid capsule ID.");
                    }
                }
            }
        }
        return message.toString();
    }

    public String consultCapsule(int consultType, String filter){
        StringBuilder message = new StringBuilder("Error: There are no searchable capsules registered.");
        if (consultType == -1){
            if (!searchableCapsules.isEmpty()){
                message = new StringBuilder("Select consult type:");
            }
        } else {
            message = new StringBuilder("\nSearch result:\n\n");
            switch (consultType) {
                case 1 -> message.append(capsulesPerSearchString(filter));
                case 2 -> {
                    Project project = searchProject(filter);
                    if (project != null) {
                        message.append(capsulesPerProject(project));
                    } else {
                        message = new StringBuilder("Error: Invalid project ID.");
                    }
                }
                case 3 -> {
                    StageName[] stages = {StageName.START, StageName.ANALYSIS, StageName.DESIGN,
                            StageName.EXECUTION, StageName.CLOSE, StageName.MONITORING
                    };
                    message.append(capsulesPerStage(stages[Integer.parseInt(filter)]));
                }
                case 4 -> {
                    User user = searchUser(filter);
                    if (user != null) {
                        Collaborator collaborator = (Collaborator) user;
                        message.append(capsulesPerCollaborator(collaborator));
                    } else {
                        message = new StringBuilder("Error: Invalid collaborator ID.");
                    }
                }
            }
        }
        return message.toString();
    }

    private String capsulesPerSearchString(String searchString){
        String[] words = searchString.split(" ");
        int amount = 0;
        StringBuilder list = new StringBuilder();
        for (Searchable searchable : searchableCapsules) {
            if (searchable instanceof Capsule capsule) {
                String[] hashtags = capsule.getKeywords();
                for (String word : words) {
                    for (String hashtag : hashtags) {
                        if (word.equalsIgnoreCase(hashtag)) {
                            list.append(capsule).append("\n\n");
                            amount++;
                        }
                    }
                }
            }
        }
        list.append("Capsules found: ").append(amount);
        return list.toString();
    }

    private String capsulesPerProject(Project project){
        int amount = 0;
        StringBuilder list = new StringBuilder();
        for (Searchable searchable : searchableCapsules){
            if (searchable instanceof Capsule capsule){
                for (Capsule pCapsule : project.getCapsules()){
                    if (pCapsule.equals(capsule)){
                        list.append(pCapsule).append("\n\n");
                        amount++;
                    }
                }
            }
        }
        list.append("Capsules found: ").append(amount);
        return list.toString();
    }

    private String capsulesPerStage(StageName stageName){
        int amount = 0;
        StringBuilder list = new StringBuilder();
        for (Searchable searchable : searchableCapsules){
            if (searchable instanceof Capsule capsule){
                if (capsule.getStage().getName().equals(stageName)){
                    list.append(capsule).append("\n\n");
                    amount++;
                }
            }
        }
        list.append("Capsules found: ").append(amount);
        return list.toString();
    }

    private String capsulesPerCollaborator(Collaborator collaborator){
        int amount = 0;
        StringBuilder list = new StringBuilder();
        for (Searchable searchable : searchableCapsules){
            if (searchable instanceof Capsule capsule){
                if (capsule.getCollaborator().equals(collaborator)){
                    list.append(capsule).append("\n\n");
                    amount++;
                }
            }
        }
        list.append("Capsules found: ").append(amount);
        return list.toString();
    }

    public String reportKnowledgeProgress(String projectID){
        StringBuilder list = new StringBuilder("Error: Invalid project ID");
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)) {
            if (!project.getCapsules().isEmpty()){
                list = new StringBuilder("Capsules per stage:\n\n");
                StageName[] stages = {StageName.START, StageName.ANALYSIS, StageName.DESIGN,
                        StageName.EXECUTION, StageName.CLOSE, StageName.MONITORING
                };
                int temp = 0;
                for (StageName stage : stages) {
                    list.append(stage.name()).append(": ");
                    for (Capsule capsule : project.getCapsules()) {
                        if (capsule.getStage().getName().equals(stage)) {
                            temp++;
                        }
                    }
                    list.append(temp).append("\n");
                    temp = 0;
                }
                list.append("\nCapsules per collaborator:\n\n");
                for (Collaborator collaborator : project.getCollaborators()){
                    list.append(collaborator.getFullName()).append(": ");
                    for (Capsule capsule : project.getCapsules()) {;
                        if (capsule.getCollaborator().equals(collaborator)) {
                            temp++;
                        }
                    }
                    list.append(temp).append("\n");
                    temp = 0;
                }
            } else {
                list = new StringBuilder("Error: There are no capsules registered in this project.");
            }
        }
        return list.toString();
    }

    public String reportKnowledgeManagement(String projectID){
        StringBuilder list = new StringBuilder("Error: Invalid project ID");
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)) {
            if (!project.getCapsules().isEmpty()){
                list = new StringBuilder("Capsules per type:\n\n");
                CapsuleType[] types = {CapsuleType.TECHNICAL, CapsuleType.MANAGEMENT,
                CapsuleType.DOMAIN, CapsuleType.EXPERIENCE};
                int temp = 0;
                for (CapsuleType type : types){
                    list.append(type.name()).append(": ");
                    for (Capsule capsule : project.getCapsules()){
                        if (capsule.getType().equals(type)){
                            temp++;
                        }
                    }
                    list.append(temp).append("\n");
                    temp = 0;
                }
                int publicC = 0, privateC= 0;
                for (Capsule capsule : project.getCapsules()){
                    if (capsule instanceof ProjectCapsule projectCapsule){
                        if (projectCapsule.isPrivate()){
                            privateC++;
                        } else {
                            publicC++;
                        }
                    }
                }
                list.append("\nPublic capsules: ").append(publicC).append("\nPrivate capsules: ").append(privateC).append("\n");
            } else {
                list = new StringBuilder("Error: There are no capsules registered in this project.");
            }
        }
        return list.toString();
    }

    public String showCollaborators(){
        StringBuilder list = new StringBuilder("\nCurrent collaborators: \n");
        boolean atLeastOneCol = false;
        for (User user : users) {
            if (user instanceof Collaborator) {
                list.append("\nName: ").append(user.getFullName()).append("\nID: ");
                list.append(user.getId()).append("\n");
                atLeastOneCol = true;
            }
        }
        if (!atLeastOneCol){
            list = new StringBuilder("Error: There are no collaborators registered yet.");

        }
        return list.toString();
    }

    public String showAllProjects(){
        StringBuilder list = new StringBuilder("Error: There are no projects registered yet");
        if (!projects.isEmpty()){
            list = new StringBuilder("\nCurrent projects:\n");
            for (Project project : projects){
                list.append("\nName: ").append(project.getName()).append("\nID: ");
                list.append(project.getId()).append("\n");
            }
        }
        return list.toString();
    }

    public String showProjects(){
        StringBuilder list = new StringBuilder("\nYour projects:\n");
        if (!projects.isEmpty()){
            Collaborator collaborator = null;
            if (currentUser instanceof Collaborator){
                collaborator = (Collaborator) currentUser;
            }
            boolean atLeastOneProject = false;
            for (Project project : projects){
                if (project.getManager().equals(currentUser) || project.getCollaborators().contains(collaborator)){
                    list.append("\nName: ").append(project.getName()).append("\nID: ");
                    list.append(project.getId()).append("\n");
                    atLeastOneProject = true;
                }
            }
            if (!atLeastOneProject){
                list = new StringBuilder("Error: You don't have projects assigned yet.");
            }
        } else {
            list = new StringBuilder("Error: There are no projects registered yet.");
        }
        return list.toString();
    }

    public String showCapsules(String projectID){
        StringBuilder list = new StringBuilder("Error: Invalid project ID.");
        Project project = searchProject(projectID);
        if (project != null && project.getManager().equals(currentUser)){
            if (!project.getCapsules().isEmpty()){
                list = new StringBuilder("\nCapsules of this project: \n");
                boolean oneOrganization = false, oneProject = false;
                for (int i = 0; i < project.getStages().length; i++){
                    Stage stage = project.getStages()[i];
                    for (Capsule capsule : project.getCapsules()){
                        if (capsule.getStage().equals(stage) && capsule.getClassificationDate() == null){
                            if (capsule instanceof OrganizationCapsule && !oneOrganization){
                                list.append("\nFOR THE ORGANIZATION:\n");
                                oneOrganization = true;
                            } else if (capsule instanceof ProjectCapsule && !oneProject){
                                list.append("\nFOR THE PROJECT:\n");
                                oneProject = true;
                            }
                            list.append(" - ").append(capsule.getId()).append("\n");
                        }
                    }
                }
                if (!oneOrganization && !oneProject){
                    list = new StringBuilder("Error: The project has no capsules to classify.");
                }
            } else {
               list = new StringBuilder("Error: The project has no capsules registered yet.");
            }
        }
        return list.toString();
    }

    public String changePassword(String currentPassword, String newPassword){
        String message = "Password changed successfully!";
        if (currentUser.getPassword().equals(currentPassword)){
            currentUser.setPassword(newPassword);
        } else {
            message = "Error: Wrong password";
        }
        return message;
    }

    private User searchUser(String id){
        User userFound = null;
        boolean found = false;
        for (int i = 0; i < users.size() && !found; i++) {
            User user = users.get(i);
            if (user.getId().equals(id)){
                userFound = user;
                found = true;
            }
        }
        return userFound;
    }

    private Project searchProject (String id){
        Project projectFound = null;
        boolean found = false;
        for (int i = 0; i < projects.size() && !found; i++) {
            Project project = projects.get(i);
            if (project.getId().equals(id)){
                projectFound = project;
                found = true;
            }
        }
        return projectFound;
    }

}