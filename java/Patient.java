package HospitalAdministration;

public class Patient extends Person{

    boolean hasStatutoryInsurance;

    Patient(String name, int age, boolean hasStatutoryInsurance) {
        super(name, age);
        this.hasStatutoryInsurance = hasStatutoryInsurance;
    }
}
