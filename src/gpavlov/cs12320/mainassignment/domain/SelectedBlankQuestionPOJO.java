package gpavlov.cs12320.mainassignment.domain;

/**
 * This is the fill in the blanks type of selected question
 */

public class SelectedBlankQuestionPOJO extends SelectedQuestionPOJO<Blank> {

    /**
     * {@inheritDoc}
     */

    private String[] answers = new String[0];

    public SelectedBlankQuestionPOJO(final AnswerableQuestion<Blank> question) {
        super(question);
    }

    @Override
    public boolean validate() {
        if (answered) {
            return getQuestion().validate(answers);
        }
        return false;
    }

    @Override
    public void captureAnswer(final AnswerCapturer answer) {
        final String[] ans = new String[getQuestion().getAnswers().size()];
        for (int i = 0; i < getQuestion().getAnswers().size(); i++) {
            ans[i] = answer.capture(getQuestion(), i);
        }
        answers = ans;
        answered = true;
    }
}
