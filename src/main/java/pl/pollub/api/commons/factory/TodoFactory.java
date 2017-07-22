package pl.pollub.api.commons.factory;

import pl.pollub.api.todo.model.Todo;

public class TodoFactory {

    public static Todo createTodo(Integer id, String content){
        return new Todo(id,content);
    }
}
