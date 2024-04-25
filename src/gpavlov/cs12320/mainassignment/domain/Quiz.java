package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

public interface Quiz {

    List<AnswerableQuestion> getQuestions();

    int getUnansweredQuestionCount();

    float validate();

    void captureAnswer(int index ,AnswerCapturer answer);

}
