package gpavlov.cs12320.mainassignment.domain;

public class Quiz {

    private final int selectedQuestion;
    private final int quizLength;

    public Quiz(final int selectedQuestion, final int quizLength) {
        this.selectedQuestion = selectedQuestion;
        this.quizLength = quizLength;
    }

    public int getSelectedQuestion() {
        return selectedQuestion;
    }

    public int getQuizLength() {
        return quizLength;
    }

    public final int validateQuiz() {


        return 0;
    }

    public void generateQuiz() {

    }

}
