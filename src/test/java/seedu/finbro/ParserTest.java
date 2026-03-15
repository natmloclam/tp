package seedu.finbro;

import org.junit.jupiter.api.Test;
import seedu.finbro.exception.FinbroException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_addValidExpense_expenseAdded() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        Parser.parse("add 12.50 food 15/03/2026", expenses, ui);

        assertEquals(1, expenses.size());
    }
}