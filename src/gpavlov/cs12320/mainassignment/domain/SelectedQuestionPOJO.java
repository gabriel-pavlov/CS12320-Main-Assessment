package gpavlov.cs12320.mainassignment.domain;

public abstract class SelectedQuestionPOJO<E> implements SelectedQuestion<E> {

    private final AnswerableQuestion<E> question;

    protected boolean answered = false;

    public SelectedQuestionPOJO(final AnswerableQuestion<E> question) {
        this.question = question;
    }

    @Override
    public AnswerableQuestion<E> getQuestion() {
        return question;
    }

    @Override
    public boolean isAnswered() {
        return answered;
    }



}
