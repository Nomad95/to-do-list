package pl.pollub.api.collaborators.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.pollub.api.commons.abstracts.AbstractIdentityObject;
import pl.pollub.api.todo.model.TodoList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractIdentityObject {
    private Integer id;
    private String name;
    private TodoList privateTodos;

    public User(Integer id,String name) {
        this.id = id;
        this.name = name;
        this.privateTodos = new TodoList();
    }
}
