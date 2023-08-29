package ui;
import model.Company;
import java.util.*;

public class Main {

    public static Company company;
    public static Scanner scanner;

    public Main(String name){
        company = new Company(name);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main objMain = new Main("GreenSQA");
        company.addUser("123", "123");
        System.out.println("Admin user: 123 - password: 123");
        objMain.login();
    }

    public void login(){
        int choice;
        do {
            System.out.println("-------WELCOME TO THE GREENSQA APP------");
            System.out.println("------------Select an option------------");
            System.out.println("1: Sign in..............................");
            System.out.println("0: Exit.................................");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter your ID:");
                    String id = scanner.nextLine();
                    System.out.println("Enter your password:");
                    String password = scanner.nextLine();
                    int userType = company.login(id, password);
                    switch (userType) {
                        case 1 -> menu(1);
                        case 2 -> menu(2);
                        case 3 -> menu(3);
                        default -> {
                            System.out.println("Error: Incorrect data");
                            System.out.println("Press Enter to return...");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option. Press Enter and try again...");
                    scanner.nextLine();
            }
        } while (choice != 0);
    }

    public void menu(int role){
        int choice;
        boolean back = false;
        switch (role) {
            case 1 -> {
                do {
                    System.out.println("---------------ADMIN MENU---------------");
                    System.out.println("------------Select an option------------");
                    System.out.println("1: Register user........................");
                    System.out.println("2: Consult user.........................");
                    System.out.println("3: Edit user............................");
                    System.out.println("4: Delete user..........................");
                    System.out.println("5: Register project.....................");
                    System.out.println("6: Change password......................");
                    System.out.println("0: Log out..............................");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1 -> {
                            registerUser();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 2 -> {
                            consultUser();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 3 -> {
                            editUser();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 4 -> {
                            deleteUser();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 5 -> {
                            registerProject();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 6 -> {
                            changePassword();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 0 -> back = true;
                        default -> {
                            System.out.println("Invalid option. Press Enter and try again...");
                            scanner.nextLine();
                        }
                    }
                } while (!back);
            }
            case 2 -> {
                do {
                    System.out.println("----------SERVICE MANAGER MENU----------");
                    System.out.println("------------Select an option------------");
                    System.out.println("1: Edit project.........................");
                    System.out.println("2: Consult project......................");
                    System.out.println("3: Consult stages.......................");
                    System.out.println("4: Finish stage.........................");
                    System.out.println("5: Consult capsule......................");
                    System.out.println("6: Classify capsule.....................");
                    System.out.println("7: Publish capsule......................");
                    System.out.println("8: Assign collaborator to a project.....");
                    System.out.println("9: Change collaborator from project.....");
                    System.out.println("10: Generate knowledge progress report..");
                    System.out.println("11: Generate knowledge management report");
                    System.out.println("12: Change password.....................");
                    System.out.println("0: Log out..............................");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1 -> {
                            editProject();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 2 -> {
                            consultProject();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 3 -> {
                            consultStages();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 4 -> {
                            finishStage();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 5 -> {
                            consultCapsule();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 6 -> {
                            classifyCapsule();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 7 -> {
                            publishCapsule();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 8 -> {
                            assignCollaboratorToProject();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 9 -> {
                            changeCollaboratorFromProject();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 10 -> {
                            reportKnowledgeProgress();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 11 -> {
                            reportKnowledgeManagement();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 12 ->{
                            changePassword();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 0 -> back = true;
                        default -> {
                            System.out.println("Invalid option. Press Enter and try again...");
                            scanner.nextLine();
                        }
                    }
                } while (!back);
            }
            case 3 -> {
                do {
                    System.out.println("-----------COLLABORATOR MENU------------");
                    System.out.println("------------Select an option------------");
                    System.out.println("1. Register capsule.....................");
                    System.out.println("2. Consult capsule......................");
                    System.out.println("3. Change password......................");
                    System.out.println("0: Log out..............................");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1 -> {
                            registerCapsule();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 2 -> {
                            consultCapsule();
                            System.out.println("Press Enter to return to the menu...");
                            scanner.nextLine();
                        }
                        case 3 -> {
                            changePassword();
                            System.out.println("Press Enter to return to the menu");
                            scanner.nextLine();
                        }
                        case 0 -> back = true;
                        default -> {
                            System.out.println("Invalid option. Press enter and try again...");
                            scanner.nextLine();
                        }
                    }
                } while (!back);
            }
        }
    }

    public void registerUser(){
        System.out.println("Enter ID:");
        String id = scanner.nextLine();
        System.out.println("Enter full name:");
        String fullName = scanner.nextLine();
        System.out.println("Select type of user to add \n 1: Service manager \n 2: Collaborator \n 3: Client");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String message;
        if (choice == 3){
            System.out.println("Select the client sector \n 1: Health \n 2: Financial \n 3: Service \n 4: Energetic");
            int sector = scanner.nextInt();
            scanner.nextLine();
            message = company.addUser(id, fullName, sector);
            System.out.println(message);
        } else {
            System.out.println("Enter hire date (dd/mm/yyyy):");
            String input = scanner.nextLine();
            String[] parts = input.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1;
            int year = Integer.parseInt(parts[2]);
            Calendar hireDate = Calendar.getInstance();
            hireDate.set(year, month, day);
            if (choice == 2) {
                System.out.println("Select the charge: \n 1: Test engineer \n 2: Automation engineer");
                int charge = scanner.nextInt();
                scanner.nextLine();
                message = company.addUser(id, fullName, hireDate, charge);
                System.out.println(message);
            } else {
                message = company.addUser(id,fullName, hireDate);
                System.out.println(message);
            }
        }
    }

    public void consultUser(){
        System.out.println("Enter User ID:");
        String id = scanner.nextLine();
        String message = company.consultUser(id);
        System.out.println(message);
    }

    public void editUser(){
        StringBuilder infoToChange = new StringBuilder(" 1: ID \n 2: fullName \n");
        System.out.println("Enter user ID:");
        String id = scanner.nextLine();
        System.out.println("Select user type: \n 1: Service manager/admin \n 2: Collaborator \n 3: Client");
        int userType = scanner.nextInt();
        scanner.nextLine();
        if (userType == 3){
            infoToChange.append(" 0: Sector \n");
        } else {
            infoToChange.append(" 3: Date hire \n");
            if (userType == 2){
                infoToChange.append("\n 4: Charge \n");
            }
        }
        System.out.println("Information you can change:");
        System.out.println(infoToChange);
        int amount;
        boolean amountOK = false;
        do {
            System.out.println("How many items do you want to change?");
            amount = scanner.nextInt();
            scanner.nextLine();
            switch (userType) {
                case 1,3 -> {
                    if (amount > 0 && amount < 4) {
                        amountOK = true;
                    }
                }
                default -> {
                    if (amount > 0 && amount < 5) {
                        amountOK = true;
                    }
                }
            }
            if (!amountOK){
                System.out.println("Error: Enter a valid amount");
                scanner.nextLine();
            }
        } while (!amountOK);
        String newID = null, newFullName = null;
        int item, newSector = 0, newCharge = 0;
        Calendar newHireDate = Calendar.getInstance();
        boolean selectionOK;
        int[] items = new int[amount];
        for (int i = 0; i < amount; i++){
            do {
                selectionOK = true;
                System.out.println("Enter the assigned number of the item " + (i+1) + " to change:");
                System.out.println(infoToChange);
                item = scanner.nextInt();
                scanner.nextLine();
                switch (item) {
                    case 0 ->{
                        if (userType == 3){
                            do {
                                selectionOK = true;
                                items[i] = item;
                                System.out.println("Select the new sector: \n 1: Health \n 2: Financial \n 3: Service \n 4: Energetic");
                                newSector = scanner.nextInt();
                                scanner.nextLine();
                                if (newSector < 1 || newSector > 4){
                                    selectionOK = false;
                                    System.out.println("Error: Enter a valid option");
                                    scanner.nextLine();
                                }
                            } while (!selectionOK);
                        } else {
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                        }
                    }
                    case 1 -> {
                        items[i] = item;
                        System.out.println("Enter the new ID:");
                        newID = scanner.nextLine();
                    }
                    case 2 -> {
                        items[i] = item;
                        System.out.println("Enter the new full name:");
                        newFullName = scanner.nextLine();
                    }
                    case 3 -> {
                        if (userType != 3){
                            items[i] = item;
                            System.out.println("Enter new hire date (dd/mm/yyyy):");
                            String input = scanner.nextLine();
                            String[] parts = input.split("/");
                            int day = Integer.parseInt(parts[0]);
                            int month = Integer.parseInt(parts[1]) - 1;
                            int year = Integer.parseInt(parts[2]);
                            newHireDate.set(year, month, day);
                        } else {
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                        }
                    }
                    case 4 -> {
                        if (userType == 2){
                            do {
                                selectionOK = true;
                                items[i] = item;
                                System.out.println("Select the new charge: \n 1: Test engineer \n 2: Automation engineer");
                                newCharge = scanner.nextInt();
                                scanner.nextLine();
                                if (newCharge < 1 || newCharge > 2){
                                    selectionOK = false;
                                    System.out.println("Error: Enter a valid option");
                                    scanner.nextLine();
                                }
                            } while (!selectionOK);
                        } else {
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                        }
                    }
                    default -> {
                        selectionOK = false;
                        System.out.println("Error: Enter a valid option");
                        scanner.nextLine();
                    }
                }
            } while (!selectionOK);
        }
        String message = null;
        switch (userType) {
            case 1 -> message = company.editUser(id, newID, newFullName, newHireDate, items);
            case 2 -> message = company.editUser(id, newID, newFullName, newHireDate, newCharge, items);
            case 3 -> message = company.editUser(id, newID, newFullName, newSector, items);
        }
        System.out.println(message);
    }

    public void deleteUser(){
        System.out.println("Enter user ID:");
        String id = scanner.nextLine();
        String message = company.deleteUser(id);
        System.out.println(message);
    }

    public void registerProject(){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter manager ID:");
        String managerID = scanner.nextLine();
        System.out.println("Enter client ID:");
        String clientID = scanner.nextLine();
        System.out.println("Enter planned start date (dd/mm/yyyy):");
        String input = scanner.nextLine();
        String[] parts = input.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1;
        int year = Integer.parseInt(parts[2]);
        Calendar pStartDate = Calendar.getInstance();
        pStartDate.set(year, month, day);
        int[] monthsPerStage = new int[6];
        for (int i = 0; i < 6; i++){
            System.out.println("How many months does stage " + (i+1)+ " last?");
            monthsPerStage[i] = scanner.nextInt();
            scanner.nextLine();
        }
        String message = company.addProject(name, managerID, clientID, pStartDate, monthsPerStage);
        System.out.println(message);
    }

    public void editProject(){
        System.out.println("ok");
    }

    public void consultProject(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            String message = company.consultProject(projectID);
            System.out.println(message);
        }
    }

    public void consultStages(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            String message = company.consultStages(projectID);
            System.out.println(message);
        }
    }

    public void finishStage(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            String message = company.finishStage(projectID, false);
            System.out.println(message);
            if (!message.equals("Error: Invalid project ID.")) {
                boolean selectionOK;
                do {
                    selectionOK = true;
                    System.out.println("Select \n 1: Finish \n 0: Cancel");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice){
                        case 1:
                            message = company.finishStage(projectID, true);
                            System.out.println(message);
                            break;
                        case 0:
                            break;
                        default:
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                    }
                } while (!selectionOK);
            }
        }
    }

    public void consultCapsule(){
        String message = company.consultCapsule(-1, null);
        System.out.println(message);
        if (!message.equals("Error: There are no searchable capsules registered.")){
            boolean selectionOK;
            int choice;
            String filter = null;
            do {
                selectionOK = true;
                System.out.println("\n 1: Search String \n 2: Project \n 3: Stage \n 4: Collaborator");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter search string:");
                        filter = scanner.nextLine();
                    }
                    case 2 -> {
                        String projects = company.showAllProjects();
                        System.out.println(projects);
                        if (!projects.equals("Error: There are no projects registered yet.")){
                            System.out.println("Enter project ID:");
                            filter = scanner.nextLine();
                        }
                    }
                    case 3 -> {
                        int stage;
                        do {
                            selectionOK = true;
                            System.out.println("Select stage: \n 0: Start \n 1: Analysis \n 2: Design \n 3: Execution \n 4: Close \n 5: Monitoring");
                            stage = scanner.nextInt();
                            scanner.nextLine();
                            if (stage < 0 || stage > 5) {
                                selectionOK = false;
                                System.out.println("Error: Enter a valid option");
                                scanner.nextLine();
                            }
                        } while (!selectionOK);
                        filter = String.valueOf(stage);
                    }
                    case 4 -> {
                        String collaborators = company.showCollaborators();
                        System.out.println(collaborators);
                        if (!collaborators.equals("Error: There are no collaborators registered yet.")){
                            System.out.println("Enter collaborator ID:");
                            filter = scanner.nextLine();
                        }
                    }
                    default -> {
                        selectionOK = false;
                        System.out.println("Error: Enter a valid option");
                        scanner.nextLine();
                    }
                }
            } while (!selectionOK);
            if (filter != null){
                message = company.consultCapsule(choice, filter);
                System.out.println(message);
            }
        }
    }

    public void publishCapsule(){
        String message = company.publishCapsule(null);
        System.out.println(message);
        if (!message.equals("Error: There are no publishable capsules yet.")){
            System.out.println("Enter capsule ID:");
            String capsuleID = scanner.nextLine();
            message = company.publishCapsule(capsuleID);
            System.out.println(message);
        }
    }

    public void assignCollaboratorToProject(){
        String projects = company.showProjects();
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            String collaborators = company.showCollaborators();
            if (!collaborators.equals("Error: There are no collaborators registered yet.")){
                System.out.println(projects);
                System.out.println("Enter project ID:");
                String projectID = scanner.nextLine();
                System.out.println(collaborators);
                System.out.println("Enter collaborator ID:");
                String collaboratorID = scanner.nextLine();
                String message = company.assignCollaboratorToProject(projectID, collaboratorID);
                System.out.println(message);
            } else {
                System.out.println(collaborators);
            }
        }
        else {
            System.out.println(projects);
        }
    }

    public void changeCollaboratorFromProject(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter current project ID:");
            String projectID = scanner.nextLine();
            String message = company.changeCollaboratorFromProject(projectID, null, null);
            System.out.println(message);
            if (!message.equals("Error: Invalid Project ID.") && !message.equals("Error: There are no collaborators in this project yet.")){
                System.out.println("Enter collaborator ID:");
                String collaboratorID = scanner.nextLine();
                boolean selectionOK;
                do{
                    selectionOK = true;
                    System.out.println("\nWhat do you want to do? \n 1: Change to a new project \n 2: Just remove");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1 -> {
                            System.out.println(projects);
                            System.out.println("Enter new project ID:");
                            String newProjectID = scanner.nextLine();
                            message = company.changeCollaboratorFromProject(projectID, newProjectID, collaboratorID);
                        }
                        case 2 -> message = company.changeCollaboratorFromProject(projectID, null, collaboratorID);
                        default -> {
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                        }
                    }
                } while (!selectionOK);
                System.out.println(message);
            }
        }
    }

    public void reportKnowledgeProgress(){
        String message = company.showProjects();
        System.out.println(message);
        if (!message.equals("Error: You don't have projects assigned yet.") &&
                !message.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            message = company.reportKnowledgeProgress(projectID);
            System.out.println(message);
        }
    }

    public void reportKnowledgeManagement(){
        String message = company.showProjects();
        System.out.println(message);
        if (!message.equals("Error: You don't have projects assigned yet.") &&
                !message.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            message = company.reportKnowledgeManagement(projectID);
            System.out.println(message);
        }
    }

    public void registerCapsule(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            int stage, type;
            boolean selectionOK;
            do {
                selectionOK = true;
                System.out.println("Select stage: \n 0: Start \n 1: Analysis \n 2: Design \n 3: Execution \n 4: Close \n 5: Monitoring");
                stage = scanner.nextInt();
                scanner.nextLine();
                if (stage < 0 || stage > 5){
                    selectionOK = false;
                    System.out.println("Error: Enter a valid option");
                    scanner.nextLine();
                }
            } while (!selectionOK);
            do {
                selectionOK = true;
                System.out.println("Select type: \n 0: Technical \n 1: Management \n 2: Domain \n 3: Experience");
                type = scanner.nextInt();
                scanner.nextLine();
                if (type < 0 || type > 3){
                    selectionOK = false;
                    System.out.println("Error: Enter a valid option");
                    scanner.nextLine();
                }
            } while (!selectionOK);
            System.out.println("Enter lesson learned:");
            String lesson = scanner.nextLine();
            System.out.println("Enter text:");
            String text = scanner.nextLine();
            String message = null;
            do {
                int choice;
                selectionOK = true;
                System.out.println("Is an organization capsule? \n 1: Yes \n 2: No");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1){
                    System.out.println("Enter standard name:");
                    String standardName = scanner.nextLine();
                    message = company.registerCapsule(projectID, stage, type, lesson, text, standardName);
                } else if (choice == 2){
                    do {
                        System.out.println("Is the project capsule private? \n 1: Yes \n 2: No");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice < 1 || choice > 2){
                            selectionOK = false;
                            System.out.println("Error: Enter a valid option");
                            scanner.nextLine();
                        }
                    } while (!selectionOK);
                    boolean isPrivate = (choice == 1);
                    message = company.registerCapsule(projectID, stage, type, lesson, text, isPrivate);
                } else {
                    selectionOK = false;
                    System.out.println("Error: Enter a valid option");
                    scanner.nextLine();
                }
            } while (!selectionOK);
            System.out.println(message);
        }
    }

    public void classifyCapsule(){
        String projects = company.showProjects();
        System.out.println(projects);
        if (!projects.equals("Error: You don't have projects assigned yet.") &&
                !projects.equals("Error: There are no projects registered yet.")){
            System.out.println("Enter project ID:");
            String projectID = scanner.nextLine();
            String capsules = company.showCapsules(projectID);
            System.out.println(capsules);
            if (!capsules.equals("Error: Invalid project ID.") &&
            !capsules.equals("Error: The project has no capsules registered yet.") &&
            !capsules.equals("Error: The project has no capsules to classify.")){
                System.out.println("Enter capsule ID:");
                String capsuleID = scanner.nextLine();
                boolean selectionOK;
                int choice;
                String message = "";
                do {
                    selectionOK = true;
                    System.out.println("Do you approve this capsule? \n 1: Yes \n 2: No");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 1){
                        message = company.approveCapsule(projectID, capsuleID, -1);
                        System.out.println(message);
                        if (message.equals("Select restriction reason:")){
                            do{
                                selectionOK = true;
                                System.out.println(" 1: Confidential \n 2: Contractual \n 3: Business secrecy \n 4: Trade secret");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                message = company.approveCapsule(projectID, capsuleID, choice);
                                if (choice < 1 || choice > 4){
                                    selectionOK = false;
                                    System.out.println("Error: Enter a valid option");
                                    scanner.nextLine();
                                }
                            } while (!selectionOK);
                            System.out.println(message);
                        }
                    } else if (choice == 2){
                        do {
                            selectionOK = true;
                            System.out.println("Select rejection reason: \n 1: Incomplete \n 2: Ambiguous \n 3: Inconsistent \n 4: Not relevant");
                            choice = scanner.nextInt();
                            scanner.nextLine();
                            if (choice < 1 || choice > 4){
                                selectionOK = false;
                                System.out.println("Error: Enter a valid option");
                                scanner.nextLine();
                            } else {
                                message = company.rejectCapsule(projectID, capsuleID, choice);
                            }
                        } while (!selectionOK);
                        System.out.println(message);
                    } else {
                        selectionOK = false;
                        System.out.println("Error: Enter a valid option");
                        scanner.nextLine();
                    }
                } while (!selectionOK);
            }
        }
    }

    public void changePassword(){
        System.out.println("Enter your current password:");
        String currentPassword = scanner.nextLine();
        System.out.println("Enter the new password:");
        String newPassword = scanner.nextLine();
        String message = company.changePassword(currentPassword, newPassword);
        System.out.println(message);
    }
}