package lesson1a;

public enum DayOfWeek {
    Monday(8), Tuesday(8), Wednesday(8),
    Thursday(8), Friday(8), Saturday(0), Sunday(0);

    public static final int HOURS_OF_DAY = 8;
    public int customHours;

    DayOfWeek(int customHours) {
        this.customHours = customHours;
    }
}
