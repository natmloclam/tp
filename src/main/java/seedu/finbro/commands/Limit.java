package seedu.finbro.commands;

import seedu.finbro.Ui;

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

    public static void setLimit(double limit, Ui ui) {
        ui.showChangeLimitWarning(limit);
        String input = ui.readCommand();
        if (input.equals("yes")) {
            Limit.limit = limit;
        } else if (input.equals("no")) {
            ui.showCancelChangeLimitMessage();
        } else {
            ui.showCancelChangeLimitMessage();
        }
    }
}