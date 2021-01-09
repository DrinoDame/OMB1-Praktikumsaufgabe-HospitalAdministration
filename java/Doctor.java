package HospitalAdministration;

public class Doctor extends Employee{

    Degree degree;

    // Constructor
    Doctor(String name, int age, float hourlyWage, int taxCategory, TimeSheet account, Degree degree) {
        super(name, age, hourlyWage, taxCategory, account);
        this.degree = degree;
    }

    @Override
    float getSalary(boolean gross) {
        // Zuerst: "Standard" Lohn ausrechnen, Später: Lohn plus Überstundenbonus
        float wage = (account.getActualTime() / 60) * hourlyWage;

        // Auf Überstunden prüfen, Bonus berechnen und addieren
        if (account.workedTimeToAllocatedTime() > 0) {
            float overtimeBonus = (float) ((account.workedTimeToAllocatedTime() / 60) * hourlyWage * 1.2);

            // wage überschreiben, da so später einfach damit weitergerechnet werden kann, auch wenn keine Überstunden gemacht wurden.
            wage = (wage - (account.workedTimeToAllocatedTime() / 60 * hourlyWage)) + overtimeBonus;
        }

        // Bonus für degree aufschlagen
        float grossSalary = wage * degree.bonus();

        // grossSalary ausgebene, wenn nur brutto gewünscht ist
        if(gross){
            return grossSalary;
        } else {
            float netSalary;
            switch (taxCategory){
                case 1:
                    netSalary = grossSalary - (grossSalary * 0.12f);
                    return netSalary;
                case 2:
                    netSalary = grossSalary - (grossSalary * 0.18f);
                    return netSalary;
                case 3:
                    netSalary = grossSalary - (grossSalary * 0.23f);
                    return netSalary;
                case 4:
                    netSalary = grossSalary - (grossSalary * 0.29f);
                    return netSalary;
                case 5:
                    netSalary = grossSalary - (grossSalary * 0.38f);
                    return netSalary;
                case 6:
                    netSalary = grossSalary - (grossSalary * 0.45f);
                    return netSalary;
                default:
                    System.out.println("The tax category was 0 or above 6. The returned salary is gross.");
                    return grossSalary;
            }
        }
    }

    // enum
    public enum Degree{
        DoctorInTraining(0), AssistentPhysician(1.1f), Specialist(1.25f), SeniorPhysician(1.4f), ChiefPhysician(1.8f);

        private final float bonus;

        Degree(float bonus) {
            this.bonus = bonus;
        }

        public float bonus() {
            return bonus;
        }

    }
}
