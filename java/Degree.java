package HospitalAdministration;

public enum Degree {
    DoctorInTraining(0), AssistentPhysician(1.1f), Specialist(1.25f), SeniorPhysician(1.4f), ChiefPhysician(1.8f);

    private final float bonus;

    Degree(float bonus) {
        this.bonus = bonus;
    }

    public float bonus() {
        return bonus;
    }
}
