package pl.pollub.todoTest;

import org.junit.Test;
import pl.pollub.api.todo.model.Todo;
import pl.pollub.api.todo.model.TodoList;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static pl.pollub.api.commons.factory.GeneralFactory.createTodo;

public class TodoListTest {

    @Test
    public void shouldAddTaskToListAndBePresent(){
        //given
        TodoList todoList = new TodoList();
        Todo todo1 = todoList.save(createTodo(1,"test1"));

        //when
        Todo foundTodo = todoList.findOne(1);

        //then
        assertTrue("Item isn't present in the list",todoList.findAll().contains(todo1));
        assertEquals("Items aren't same",todo1,foundTodo);
    }

    @Test
    public void shouldUpdateListItemWithSameId(){
        //given
        TodoList todoList = new TodoList();
        Todo todo1 = todoList.save(createTodo(1,"test1"));

        //when
        Todo todo2 = todoList.save(createTodo(1,"test2"));
        Todo foundTodo = todoList.findOne(1);

        //then
        assertThat("Item wasn't updated",foundTodo, is(equalTo(todo2)));
    }

    @Test
    public void removedItemShouldNotBeOnTheList(){
        //given
        TodoList todoList = new TodoList();
        Todo todo1 = todoList.save(createTodo(1,"test1"));
        Todo todo2 = todoList.save(createTodo(2,"test2"));

        //when
        todoList.remove(todo1);

        //then
        assertFalse("Item is still present",todoList.findAll().contains(todo1));
    }

    @Test
    public void shouldAddListOfItemsToCurrentListWithNewId(){
        //given
        TodoList todoList = new TodoList();
        Todo todo1 = todoList.save(createTodo(1,"test1"));
        Todo todo2 = todoList.save(createTodo(2,"test2"));
        Todo todo3 = createTodo(1, "test3");
        Todo todo4 = createTodo(2, "test4");
        List<Todo> newTodos = Arrays.asList(todo3,todo4);

        //when
        todoList.addListOfTodos(newTodos);

        //then
        assertEquals(4,todoList.getElements().size());
        assertNotEquals(todo3.getId(),todoList.getElements().get(2).getId());
    }


}
