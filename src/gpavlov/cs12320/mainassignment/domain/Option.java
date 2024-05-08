package gpavlov.cs12320.mainassignment.domain;

/**
 * this is an option, the object used to represent the answers of single answer questions
 */

public interface Option {
    /**
     * this method returns an option's answer
     * @return the option text as string
     */

    String getAnswer();

    /**
     *
     * @return true if correct answer, false if wrong choice
     */

    boolean isCorrect();
}
