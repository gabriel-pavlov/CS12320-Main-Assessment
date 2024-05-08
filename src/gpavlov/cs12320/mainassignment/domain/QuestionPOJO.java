package gpavlov.cs12320.mainassignment.domain;

import java.util.Objects;

public abstract class QuestionPOJO implements Question{
    /**
     * {@inheritDoc}
     */
    private final String questionID;
    private final String questionText;

    public QuestionPOJO(final String questionID, final String questionText) {
        this.questionID = questionID;
        this.questionText = questionText;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getQuestionText() {
        return questionText;
    }



}
