package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

/**
 * this is the Single answer version of the question object
 */

public interface SingleAnswer extends AnswerableQuestion<Option> {

    /**
     * this method shows the list of options that can be chosen to answer the question
     * @return list of all possible option objects for this question
     */

    List<Option> getAnswers();


}
