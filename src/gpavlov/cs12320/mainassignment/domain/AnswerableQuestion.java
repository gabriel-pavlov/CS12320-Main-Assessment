package gpavlov.cs12320.mainassignment.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * an interface that allows questions to be answered by students during the quiz
 * @param <E> the type of question being answered.
 */

public interface AnswerableQuestion<E> extends Question {

    /**
     *
     * @return list of answer objects (blank or options) for the question
     */

    List<E> getAnswers();

    /**
     * this method allows a teacher to add a new answer for a given question
     * @param args the answer contents (and if option whether it is correct or not)
     * @return a new answer object (blank or option)
     */

    E addAnswer(Map<String, Object> args);

    /**
     * this method allows a teacher to remove an existing answer for a given question
     * @param index the index of the answer in the question's answer list
     * @return an empty optional if index invalid or reference to removed answer object
     */

    Optional<E> removeAnswer(int index);

}
