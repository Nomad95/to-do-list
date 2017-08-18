package pl.pollub.task2;

import lombok.Data;

import java.util.List;

@Data
public class Task {

    private final int id;

    private final int userId;

    private final List<Integer> contributors;

}
