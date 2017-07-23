package pl.pollub.api.commons.generics;

import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.commons.abstracts.AbstractIdentityObject;

import java.util.List;

public interface GenericCollaboration<T extends AbstractIdentityObject> {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    boolean removeUser(User user);

    T addSharedItem(T item);

    List<T> getAllSharedItems();

    T getSharedItemById(Integer id);

    boolean removeSharedItem(T item);
}
