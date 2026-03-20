package seedu.finbro.commands;

import seedu.finbro.parser.Parser;
import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.exception.FinbroException;

public class HelpCommand extends Command {
    private final String arg;

    public HelpCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage) throws FinbroException {
        Command command;
        try {
            command = Parser.parse(arg);
        } catch (FinbroException e) {
            ui.showHelpMessage(getHelpMessage());
            return;
        }

        ui.showCommandHelpMessage(command);
    }

    @Override
    public String getHelpMessage() {
        return """
               Valid Commands:
               add - Add a new expense
               delete - Delete an expense
               view - View your expenses
               limit - Set/view your monthly spending limit
               edit limit - Edit your monthly spending limit
               
               Enter "help <command>" for a more detailed explanation on how to use each command""";
    }
}
