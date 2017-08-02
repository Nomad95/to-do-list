package pl.pollub.api.collaborators.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.collaborators.model.Collaboration;
import pl.pollub.api.collaborators.model.CollaborationList;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.collaborators.model.UserList;
import pl.pollub.api.commons.factory.GeneralFactory;
import pl.pollub.api.todo.model.Todo;

import java.util.List;

@RestController
@RequestMapping(value = "/api/collabs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollaborationController {

    private final @NonNull CollaborationList collaborationList;
    private final @NonNull UserList userList;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCollaboration(){
        collaborationList.save(GeneralFactory.createNewCollaboration(collaborationList.generateId()));
    }

    @PostMapping(value = "/{collabId}/{userId}")
    public void addUserToCollaboration(@PathVariable("collabId")Integer collabId, @PathVariable("userId") Integer userId){
        User foundUser = userList.findOne(userId);
        collaborationList.findOne(collabId).addUser(foundUser);
    }

    @PostMapping(value = "/{collabId}")
    public void addSharedTodoToCollaboration(@PathVariable("collabId")Integer collabId, @RequestBody TextNode todo){
        Collaboration collab = collaborationList.findOne(collabId);
        collab.addSharedItem(GeneralFactory.createTodo(collab.getSharedTodos().generateId(),todo.asText()));
    }

    @GetMapping
    public @ResponseBody List<Collaboration> getCollaborations(){
        return collaborationList.findAll();
    }

    /**
     * @param userId id of removed user
     * @param collabId id of associeted collab
     */
    @DeleteMapping(value = "/removeUser/{collabId}/{userId}")
    public void removeUserFromCollaboration(
            @PathVariable("userId") Integer userId,
            @PathVariable("collabId") Integer collabId){
        Collaboration collab = collaborationList.findOne(collabId);
        collab.getColaborationUsers().removeById(userId);
    }

    /**
     * @param todoId id of to-do we want to remove
     * @param collabId id of collaboration
     */
    @DeleteMapping(value = "/removeTodo/{collabId}/{todoId}")
    public void removeSharedTodoFromCollaboration(
            @PathVariable("todoId") Integer todoId,
            @PathVariable("collabId") Integer collabId){
        Collaboration collab = collaborationList.findOne(collabId);
        collab.getSharedTodos().removeById(todoId);
    }

    //czy taka ścieżka nie jest zbyt skomplikowana?
    //istnieje lepszy sposób?

    /**
     * Sets to-do to accomplished by provided user
     * @param todoId id of to-do object
     * @param collabId collacoration id
     * @param userId user which accomplished this to-do
     * @return to-do set to accomplished
     */
    @PostMapping(value = "/accomplish/{collabId}/{userId}/{todoId}")
    public @ResponseBody Todo setSharedTodoAccomplished(
            @PathVariable("todoId") Integer todoId,
            @PathVariable("collabId") Integer collabId,
            @PathVariable("userId") Integer userId){
        User user = userList.findOne(userId);
        return collaborationList.findOne(collabId).getSharedTodos().makeTodoAccomplished(todoId,user);
    }
}
