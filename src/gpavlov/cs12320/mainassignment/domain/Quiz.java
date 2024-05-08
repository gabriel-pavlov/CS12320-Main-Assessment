package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

/**
 * This is a quiz, an object that allows a student user to take quick quiz and test their knowledge
 */

public interface Quiz {

    /**
     * This method returns the list of questions the quiz contains
     * @return the list of selected questions made during quiz generation
     */

    List<AnswerableQuestion> getQuestions();

    /**
     * this method returns the amount of questions left unanswered when the student selects to end the quiz
     * @return the amount of questions without an answer as int
     */

    int getUnansweredQuestionCount();

    /**
     * this method checks the student's answers against the actual answers and calculates their overall score
     * @return score of student
     */

    float validate();

    /**
     * this method captures and records the answers that the student provides
     *
     * @param index the index of the question being answered
     * @param answer the answer recorded by the program
     */

    void captureAnswer(int index ,AnswerCapturer answer);

}
