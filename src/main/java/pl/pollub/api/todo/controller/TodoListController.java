package pl.pollub.api.todo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoListController {

   /* private final @NonNull AllTodoLists allTodoLists;

    @Autowired
    public TodoListController(AllTodoLists allTodoLists) {
        this.allTodoLists = allTodoLists;
    }

    *//**
     * Adds new Todo to list with provided id
     *//*
    @RequestMapping(value = "/{todoListId}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addTodoToList(@RequestBody NewTodo todo, @PathVariable("todoListId")Integer todoListId){
        TodoList foundList = allTodoLists.findOne(todoListId);
        foundList.addTodoFromNewTodo(todo);
    }

    *//**
     * Get all todos from list with provided id
     *//*
    @RequestMapping(value = "/{todoListId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getTodosFromList(@PathVariable("todoListId")Integer todoListId){
        TodoList foundList = allTodoLists.findOne(todoListId);
        return foundList.findAll();
    }

    *//**
     * Remove item with todoId from list with todoListId
     *//*
    @RequestMapping(value = "/{todoListId}/{todoId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Boolean> removeTodoById(@PathVariable("todoId")Integer todoId,@PathVariable("todoListId")Integer todoListId){
        TodoList foundList = allTodoLists.findOne(todoListId);
        boolean wasRemoved = foundList.removeById(todoId);
        return wasRemoved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

}
