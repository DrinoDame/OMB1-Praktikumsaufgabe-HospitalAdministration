package HospitalAdministration;

public enum Shift {
    nightShift(25), earlyShift(5), lateShift(10);

    private final int bonus;

    Shift(int bonus){
        this.bonus = bonus;
    }

    public int bonus(){
        return bonus;
    }
}
