package pl.pollub.todoTest;

import org.junit.Before;
import org.junit.Test;
import pl.pollub.api.todo.model.Todo;
import pl.pollub.api.todo.model.TodoList;
import pl.pollub.exception.exceptions.ForbiddenTodoOperationException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static pl.pollub.api.commons.factory.GeneralFactory.createNewUser;
import static pl.pollub.api.commons.factory.GeneralFactory.createTodo;

public class TodoListTest {

    private TodoList todoList;

    @Before
    public void before(){
        todoList = new TodoList();
    }

    @Test
    public void shouldAddTaskToListAndBePresent(){
        //given
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
        Todo todo1 = todoList.save(createTodo(todoList.generateId(),"test1"));
        Todo todo2 = todoList.save(createTodo(todoList.generateId(),"test2"));
        //specify todos with random ids
        Todo todo3 = createTodo(1, "test3");
        Todo todo4 = createTodo(2, "test4");
        List<Todo> newTodos = Arrays.asList(todo3,todo4);

        //when
        todoList.addListOfTodos(newTodos);//should generate new id for new todos

        //then
        assertEquals(4,todoList.getElements().size());
        assertNotEquals(todo3.getId(),todoList.getElements().get(2).getId());
    }

    //should not throw outOfBound Exception
    @Test
    public void shouldReturnNullWhenEntityWithSpecifiedIdWasntFound(){
        //given -> before

        //when
        Todo foundElement = todoList.findOne(100);

        //then
        assertEquals("Item with specified id was somehow found",foundElement,null);
    }

    @Test(expected = ForbiddenTodoOperationException.class)
    public void shouldThrowExceptionTryingToFinishFinishedTodo(){
        //given
        todoList.save(createTodo(1,"test1"));
        todoList.makeTodoAccomplished(1,createNewUser(1,"user1"));

        //when
        todoList.makeTodoAccomplished(1,createNewUser(2,"user2"));

        //then exception
    }

    @Test
    public void shouldReturnOnlyUnfinishedTodos(){
        //given
        Todo todo1 = todoList.save(createTodo(1, "test1"));
        todoList.save(createTodo(2, "test2"));
        todoList.save(createTodo(3, "test3"));
        Todo todo4 = todoList.save(createTodo(4, "test4"));
        Todo todo2 = todoList.makeTodoAccomplished(2, createNewUser(2, "user1"));
        Todo todo3 = todoList.makeTodoAccomplished(3, createNewUser(1, "user1"));

        //when
        List<Todo> unfinishedTodos = todoList.getUnfinishedTodos();

        //then
        assertThat(unfinishedTodos,not(hasItems(todo2,todo3)));
    }

}
