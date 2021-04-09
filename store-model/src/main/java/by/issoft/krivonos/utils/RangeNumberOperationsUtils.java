package by.issoft.krivonos.utils;

public class RangeNumberOperationsUtils {

    private final int MAX;
    private final int MIN;

    public RangeNumberOperationsUtils( int min, int max) {
        MAX = max;
        MIN = min;
    }

    public boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isInRange(int value) {
        return isInRange(value, MIN, MAX);
    }

}
