package vitals;

public class Main {

    static boolean isBatteryManagementSystemMeasuresWithinRange(float value, float min, float max, String message, boolean warningEnabled, String warningMessage) {
        if (value < min || value > max) {
            System.out.println(message);
            return false;
        }
        if (warningEnabled) {
            float warningLowerLimit = min + 0.05f * (max - min);
            float warningUpperLimit = max - 0.05f * (max - min);
            if (value < warningLowerLimit) {
                System.out.println("Warning: " + warningMessage + " approaching discharge");
            } else if (value > warningUpperLimit) {
                System.out.println("Warning: " + warningMessage + " approaching charge-peak");
            }
        }
        return true;
    }

    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        return isBatteryManagementSystemMeasuresWithinRange(temperature, 0, 45, "Temperature out of range!", true, "Temperature") &&
               isBatteryManagementSystemMeasuresWithinRange(soc, 20, 80, "State of Charge out of range!", true, "State of Charge") &&
               isBatteryManagementSystemMeasuresWithinRange(chargeRate, 0, 0.8f, "Charge Rate out of range!", true, "Charge Rate");
    }

    public static void main(String[] args) {
        assert (batteryIsOk(25, 70, 0.7f));
        assert (!batteryIsOk(50, 85, 0.0f));
        assert (!batteryIsOk(-10, 70, 0.5f));
        assert (!batteryIsOk(25, 90, 0.5f));
        assert (!batteryIsOk(25, 70, 1.0f));
        assert (batteryIsOk(0, 20, 0.8f));
    }
}
