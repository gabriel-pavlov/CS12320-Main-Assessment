package gpavlov.cs12320.mainassignment.domain;

import java.util.List;
import java.util.Optional;

public interface QuestionBank {

    String getBankID();

    String getModID();

    String getUniqueID();

    List<Question> getQuestions();

    Optional<Question> getQuestion(String id);

    Quiz createQuiz(int numberOfQuestions);

    Question addQuestion(String id, String questionText, Question.Type type);

    Optional<Question> removeQuestion(int index);

    boolean hasQuestions();
}
