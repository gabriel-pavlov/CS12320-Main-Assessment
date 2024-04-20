package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

public interface SingleAnswer extends AnswerableQuestion<Option> {

    List<Option> getAnswers();


}
