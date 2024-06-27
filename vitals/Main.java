package vitals;

public class Main {
   
    static boolean isBatteryManagementSystemMeasuresWithinRange(float value, float min, float max, String message) {
        if (value < min || value > max) {
            System.out.println(message);
            return false;
        }
        return true;
    }

   
    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        return isBatteryManagementSystemMeasuresWithinRange(temperature, 0, 45, "Temperature out of range!") &&
               isBatteryManagementSystemMeasuresWithinRange(soc, 20, 80, "State of Charge out of range!") &&
               isBatteryManagementSystemMeasuresWithinRange(chargeRate, 0, 0.8f, "Charge Rate out of range!");
    }

    public static void main(String[] args) {
        assert (batteryIsOk(25, 70, 0.7f)) ;
        assert (!batteryIsOk(50, 85, 0.0f));
        assert (!batteryIsOk(-10, 70, 0.5f));
        assert (!batteryIsOk(25, 90, 0.5f)) ;
        assert (!batteryIsOk(25, 70, 1.0f));
        assert (batteryIsOk(0, 20, 0.8f)) ;
    }
}
