package pl.pollub.api.commons.factory;

import pl.pollub.api.collaborators.model.Collaboration;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.todo.model.Todo;

public class GeneralFactory {

    public static Todo createTodo(Integer id, String content){
        return new Todo(id,content);
    }

    public static User createNewUser(Integer id, String name){
        return new User(id,name);
    }

    public static Collaboration createNewCollaboration(Integer id){
        return new Collaboration(id);
    }
}
