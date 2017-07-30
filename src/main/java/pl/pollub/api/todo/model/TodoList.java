package pl.pollub.api.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.commons.abstracts.AbstractCrud;
import pl.pollub.api.commons.factory.GeneralFactory;
import pl.pollub.api.commons.generics.GenericCrud;
import pl.pollub.exception.exceptions.ForbiddenTodoOperationException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public Todo makeTodoAccomplished(Integer id, User user){
        Todo foundTodo = findOne(id);
        if(foundTodo.isFinished())
            throw new ForbiddenTodoOperationException("Cannot finish already finished todo");
        foundTodo.setFinished(true);
        foundTodo.setFinishedBy(user.getName());
        foundTodo.setFinishedDate(new Date());
        return save(foundTodo);
    }

    @JsonIgnore
    public List<Todo> getUnfinishedTodos(){
        return getElements().stream().filter(todo -> !todo.isFinished()).collect(Collectors.toList());
    }
}
