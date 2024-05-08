package gpavlov.cs12320.mainassignment.domain;

/**
 * This is the single answer type of selected question
 */

public class SelectedSingleQuestionPOJO extends SelectedQuestionPOJO<Option> {

    /**
     * {@inheritDoc}
     */

    private int answerIndex = 0;

    public SelectedSingleQuestionPOJO(final AnswerableQuestion<Option> question) {
        super(question);
    }

    @Override
    public boolean validate() {
        if (answered) {
            final Option answerOption = getQuestion().getAnswers().get(answerIndex);
            return getQuestion().validate(answerOption.getAnswer());
        }
        return false;
    }

    @Override
    public void captureAnswer(final AnswerCapturer answer) {
        final String ans = answer.capture(getQuestion(), 0);
        answerIndex = Integer.parseInt(ans) - 1;
        answered = true;
    }
}
