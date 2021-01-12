package HospitalAdministration;

public class Main {

    public static void main(String[] args) throws PersonNotFoundException, RoomNotFoundException {

        // Farben für die sout's... :D
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CYAN = "\u001B[36m";

        // neues Management
        PersonnelManagement managementOfTestHospital = new PersonnelManagement();

        // ----- hard gecodeder Stuff, für einfacheres Testen -----
        /*
            // Doctor Tina
            TimeSheet tinasTime = new TimeSheet();
            Doctor tina = new Doctor("Tina", 38, 100.0f, 2, tinasTime, Doctor.Degree.Specialist);
            // Time Werte Tina (Überstunden)
            tinasTime.setAllocatedTime(7200);
            tinasTime.changeActualTime(7260);
            // managementOfTestHospital zuweisen
            managementOfTestHospital.persons.add(tina);
    
            // Nurse Paul
            TimeSheet paulsTime = new TimeSheet();
            Nurse paul = new Nurse("Paul", 55, 50.0f, 1, paulsTime, Nurse.Shift.nightShift);
            // Time Werte Paul (Minusstunden)
            paulsTime.setAllocatedTime(9600);
            paulsTime.changeActualTime(9000);
            // managementOfTestHospital zuweisen
            managementOfTestHospital.persons.add(paul);

            // Patienten
            Patient tom = new Patient("Tom", 40,  true);
            managementOfTestHospital.persons.add(tom);

            Patient klaus = new Patient("Klaus", 78, true);
            managementOfTestHospital.persons.add(klaus);

            // nicht gesetzlich versichert
            Patient amy = new Patient("Amy", 36, false);
            managementOfTestHospital.persons.add(amy);

            // Räume
            Room one = new Room(1, 111);
            managementOfTestHospital.rooms.add(one);

            Room two = new Room(2, 222);
            managementOfTestHospital.rooms.add(two);

            Room three = new Room( 3, 333);
            managementOfTestHospital.rooms.add(three);
        */
        // ----- Ende hard gecodeder Stuff -----

        // Start update
        System.out.println(ANSI_CYAN + "With this tool you can manage the persons in a hospital. Type /help to see all the possible commands." + ANSI_RESET);
        managementOfTestHospital.update();

    }
}
