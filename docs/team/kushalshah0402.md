---
layout: page
title: Kushal's Project Portfolio Page
---

### Project: FinBro

Finbro is a command-line personal finance management application designed to help users track expenses, monitor spending habits, and manage a monthly budget efficiently.

It is built using Java and follows a modular, command-based architecture, where user inputs are parsed into executable commands.

Given below are my contributions to the project.

* **New Feature**: Added the `add` command
  * What it does: Allows the user to record a new expense either via direct mode (single command with all parameters) or walkthrough mode (interactive prompts).
  * Justification: The dual-mode design improves usability by supporting both experienced users who prefer fast entry and new users who benefit from guided input.
  * Highlights:
    * Invalid dates, non-positive and non-number amounts are all met with applicable error messages and prompts the user with the correct format
    * Implementing the walkthrough mode required careful handling of sequential input validation with repeated prompts on invalid input. The confirmation step before saving reduces accidental entries.

* **New Feature**: Added the `view all` command
  * What it does: Retrieves all recorded expenses from the system and displays them in a structured, readable format — showing the amount, category, and date for each entry, along with a computed total expenditure at the end.
  * Justification: Users need a way to get a full overview of their spending history at a glance. Without this, there is no way to review past expenses in a single command.
  * Highlights: 
    * Handles the edge case where no expenses have been recorded yet, displaying an appropriate message instead of an empty or broken output
    * Total expenditure is computed dynamically by iterating through the expense list, so it is always accurate and up to date
    * Display logic is fully handled by `Ui` keeping `ViewCommand` focused solely on retrieving the data, which follows the separation of concerns principle


* **New Feature**: Added the `Storage` component
  * What it does: Handles persistent loading and saving of expenses and budget limits to a local `.txt` file. On startup, expenses and the budget limit are loaded automatically. After every command, the updated data is saved.
  * Justification: Persistence is a core requirement — without it, all expense records would be lost every time the app closes. The storage layer ensures data survives across sessions without requiring a database.
  * Highlights: 
    * Implemented corruption handling — invalid or malformed lines are logged and skipped rather than crashing the app
    * The budget limit is stored as the first line in a special `LIMIT | <value>` format, and is read separately before expenses are processed
    * If the data file does not exist on first launch, the app gracefully creates a new one instead of throwing an error
    * Amount validation is enforced during loading to prevent negative or zero values from being loaded into the system

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=kushalshah0402&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

* **Enhancements implemented**:
  * Enhanced budget limit feedback after adding an expense (Pull request #32)
    * What it does: After every expense is added, the system checks the user's total spending against their set budget limit and warns them if they are close to or have exceeded it.
    * Justification: Previously, users had no immediate feedback on their budget status after adding an expense. This enhancement makes the app proactive rather than passive, helping users stay aware of their spending in real time.
    * Highlights:
      * Two distinct warning messages are shown depending on whether the user is approaching or has already exceeded the limit
      * The exact limit amount is displayed in the warning so users know precisely what threshold they are being measured against
      * Logic is cleanly separated — `Ui` handles the display while the budget checking logic lives outside of `Ui`, following separation of concerns

* **Documentation**:
  * User Guide:
    * Added documentation for the `view` command (Pull request [#93](https://github.com/AY2526S2-CS2113-T10-4/tp/pull/93))
    * Added documentation for the `add` command (Pull request [#64](https://github.com/AY2526S2-CS2113-T10-4/tp/pull/64))
  * Developer Guide:
    * Added implementation details for the `view` command including the UML sequence diagram (Pull request [#94](https://github.com/AY2526S2-CS2113-T10-4/tp/pull/94))
    * Added implementation details for the `add` command including the UML sequence diagram (Pull request [#92](https://github.com/AY2526S2-CS2113-T10-4/tp/pull/92))
    * Added implementation details for the Storage component (Pull request[#96](https://github.com/AY2526S2-CS2113-T10-4/tp/pull/96))

* **Contributions to team-based tasks**:
  * Set up the GitHub team organisation and repository — forked the repo 
  * enabled and configured the issue tracker with custom labels, created milestones, added team members, set up the team PR 
  * configured Gradle
  * Managed releases — created the JAR file for the team demo and published the v1.0 release on GitHub
  * Maintained developer documentation not specific to a feature — wrote the Storage component section in the Developer Guide, covering the load/save operations, file format, and design considerations

* **Community**:


* **Contribution to the developer guide in more detail**:

    ## Storage Component
    The following is an excerpt from the Developer Guide for the Storage component that I wrote.

    The `Storage` class is responsible for persisting expense data and the budget limit across sessions. It reads from and writes to a local `.txt` file.

    #### Load Operation

    When the application starts, `Storage.load()` is called:

    1. If the file does not exist, an empty list is returned and a new file will be created on the next save
    2. The first line is passed to `readLimit()` which checks for the `LIMIT | <value>` format and sets the budget limit via `Limit.setLimit()`
    3. If the first line is not a limit entry, it is treated as an expense line instead
    `. Each subsequent line is passed to `processExpenseLine()` which splits by `|` and validates the format and amount before adding to the list
    5. Corrupted or malformed lines are logged and skipped without crashing the application

    #### Save Operation

    After every command, `Storage.save()` is called:

    1. The budget limit is written first in the format `LIMIT | <value>`
    2. Each expense is written in the format `amount | category | date`
    3. The file and its parent directories are created automatically if they do not exist

    #### File Format

    The `finbro.txt` file follows this structure:

    LIMIT | 1000.00

    50.00 | food | 2026-03-01

    20.00 | transport | 2026-03-02

    #### Design Considerations

    Corruption handling
    - Malformed lines are skipped rather than throwing an exception
    - Ensures a single corrupted entry does not affect the rest of the data
    - All skipped lines are logged at WARNING or SEVERE level for debugging

    Limit stored as first line
    - Separating the limit from expense entries allows it to be read and applied before any expenses are processed
    - Falls back gracefully if no limit line is found

    Flat file over a database
    - Keeps the application lightweight with no external dependencies
    - Sufficient for the scale of data this application handles

    ---

    ---

    #### View Expense Feature (Partial)

    The following is an excerpt from the Developer Guide for the `view` command that I contributed to.

    The `ViewCommand` class is responsible for handling both modes. When executed, the command checks the argument supplied:
    - If argument is `all` — all expenses are retrieved and displayed
    - If argument is a category name — only matching expenses are retrieved and displayed
    - If argument is empty — an error is thrown

    The following diagram shows the sequence of operations for the `view` command:

    ![View Expense Sequence Diagram](../UML_diagrams/images/ViewCommand.png)

    ---

    #### Add Expense Feature (Partial)

    The following is an excerpt from the Developer Guide for the `add` command that I contributed to.

    The following diagram shows the sequence of operations for the `add` command:

    ![Add Expense Sequence Diagram](../UML_diagrams/images/AddCommand.png)

---
* **Contribution to the user guide in more detail**:
    ### Add Expense Command

    The following is an excerpt from the User Guide for the `add` command that I wrote.

    The `add` command lets you record a new expense in two modes:

    **Direct Mode:**
    ```
    add <amount> <category> <date>
    ```

    **Walkthrough Mode:**
    ```
    add
    ```
    The system will guide you through entering the amount, category, date, and a final confirmation step.

    ---

    #### View Expenses

    The following is an excerpt from the User Guide for the `view` command that I wrote.

    The `view` command displays your recorded expenses.

    `view all` — displays all recorded expenses

    `view <category>` — displays expenses under a specific category

    **Notes:**
    - Categories are not case sensitive
    - Running `view` without any argument will display an error message