package pl.pollub.api.todo.model;

import pl.pollub.api.commons.abstracts.AbstractCrud;
import pl.pollub.api.commons.factory.GeneralFactory;
import pl.pollub.api.commons.generics.GenericCrud;

import java.util.List;

/**
 * Klasa reprezentuje listę zadań
 */
public class TodoList extends AbstractCrud<Todo> implements GenericCrud<Todo> {

    public Todo addTodoFromNewTodo(NewTodo newTodo){
        return save(GeneralFactory.createTodo(generateId(),newTodo.getContent()));
    }

    /**
     * Adds todos list to existing list generating new id
     */
    public void addListOfTodos(List<Todo> todos){
        todos.forEach(todo->save(GeneralFactory.createTodo(generateId(),todo.getContent())));
    }
}
