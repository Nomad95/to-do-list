package pl.pollub.api.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.pollub.api.commons.abstracts.AbstractIdentityObject;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends AbstractIdentityObject {

    private final Integer id;
    private String content;
    private String finishedBy;
    private Date finishedDate;
    private boolean finished;

    public Todo(Integer id, String content) {
        this.id = id;
        this.content = content;
        this.finished = false;
    }
}
