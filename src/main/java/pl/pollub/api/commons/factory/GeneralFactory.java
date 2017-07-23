package pl.pollub.api.commons.factory;

import pl.pollub.api.collaborators.model.Collaboration;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.todo.model.Todo;

public class GeneralFactory {

    public static Todo createTodo(Integer id, String content){
        return new Todo(id,content);
    }

    static public User createNewUser(Integer id, String name){
        return new User(id,name);
    }

    static public Collaboration createNewCollaboration(Integer id){
        return new Collaboration(id);
    }
}
