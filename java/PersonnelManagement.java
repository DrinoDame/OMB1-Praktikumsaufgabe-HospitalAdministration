package HospitalAdministration;

import java.util.ArrayList;
import java.util.Scanner;

public class PersonnelManagement {
    // ArrayLists für Personen und Räume
    ArrayList<Person> persons = new ArrayList<Person>();
    ArrayList<Room> rooms = new ArrayList<Room>();

    // Farben für die sout's... :D
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    // while-Schleife mit Switch
    void update() throws RoomNotFoundException, PersonNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning){
            String userInput = scanner.next();
            switch (userInput){
                case "/help":
                    System.out.println("""
                           The following commands can be used:\s
                           /help: see all possible commands\s
                           /exit: exit the program\s
                           /newPatient: create a new patient\s
                           /newEmployee: create a new employee\s
                           /newRoom: create a new room\s
                           /releasePatient: release a patient from the hospital\s
                           /deleteRoom: delete an existing room by room number\s
                           /addPersonToRoom: assign a person (patient or employee) to a room\s
                           /movePatient: move patient to another room\s
                           /setAllocatedTime: set monthly allocated working time of an employee\s
                           /changeWorkedTime: change the actually worked time this month of an employee\s
                           /salary: get the gross or net salary of an employee\s
                           /overtime: look up if employee has overtime\s
                           /minToHours: calculates minutes to hours\s
                           /personsInRoom: overview of the persons in a room\s
                           /hospitalOverview: overview of the complete hospital""");
                    break;

                case "/exit":
                    System.out.println(ANSI_RED + "Self destruction activated." + ANSI_RESET);
                    System.out.println("Hospital explodes in...");
                    System.out.println("3...");
                    System.out.println("2...");
                    System.out.println("1...");
                    System.out.println(ANSI_RED + "BOOOOOOM" + ANSI_RESET);
                    System.out.println("     _.-^^---....,,--\n" +
                                       " _--                  --_\n" +
                                       "<                        >)\n" +
                                       "|                         |\n" +
                                       " \\._                   _./\n" +
                                       "    ```--. . , ; .--'''\n" +
                                       "          | |   |\n" +
                                       "       .-=||  | |=-.\n" +
                                       "       `-=#$%&%$#=-'\n" +
                                       "          | ;  :|\n" +
                                       " _____.,-#%&$@%#&#~,._____");
                    isRunning = false;
                    break;

                case "/newPatient":
                    System.out.println("What's the name of the new patient?");
                    String patientName = scanner.next();
                    System.out.println("How old is the patient?");
                    int patientAge = scanner.nextInt();
                    System.out.println("Has the patient statutory insurance? Please answer with true or false.");
                    boolean hasStatutoryInsurance = scanner.nextBoolean();
                    Patient newPatient = new Patient(patientName, patientAge, hasStatutoryInsurance);
                    createPerson(newPatient);
                    break;

                case "/newEmployee":
                    System.out.println("Would you like to hire a doctor or a nurse?");
                    String newEmployeesType = scanner.next();
                    System.out.println("What's the name of the " + newEmployeesType + "?");
                    String newEmployeesName = scanner.next();
                    System.out.println("How old is " + newEmployeesName +"?");
                    int newEmployeesAge = scanner.nextInt();
                    System.out.println("What's the hourly wage?");
                    float newEmployeesWage = scanner.nextFloat();
                    System.out.println("Which tax category has " + newEmployeesName + "? Choose between 0 and 6.");
                    int newEmployeesTaxCategory = scanner.nextInt();
                    System.out.println("Please enter the shift for nurses or the degree for physicians.\n " +
                            "Possible degrees: DoctorInTraining, AssistentPhysician, Specialist, SeniorPhysician, ChiefPhysician.\n" +
                            "Possible shifts: nightShift, earlyShift, lateShift");
                    String degreeOrShift = scanner.next();
                    createPerson(newEmployeesType, newEmployeesName, newEmployeesAge, newEmployeesWage, newEmployeesTaxCategory, degreeOrShift);
                    break;

                case "/newRoom":
                    System.out.println("How many beds are in the room?");
                    int numberOfBeds = scanner.nextInt();
                    System.out.println("Which number has the room?");
                    int roomNumber = scanner.nextInt();
                    createRoom(numberOfBeds, roomNumber);
                    break;

                case "/releasePatient":
                    System.out.println("Which patient you want to release?");
                    String patientToRelease = scanner.next();
                    releasePatient(patientToRelease);
                    break;

                case "/deleteRoom":
                    System.out.println("Please enter the room number of the room you want to delete.");
                    int roomToDelete = scanner.nextInt();
                    deleteRoom(roomToDelete);
                    break;

                case "/addPersonToRoom":
                    System.out.println("Which person you want to assign a room?");
                    String patientsName = scanner.next();
                    System.out.println("What is the room number of the room " + patientsName + " should be added to?");
                    int addPatientToRoomNumber = scanner.nextInt();
                    addPersonToRoom(patientsName, addPatientToRoomNumber);
                    break;

                case "/movePatient":
                    System.out.println("Which patient you want to shift?");
                    String patientToMove = scanner.next();
                    System.out.println("In which room the patient should move?");
                    int moveToRoom = scanner.nextInt();
                    movePatient(patientToMove, moveToRoom);
                    break;

                case "/setAllocatedTime":
                    System.out.println("Which employees allocated time you want to set?");
                    String setAllocatedTimeOf = scanner.next();
                    System.out.println("Please enter the allocated time in minutes.");
                    int allocatedTime = scanner.nextInt();
                    setAllocatedTime(setAllocatedTimeOf, allocatedTime);
                    break;

                case "/changeWorkedTime":
                    System.out.println("Which employees worked time you want to change?");
                    String changeWorkedTimeOf = scanner.next();
                    System.out.println("Please enter the minutes that should be added or removed(-).");
                    int minutesToChange = scanner.nextInt();
                    changeActualTime(changeWorkedTimeOf, minutesToChange);
                    break;

                case "/salary":
                    System.out.println("From which employee you want to know the salary for this month?");
                    String salaryOf = scanner.next();
                    System.out.println("Would you like to know the gross or net salary?");
                    String grossOrNet = scanner.next();
                    getSalaryOfEmployee(salaryOf, grossOrNet);
                    break;

                case "/overtime":
                    System.out.println("From which employee you want to check the overtime?");
                    String overtimeOf = scanner.next();
                    hasOvertime(overtimeOf);
                    break;

                case "/minToHours":
                    System.out.println("How many minutes you want to convert in hours?");
                    int minutesToConvert = scanner.nextInt();
                    TimeSheet.minToHours(minutesToConvert);
                    break;

                case "/personsInRoom":
                    System.out.println("From which room you want to have a overview?");
                    int overviewOverRoom = scanner.nextInt();
                    personsInRoom(overviewOverRoom);
                    break;

                case "/hospitalOverview":
                    hospitalOverview();
                    break;

                default:
                    System.out.println("Sorry, your input didn't match any of the commands. Please try again.");
            }
        }
    }

    // ----- Diverse Getter -----

    Person getPersonByName(String name) throws PersonNotFoundException {
        for (Person person : persons) {
            if(person.name.equals(name)){
                return person;
            }
        }
        throw new PersonNotFoundException(ANSI_RED + "Sorry, the person you are looking for is not existing." + ANSI_RESET);
    }

    Room getRoomByNumber(int roomNumber) throws RoomNotFoundException{
        for (Room room : rooms){
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        throw new RoomNotFoundException(ANSI_RED + "There is no room with room number " + roomNumber + "." + ANSI_RESET);
    }

    Room getRoomOfPatientByName(String patientsName) throws RoomNotFoundException{
        for (Room room : rooms) {
            for (Patient patient : room.patientsInRoom) {
                if(patient.name.equals(patientsName)){
                    return room;
                }
            }
        }
        throw new RoomNotFoundException(ANSI_RED + "Couldn't find a room with " + patientsName + " in it." + ANSI_RESET);
    }


    // ----- Eigentliche Methoden -----

    // createPerson für Patienten: fügt neuen Patienten der ArrayList persons hinzu.
    void createPerson(Patient newPatient){
        try {
            // wenn Person bereits vorhanden, dann Fehlermeldung ausgeben
            Person mightAlreadyExist = getPersonByName(newPatient.name);
            if(mightAlreadyExist.name.equals(newPatient.name)){
                System.out.println(ANSI_RED + "Sorry, there is already a patient named " + mightAlreadyExist.name + " in the hospital." + ANSI_RESET);
            }
        } catch (PersonNotFoundException e) {
            // wenn Person _nicht_ vorhanden ist, dann soll neue Person angelegt werden
            persons.add(newPatient);
            System.out.println(ANSI_CYAN + "Patient " + newPatient.name + " has entered the hospital." + ANSI_RESET);
        }
    }

    // createPerson für Personal. Prüft auf Doctor oder Nurse und legt neuen employee mit passendem degree oder shift an.
    // Erstellt außerdem ein TimeSheet. employee wird der ArrayList persons hinzugefügt.
    void createPerson(String employeeType, String employeeName, int employeeAge, float employeeWage, int employeeTaxCategory, String degreeOrShift){
        try {
            // wenn Person bereits existiert, Fehlermeldung ausgeben.
            Person mightAlreadyExist = getPersonByName(employeeName);
            if(mightAlreadyExist.name.equals(employeeName)){
                System.out.println(ANSI_RED + "Sorry, there is already an employee named " + mightAlreadyExist.name + " in the hospital." + ANSI_RESET);
            }
        } catch (PersonNotFoundException e) {
            // ansonsten auf Doctor oder Nurse prüfen und Angestellten anlegen
            TimeSheet account = new TimeSheet();

            if (employeeType.equals("doctor")) {
                Degree degree = null;
                switch (degreeOrShift) {
                    case "DoctorInTraining":
                        degree = Degree.DoctorInTraining;
                        break;
                    case "AssistentPhysician":
                        degree = Degree.AssistentPhysician;
                        break;
                    case "Specialist":
                        degree = Degree.Specialist;
                        break;
                    case "SeniorPhysician":
                        degree = Degree.SeniorPhysician;
                        break;
                    case "ChiefPhysician":
                        degree = Degree.ChiefPhysician;
                        break;
                    default:
                        System.out.println(ANSI_RED + "Entered degree didn't match. Please try again." + ANSI_RESET);
                }
                Doctor newDoctor = new Doctor(employeeName, employeeAge, employeeWage, employeeTaxCategory, account, degree);
                persons.add(newDoctor);
                System.out.println(ANSI_CYAN + newDoctor.name + " is now working as a " + degree + " for the hospital." + ANSI_RESET);

            } else if (employeeType.equals("nurse")) {
                Shift shift = null;
                switch (degreeOrShift) {
                    case "nightShift":
                        shift = Shift.nightShift;
                        break;
                    case "earlyShift":
                        shift = Shift.earlyShift;
                        break;
                    case "lateShift":
                        shift = Shift.lateShift;
                        break;
                    default:
                        System.out.println(ANSI_RED + "Entered shift didn't match. Please try again." + ANSI_RESET);
                }
                Nurse newNurse = new Nurse(employeeName, employeeAge, employeeWage, employeeTaxCategory, account, shift);
                persons.add(newNurse);
                System.out.println(ANSI_CYAN + newNurse.name + " is now working as a nurse in " + shift + " for the hospital." + ANSI_RESET);

            } else {
                // wenn Eingabe weder nurse noch doctor war, Fehlermeldung ausgeben
                System.out.println(ANSI_RED + "An error occurred. Couldn't create a new employee." + ANSI_RESET);
            }
        }
    }

    // Legt einen neuen Raum an und fügt ihn der ArrayList rooms hinzu.
    void createRoom(int numberOfBeds, int roomNumber){
        // Prüfen, ob Raum bereits existiert
        try {
            Room mightAlreadyExist = getRoomByNumber(roomNumber);
            if(mightAlreadyExist.getRoomNumber() == roomNumber){
                System.out.println(ANSI_RED + "Sorry, room " + roomNumber + " already exists." + ANSI_RESET);
            }
        } catch (RoomNotFoundException e){
            Room newRoom = new Room(numberOfBeds, roomNumber);
            rooms.add(newRoom);
            System.out.println(ANSI_CYAN + "A new room number " + roomNumber+ " with " + numberOfBeds + " beds was created." + ANSI_RESET);
        }
    }

    // Entfernt eine gewünschte Person aus der persons ArrayList
    void releasePatient(String patientToRelease) throws PersonNotFoundException, RoomNotFoundException {
        // Patient aus persons ArrayList entfernen
        Person toRelease = getPersonByName(patientToRelease);
        persons.remove(toRelease);
        // Patient aus ArrayList in Room entfernen
        Room patientsRoom = getRoomOfPatientByName(patientToRelease);
        patientsRoom.patientsInRoom.remove(toRelease);

        System.out.println(ANSI_CYAN + "Patient " + patientToRelease + " was released and is no longer a patient of the hospital.\n" +
                           "The bed in room " + patientsRoom.getRoomNumber() + " will soon be ready for another patient." + ANSI_RESET);
    }

    // Entfernt einen Raum anhand der roomNumber aus der ArrayList rooms
    void deleteRoom(int roomToDelete) throws RoomNotFoundException{
        Room toDelete = getRoomByNumber(roomToDelete);
        rooms.remove(toDelete);
        System.out.println(ANSI_CYAN + "Room " + roomToDelete + " was deleted." + ANSI_RESET);
    }

    // Person einem Raum hinzufügen
    void addPersonToRoom(String personsName, int roomNumber){
        try {
            Person personToAdd = getPersonByName(personsName);
            Room moveToRoom = getRoomByNumber(roomNumber);
            // Prüfen, ob's ein Patient oder Angestellter ist
            if(personToAdd instanceof Patient){
                moveToRoom.addPatient(personToAdd);
            } else {
                moveToRoom.addEmployee(personToAdd);
            }
        } catch (RoomNotFoundException e){
            System.out.println(e.getMessage());
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Patient verlegen in anderes Zimmer. Catch error, wenn Patient gesetzlich versichert.
    void movePatient(String patientToShift, int moveToRoom) throws PersonNotFoundException, RoomNotFoundException{
        Patient toShift = (Patient) getPersonByName(patientToShift);
        try {
            if (toShift.hasStatutoryInsurance == true) {
                throw new HasStatutoryInsuranceException(ANSI_RED + "The patient has Statutory Insurance. Therefore the room can not be changed." + ANSI_RESET);

            } else {
                // Raum finden, in dem Patient aktuell liegt. Für später.
                Room currentRoom = getRoomOfPatientByName(patientToShift);

                Room toMove = getRoomByNumber(moveToRoom);
                // wenn Patient _nicht_ bereits in dem Zimmer ist, dann hinzufügen und aus vorherigem Raum entfernen
                if(toMove.getRoomNumber() != currentRoom.getRoomNumber()){
                    toMove.addPatient(toShift);
                    currentRoom.patientsInRoom.remove(toShift);
                } else {
                    System.out.println(ANSI_RED + "You tried to move the patient to the room he already is in." + ANSI_RESET);
                }
            }
        } catch (HasStatutoryInsuranceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Sollzeit eines Employees setzen
    void setAllocatedTime(String employeesName, int allocatedTime) throws PersonNotFoundException{
        Employee employee = (Employee) getPersonByName(employeesName);
        int newAllocatedTime = employee.account.setAllocatedTime(allocatedTime);
        System.out.println(ANSI_CYAN + "The allocated time of " + employee.name + " has been set to " + newAllocatedTime + " minutes per month." + ANSI_RESET);
        System.out.print("This equals ");
        TimeSheet.minToHours(newAllocatedTime);
        System.out.println();
    }

    // Istzeit eines Employees ändern
    void changeActualTime(String employeesName, int minutesToChange){
        try {
            Employee employee = (Employee) getPersonByName(employeesName);
            int newActualTime = employee.account.changeActualTime(minutesToChange);
            System.out.println(ANSI_CYAN + "The worked time of " + employee.name + " has been set to " + newActualTime + " minutes this month." + ANSI_RESET);
            System.out.print("This equals ");
            TimeSheet.minToHours(newActualTime);
            System.out.println();
        } catch (PersonNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    // Gehalt eines Angestellten ausrechnen, brutto oder netto
    void getSalaryOfEmployee(String employeesName, String grossOrNet){
        try {
            Employee employee = (Employee) getPersonByName(employeesName);
            if (grossOrNet.equals("gross")) {
                float grossSalary = employee.getSalary(true);
                System.out.println(ANSI_CYAN + "The gross salary of " + employee.name + " this month is " + grossSalary + "." + ANSI_RESET);
            } else if (grossOrNet.equals("net")) {
                float netSalary = employee.getSalary(false);
                System.out.println(ANSI_CYAN + "The net salary of " + employee.name + " this month is " + netSalary + "." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Sorry, your input was neither 'gross' nor 'net'. Couldn't calculate the salary." + ANSI_RESET);
            }
        } catch (PersonNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    // eventuelle Überstunden eines Angestellen ausgeben
    void hasOvertime(String employeesName) throws PersonNotFoundException {
        Employee employee = (Employee) getPersonByName(employeesName);
        int difference = employee.account.workedTimeToAllocatedTime();
        if(difference < 0){
            System.out.println(ANSI_CYAN + "There are " + difference + " minutes on " + employee.name + "s account. " + employee.name + " has no overtime." + ANSI_RESET);
        } else {
            System.out.println(ANSI_CYAN + "This month " + employee.name + " already worked " + difference + " minutes more than specified." + ANSI_RESET);
        }
    }

    // Übersicht der Angestellten und Patienten in einem Raum
    void personsInRoom(int roomNumber) throws RoomNotFoundException {
        Room toOverview = getRoomByNumber(roomNumber);
        toOverview.personsInRoom();
    }

    // Komplette Übersicht über die zur Zeit im Krankenhaus befindlichen Personen und Räume, sowie Betten.
    void hospitalOverview(){

        int amountOfBeds = 0;
        for (Room room : rooms) {
            amountOfBeds += room.getNumberOfBeds();
        }

        int amountOfFullBeds = 0;
        for (Room room : rooms) {
            amountOfFullBeds += room.patientsInRoom.size();
        }

        int amountOfPatients = 0;
        for (Person person : persons) {
            if(person instanceof Patient){
                amountOfPatients ++;
            }
        }


        int amountOfDoctors = 0;
        for (Person person : persons) {
            if(person instanceof Doctor) {
                amountOfDoctors++;
            }
        }

        int amountOfNurses = 0;
        for (Person person : persons) {
            if(person instanceof Nurse){
                amountOfNurses ++;
            }
        }

        int amountOfEmployees = 0;
        for (Person person : persons) {
            if(person instanceof Employee){
                amountOfEmployees++;
            }
        }

        System.out.println("There are " + persons.size() + " persons in the hospital.\n" +
                           amountOfPatients + " are patients and " + amountOfEmployees + " are employees.\n" +
                           amountOfDoctors + " of the employees are doctors, the remaining " + amountOfNurses + " are nurses.\n" +
                           "The hospital has " + rooms.size() + " rooms with a total amount of " + amountOfBeds + " beds.\n" +
                           amountOfFullBeds + " beds are full.");
    }

}
