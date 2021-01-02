package HospitalAdministration;

public abstract class Employee extends Person {

    float hourlyWage;
    int taxCategory;
    TimeSheet account;

    Employee(String name, int age, String type, float hourlyWage, int taxCategory, TimeSheet account) {
        super(name, age, type);
        this.hourlyWage = hourlyWage;
        this.taxCategory = taxCategory;
        this.account = account;
    }

    abstract float getSalary(boolean gross);

}
