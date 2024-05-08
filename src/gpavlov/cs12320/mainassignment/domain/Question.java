package gpavlov.cs12320.mainassignment.domain;

/**
 * this is a question, which is an object that holds the
 * information about a specific question that can be chosen for a quiz
 */

public interface Question {

    /**
     * type options
     */

    enum Type {SINGLE_CHOICE, FILL_BLANKS}

    /**
     * method that returns question ID of given question
     * @return Question ID
     */

    String getQuestionID();

    /**
     * method that returns question text of given question
     * @return Question text
     */

    String getQuestionText();

    /**
     * method that returns question type of given question
     * @return Question type according to enum
     */

    Type getType();

    /**
     * method that validates whether a student input is
     * @param stuAnswer a string array containing the student's answer(s)
     * @return boolean true if student answered correctly, false otherwise
     */

    boolean validate(String ... stuAnswer);

}
