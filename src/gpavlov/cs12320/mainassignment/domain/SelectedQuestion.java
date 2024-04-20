package gpavlov.cs12320.mainassignment.domain;

public class SelectedQuestion {

    private final String questionID;
    private final String studans;

    public SelectedQuestion(final String questionID, final String studans) {
        this.questionID = questionID;
        this.studans = studans;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getStudans() {
        return studans;
    }
}
