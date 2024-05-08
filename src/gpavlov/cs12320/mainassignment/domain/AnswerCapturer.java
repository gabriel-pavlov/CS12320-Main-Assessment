package gpavlov.cs12320.mainassignment.domain;

/**
 * this is the answer capture interface. Its purpose is to record the answers the student gives for the quiz
 */

public interface AnswerCapturer {

    /**
     * this method records the student's answers
     *
     * @param aq the question to be answered
     * @param answerIndex which portion of the answer the student has written for. mainly used for blank questions
     * @return the answer the student has entered for that portion of the question
     */

    String capture(AnswerableQuestion aq, int answerIndex);

}
