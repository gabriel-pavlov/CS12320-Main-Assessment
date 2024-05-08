package gpavlov.cs12320.mainassignment.domain;

import java.util.List;
import java.util.Optional;

/**
 * This is a Question bank, which holds a list of questions that can be turned into a quiz
 */

public interface QuestionBank {

    /**
     * gets the ID of the bank
     * @return the Bank ID
     */

    String getBankID();

    /**
     * gets the ID of the question bank's parent module
     * @return Mod ID
     */

    String getModID();

    /**
     * gets the unique identifier of the Question Bank, which is a composite key of the parent mod ID and the bank ID
     * @return Unique ID (mod ID:bank ID)
     */

    String getUniqueID();

    /**
     * gets a list of the questions the question bank contains
     * @return List of question objects
     */

    List<Question> getQuestions();

    /**
     * searches for a specific question by its question ID
     *
     * @param id The question ID of question to be found
     * @return The question being searched for
     */

    Optional<Question> getQuestion(String id);

    /**
     * creates a new Quiz for the student to take. The student provides how many questions they wish to answer
     *
     * @param numberOfQuestions the amount of questions the user has asked the quiz to be.
     *                          If it is larger than list of questions, it will be changed
     *                          to match the size of the Question banks list of questions
     * @return a new quiz object to be used in
     */

    QuizPOJO createQuiz(int numberOfQuestions);

    /**
     * a method that allows a teacher user to add new questions to the Questio bank
     *
     * @param id question ID
     * @param questionText the question as text
     * @param type the type of question
     * @return new question object to be added to list of questions
     */

    Question addQuestion(String id, String questionText, Question.Type type);

    /**
     * a method that allows a teacher user to remove a chosen question
     *
     * @param index the index of the question to be deleted within the Question bank's list of questions
     * @return An Optional object containg a reference to the deleted question
     */

    Optional<Question> removeQuestion(int index);

    /**
     * a method that checks if a bank has any questions left in it.
     * @return Boolean value of true if list of questions not empty
     */

    boolean hasQuestions();
}
