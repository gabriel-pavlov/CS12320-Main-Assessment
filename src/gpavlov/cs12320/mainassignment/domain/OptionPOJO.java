package gpavlov.cs12320.mainassignment.domain;

public class OptionPOJO implements Option {

    private final String answer;
    private final boolean isCorrect;

    public OptionPOJO(final String answer, final boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean isCorrect() {
        return isCorrect;
    }
}
