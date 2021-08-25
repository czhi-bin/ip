import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private boolean isActive;
    private Ui ui;
    private Parser parser;

    public Duke(){
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser(taskList, storage, ui);
    }

    private void start(){
        isActive = true;
        taskList.loadFromList(storage.load());

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (isActive){
            String command = sc.nextLine();
            try {
                isActive = parser.process(command);
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
