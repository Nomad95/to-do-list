package pl.pollub.api.collaborators.model;

import org.springframework.stereotype.Component;
import pl.pollub.api.commons.abstracts.AbstractCrud;
import pl.pollub.api.commons.generics.GenericCrud;

@Component
public class UserList extends AbstractCrud<User> implements GenericCrud<User>{
}
