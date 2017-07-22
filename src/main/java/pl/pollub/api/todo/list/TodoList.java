package pl.pollub.api.todo.list;

import org.springframework.stereotype.Component;
import pl.pollub.api.commons.abstracts.AbstractCrud;
import pl.pollub.api.commons.factory.TodoFactory;
import pl.pollub.api.commons.generics.GenericCrud;
import pl.pollub.api.todo.model.NewTodo;
import pl.pollub.api.todo.model.Todo;

@Component
public class TodoList extends AbstractCrud<Todo> implements GenericCrud<Todo> {

    public Todo addTodoFromNewTodo(NewTodo newTodo){
        return save(TodoFactory.createTodo(generateId(),newTodo.getContent()));
    }
}
