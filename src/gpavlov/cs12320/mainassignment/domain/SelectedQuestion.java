package gpavlov.cs12320.mainassignment.domain;

public interface SelectedQuestion<E> {

    AnswerableQuestion<E> getQuestion();

    boolean validate();

    boolean isAnswered();

    void captureAnswer(AnswerCapturer answer);

}
