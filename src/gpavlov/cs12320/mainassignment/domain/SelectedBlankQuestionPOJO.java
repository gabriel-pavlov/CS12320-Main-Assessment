package gpavlov.cs12320.mainassignment.domain;

public class SelectedBlankQuestionPOJO extends SelectedQuestionPOJO<Blank> {

    private String[] answers = new String[0];

    public SelectedBlankQuestionPOJO(final AnswerableQuestion<Blank> question) {
        super(question);
    }

    @Override
    public boolean validate() {
        return getQuestion().validate(answers);
    }

    @Override
    public void captureAnswer(final AnswerCapturer answer) {
        final String[] ans = new String[getQuestion().getAnswers().size()];
        for (int i = 0; i < getQuestion().getAnswers().size(); i++) {
            ans[i] = answer.capture(getQuestion());
        }
        answers = ans;
        answered = true;
    }
}
