package vitals;

public class Main {

    static boolean isWithinRange(float value, float min, float max, String message) {
        if (value < min || value > max) {
            System.out.println(message);
            return false;
        }
        return true;
    }

    static void checkWarning(float value, float min, float max, String warningMessage) {
        float warningLowerLimit = min + 0.05f * (max - min);
        float warningUpperLimit = max - 0.05f * (max - min);
        if (value < warningLowerLimit) {
            System.out.println("Warning: " + warningMessage + " approaching discharge");
        } else if (value > warningUpperLimit) {
            System.out.println("Warning: " + warningMessage + " approaching charge-peak");
        }
    }

    static boolean checkTemperature(float temperature) {
        if (!isWithinRange(temperature, 0, 45, "Temperature out of range!")) {
            return false;
        }
        checkWarning(temperature, 0, 45, "Temperature");
        return true;
    }

    static boolean checkSoc(float soc) {
        if (!isWithinRange(soc, 20, 80, "State of Charge out of range!")) {
            return false;
        }
        checkWarning(soc, 20, 80, "State of Charge");
        return true;
    }

    static boolean checkChargeRate(float chargeRate) {
        if (!isWithinRange(chargeRate, 0, 0.8f, "Charge Rate out of range!")) {
            return false;
        }
        checkWarning(chargeRate, 0, 0.8f, "Charge Rate");
        return true;
    }

    static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
        return checkTemperature(temperature) &&
               checkSoc(soc) &&
               checkChargeRate(chargeRate);
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
