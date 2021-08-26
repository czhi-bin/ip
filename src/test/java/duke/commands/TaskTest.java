package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void checkStatusTest(){
        Task toDo = new ToDo("sleep", false);
        assertEquals("[T][ ] sleep", toDo.checkStatus());
    }

    @Test
    public void toStringTest(){
        Task toDo = new ToDo("sleep", false);
        assertEquals("T|0|sleep", toDo.toString());
    }
}