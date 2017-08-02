package pl.pollub.api.collaborators.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pollub.api.commons.abstracts.AbstractIdentityObject;
import pl.pollub.api.commons.generics.GenericCollaboration;
import pl.pollub.api.todo.model.Todo;
import pl.pollub.api.todo.model.TodoList;

import java.util.List;

/**
 * Klasa reprezentuje "współpracowników" czyli grupę osób dzielących te same zadania
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Collaboration extends AbstractIdentityObject implements GenericCollaboration<Todo>{

    private Integer id;
    private UserList colaborationUsers;
    private TodoList sharedTodos;

    public Collaboration(Integer id) {
        this.id = id;
        this.sharedTodos = new TodoList();
        this.colaborationUsers = new UserList();
    }

    @Override
    public User addUser(User user) {
        return colaborationUsers.save(user);
    }

    @Override
    @JsonIgnore
    public List<User> getAllUsers() {
        return colaborationUsers.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return colaborationUsers.findOne(id);
    }

    @Override
    public boolean removeUser(User user) {
        return colaborationUsers.remove(user);
    }

    @Override
    public Todo addSharedItem(Todo item) {
        return sharedTodos.save(item);
    }

    @Override
    @JsonIgnore
    public List<Todo> getAllSharedItems() {
        return sharedTodos.findAll();
    }

    @Override
    public Todo getSharedItemById(Integer id) {
        return sharedTodos.findOne(id);
    }

    @Override
    public boolean removeSharedItem(Todo item) {
        return sharedTodos.remove(item);
    }
}
