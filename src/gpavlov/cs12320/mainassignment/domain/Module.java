package gpavlov.cs12320.mainassignment.domain;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

/**
 * This is a module object, which holds within itself all the Question banks pertaining to a specific module of a course
 */

public interface Module {

    /**
     * Unique module ID.
     *
     * @return module ID
     */
    String getModID();

    /**
     * a list of unique question banks
     *
     * @return list of question bank objects
     */
    List<QuestionBank> getQuestionBanks();

    /**
     * a method to return the information of specific question bank
     *
     * @param qbId the ID of the question bank that is required
     * @return the question bank with the corresponding ID or empty Optional if ID is invalid
     */

    Optional<QuestionBank> getQuestionBank(String qbId);

    /**
     * a method that creates a new question bank
     *
     * @param qbID the ID of the question bank to be created
     * @return a new question bank object
     */

    QuestionBank createQuestionBank(String qbID);

    /**
     * a method that deletes a question bank with the corresponding ID. The question bank must be empty for this method to be invoked.
     *
     * @param qbID the ID of the question bank to be deleted
     * @return an Optional object with a value of null if invalid ID is supplied or a reference to the deleted object
     */

    Optional<QuestionBank> deleteQuestionBank(String qbID);
}
