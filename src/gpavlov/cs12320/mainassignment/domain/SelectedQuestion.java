package gpavlov.cs12320.mainassignment.domain;

/**
 * this object is a question selected for the quiz program
 *
 */

public interface SelectedQuestion<E> {

    /**
     *
     * @return
     */

    AnswerableQuestion<E> getQuestion();

    /**
     * this method validates the student's input against the actual answer of the question
     * @return true if answer received matches answer stored in question
     */

    boolean validate();

    /**
     * this method updates that the question was answered if the student enters an answer through validate function
     * @return true if answer was provided, regardless of whether it was correct or not
     */

    boolean isAnswered();

    /**
     * this method records the answer the student provides
     * @param answer the answer the student has provided
     */

    void captureAnswer(AnswerCapturer answer);

}
