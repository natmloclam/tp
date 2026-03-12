package seedu.finbro.commands;

public class Limit {
    private static double limit;
    private static double spent;

    public Limit() {
        Limit.limit = 0;
        Limit.spent = 0;
    }

    public static double getLimit() {
        return limit;
    }

    public static double getSpent() {
        return spent;
    }

    public static void setLimit(double limit) {
        Limit.limit = limit;
    }
}