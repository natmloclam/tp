package seedu.finbro.commands;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.exception.FinbroException;
import seedu.finbro.utils.Limit;

public class SetLimitCommand extends Command {
    @Override
    public void execute(String input, ExpenseList expenseList, Ui ui, Storage storage) throws FinbroException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            ui.showLimit();
            return;
        }

        double limit;
        // check if limit is of valid type
        try {
            limit = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            throw new FinbroException("Monthly spending limit must be a number");
        }

        // check if limit is a valid value
        if (limit < 0) {
            throw new FinbroException("Monthly spending limit must be at least $0");
        }

        Limit.setLimit(limit, ui);
        ui.showLimit();
    }

    @Override
    public String getHelpMessage() {
        return """
                Sets the monthly spending limit.
                Format: limit <amount>
                Use: Creates or replaces the current monthly budget limit.
                Note: amount must be positive.""";
    }
}
