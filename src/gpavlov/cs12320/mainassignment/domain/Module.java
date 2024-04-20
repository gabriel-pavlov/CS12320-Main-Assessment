package gpavlov.cs12320.mainassignment.domain;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public interface Module {

    /**
     * Unique module ID.
     *
     * @return module ID
     */
    String getModID();

    List<QuestionBank> getQuestionBanks();

    Optional<QuestionBank> getQuestionBank(String qbId);

    QuestionBank createQuestionBank(String qbID);

    Optional<QuestionBank> deleteQuestionBank(String qbID);
}
