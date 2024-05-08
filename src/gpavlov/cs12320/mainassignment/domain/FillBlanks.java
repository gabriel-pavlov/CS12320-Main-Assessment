package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

/**
 * this is the fill in blanks version of the question object
 */

public interface FillBlanks extends AnswerableQuestion<Blank> {

    /**
     * this method returns the list of blanks that must be entered to answer the question
     * @return list of all possible blank objects for this question
     */

    List<Blank> getAnswers();


}
