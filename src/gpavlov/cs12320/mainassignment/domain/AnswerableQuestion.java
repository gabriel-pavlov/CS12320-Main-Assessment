package gpavlov.cs12320.mainassignment.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnswerableQuestion<E> extends Question {

    List<E> getAnswers();

    E addAnswer(Map<String, Object> args);

    Optional<E> removeAnswer(int index);

}
