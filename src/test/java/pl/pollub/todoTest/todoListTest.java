package pl.pollub.todoTest;

import org.junit.Test;
import pl.pollub.api.todo.list.TodoList;
import pl.pollub.api.todo.model.Todo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static pl.pollub.api.commons.factory.TodoFactory.createTodo;

public class todoListTest {

    @Test
    public void shouldAddTaskToListAndBePresent(){
        //given
        TodoList todoList = new TodoList();
        Todo todo1 = todoList.save(createTodo(1,"test1"));

        //when
        Todo foundTodo = todoList.findOne(1);

        //then
        assertTrue("Item isn't present in the list",todoList.findAll().contains(todo1));
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


}
