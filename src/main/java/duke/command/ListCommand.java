package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a List commands that deals with listing out all the task in the task list.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class ListCommand extends DukeCommand {

    /**
     * Constructor for a List Command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public ListCommand(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the List command. Prints the list of
     * tasks currently stored in the program.
     * @return
     */
    @Override
    public String execute() {
        return ui.listTasks(list.getList());
    }
}
