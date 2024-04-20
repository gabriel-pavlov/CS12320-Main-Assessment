package gpavlov.cs12320.mainassignment.domain;

import java.util.List;

public interface FillBlanks extends AnswerableQuestion<Blank> {

    List<Blank> getAnswers();


}
