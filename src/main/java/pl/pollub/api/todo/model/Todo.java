package pl.pollub.api.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.pollub.api.commons.abstracts.AbstractListObject;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends AbstractListObject{

    private final Integer id;
    private final String content;
}
