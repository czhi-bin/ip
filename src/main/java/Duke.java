import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private enum CommandTypes {
        EXIT, ADD, LIST, DONE
    }

    private final String HORIZONTAL_LINE = "____________________________________________________________";
    private final String INDENTATION = "    ";
    private ArrayList<Task> taskList;
    private boolean isActive;

    private Duke(){
        this.taskList = new ArrayList<>();
        this.isActive = true;
    }

    private void greet(){
        String msg = "Hello! I'm Duke\n";
        msg += INDENTATION + "What can I do for you?";
        printMessageWithFormat(msg);
    }

    private void printMessageWithFormat(String msg){
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    private void exit(){
        String msg = "Bye. Hope to see you again soon!";
        printMessageWithFormat(msg);
        this.isActive = false;
    }

    private void addTask(Task task){
        this.taskList.add(task);
        String msg = "added: " + task.showDescription();
        printMessageWithFormat(msg);
    }

    private void markTaskAsDone(int taskNumber){
        taskList.get(taskNumber-1).markDone();
        String msg = "Nice! I've marked this task as done:\n   ";
        msg += INDENTATION + taskList.get(taskNumber-1).checkStatus();
        printMessageWithFormat(msg);
    }

    private void start(){
        greet();
        processCommand();
    }

    private void listTasks(){
        String msg = String.format("1. %s", taskList.get(0).checkStatus());
        for (int i = 2; i <= taskList.size(); i++){
            msg += String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i-1).checkStatus());
        }
        printMessageWithFormat(msg);
    }

    private void processCommand(){
        Scanner sc = new Scanner(System.in);

        while (this.isActive){
            String command = sc.nextLine();
            switch(getCommandType(command)){
                case EXIT:
                    exit();
                    break;

                case LIST:
                    listTasks();
                    break;

                case ADD:
                    Task task = new Task(command);
                    addTask(task);
                    break;

                case DONE:
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    markTaskAsDone(taskNumber);
                    break;
            }
        }
        sc.close();
    }

    private CommandTypes getCommandType(String command){
        String commandType = command.split(" ")[0];
        switch(commandType){
            case "list":
                return CommandTypes.LIST;

            case "bye":
                return CommandTypes.EXIT;

            case "done":
                return CommandTypes.DONE;

            default:
                return CommandTypes.ADD;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }
}
