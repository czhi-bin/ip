package duke;

import java.util.Scanner;

import duke.command.DukeCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulate a bot which helps user to keep track of a list of task.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser(taskList, storage, ui);
    }

    private void start() {
        boolean isActive = true;
        taskList.loadFromList(storage.load());

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String input = sc.nextLine();
            try {
                DukeCommand command = parser.processInput(input);
                command.execute();
                isActive = !command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Starts the program.
     *
     * @param args Arguments needed to start to program
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
