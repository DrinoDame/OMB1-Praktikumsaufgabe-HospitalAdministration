package HospitalAdministration;

public class Patient extends Person {

    boolean hasStatutoryInsurance;

    Patient(String name, int age, String type, boolean hasStatutoryInsurance) {
        super(name, age, type);
        this.hasStatutoryInsurance = hasStatutoryInsurance;
    }


}
