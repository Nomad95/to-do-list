package pl.pollub.api.collaborators.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.collaborators.model.Collaboration;
import pl.pollub.api.collaborators.model.CollaborationList;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.collaborators.model.UserList;
import pl.pollub.api.commons.factory.GeneralFactory;

import java.util.List;

@RestController
@RequestMapping(value = "/api/collabs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollaborationController {

    private final @NonNull CollaborationList collaborationList;
    private final @NonNull UserList userList;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCollaboration(){
        collaborationList.save(GeneralFactory.createNewCollaboration(collaborationList.generateId()));
    }

    @RequestMapping(value = "/{collabId}/{userId}",method = RequestMethod.POST)
    public void addUserToCollaboration(@PathVariable("collabId")Integer collabId, @PathVariable("userId") Integer userId){
        User foundUser = userList.findOne(userId);
        collaborationList.findOne(collabId).addUser(foundUser);
    }

    @RequestMapping(value = "/{collabId}",method = RequestMethod.POST)
    public void addSharedTodoToCollaboration(@PathVariable("collabId")Integer collabId, @RequestBody TextNode todo){
        Collaboration collab = collaborationList.findOne(collabId);
        collab.addSharedItem(GeneralFactory.createTodo(collab.getSharedTodos().generateId(),todo.asText()));
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Collaboration> getCollaborations(){
        return collaborationList.findAll();
    }
}
