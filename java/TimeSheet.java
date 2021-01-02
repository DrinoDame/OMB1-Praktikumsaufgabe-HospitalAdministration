package HospitalAdministration;

public class TimeSheet {

    private int allocatedTime;
    private int actualTime;

    // Constructor
    static void minToHours(int toConvert){
        int hours = toConvert / 60;
        int minutes = toConvert % 60;
        System.out.print(hours + " hours and " + minutes + " minutes.");
    }

    // ----- Getter -----
    int getActualTime(){
        return actualTime;
    }

    int getAllocatedTime() { return allocatedTime; }

    // ----- Methods -----
    int workedTimeToAllocatedTime(){
        return actualTime - allocatedTime;
    }

    int setAllocatedTime(int time){
        allocatedTime = time;
        return allocatedTime;
    }

    int changeActualTime(int time){
        actualTime += time;
        return actualTime;
    }

}
