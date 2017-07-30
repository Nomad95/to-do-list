package pl.pollub.api.collaborators.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.collaborators.model.UserList;
import pl.pollub.exception.exceptions.ElementNotFoundException;

import java.util.List;

import static pl.pollub.api.commons.factory.GeneralFactory.createNewUser;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final @NonNull UserList userList;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addNewUser(@RequestBody TextNode name){
        return userList.save(createNewUser(userList.generateId(),name.asText()));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable("userId") Integer userId){
        return userList.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(){
        return userList.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody  User updateUserInfo(@RequestBody User user){
        User foundUser = userList.findOne(user.getId());
        if(foundUser != null)
            return userList.save(user);
        else throw new ElementNotFoundException();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable("userId") Integer userId){
        userList.removeById(userId);
    }

}
