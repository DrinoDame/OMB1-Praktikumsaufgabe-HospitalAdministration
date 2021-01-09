package HospitalAdministration;

public abstract class Employee extends Person {

    float hourlyWage;
    int taxCategory;
    TimeSheet account;

    Employee(String name, int age, float hourlyWage, int taxCategory, TimeSheet account) {
        super(name, age);
        this.hourlyWage = hourlyWage;
        this.taxCategory = taxCategory;
        this.account = account;
    }

    abstract float getSalary(boolean gross);

}
