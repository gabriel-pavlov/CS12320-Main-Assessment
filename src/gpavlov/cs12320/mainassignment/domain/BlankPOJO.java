package gpavlov.cs12320.mainassignment.domain;

public class BlankPOJO implements Blank {
    /**
     * {@inheritDoc}
     */
    private final String answer;

    public BlankPOJO(final String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }


}
