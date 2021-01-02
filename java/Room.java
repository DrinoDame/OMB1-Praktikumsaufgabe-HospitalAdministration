package HospitalAdministration;

import java.util.ArrayList;

public class Room {
    // ArrayLists für die Personen im Raum
    ArrayList<Patient> patientsInRoom = new ArrayList<Patient>();
    ArrayList<Employee> employeesInRoom = new ArrayList<Employee>();

    // Farben für die sout's... :D
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";


    private int numberOfBeds;
    private int roomNumber;

    // Constructor
    Room(int numberOfBeds, int roomNumber){
        this.numberOfBeds = numberOfBeds;
        this.roomNumber = roomNumber;
    }

    // ----- Getter -----
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    // ----- Methods -----
    void addPatient(Person personToAdd){
        Patient patientToAdd = (Patient) personToAdd;
        if(numberOfBeds > patientsInRoom.size()){
            patientsInRoom.add(patientToAdd);
            System.out.println(ANSI_CYAN + "Patient " + personToAdd.name + " has been moved to room number " + roomNumber + "." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Sorry, the room " + roomNumber + " is already full." + ANSI_RESET);
        }
    }

    void addEmployee(Person personToAdd){
        Employee employeeToAdd = (Employee) personToAdd;
        employeesInRoom.add(employeeToAdd);
        System.out.println(ANSI_CYAN + employeeToAdd.type + " " + employeeToAdd.name + " has been moved to room number " + roomNumber + "." + ANSI_RESET);
    }

    void personsInRoom(){
        System.out.println("In room number " + roomNumber + " with " + numberOfBeds + " beds are the following patients:");
        for (Patient patient : patientsInRoom) {
            System.out.println(patient.name);
        }
        System.out.println("Also there are the following employees:");
        for (Employee employee : employeesInRoom) {
            System.out.println(employee.name);
        }
    }

}
