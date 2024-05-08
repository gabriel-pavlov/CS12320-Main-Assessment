package gpavlov.cs12320.mainassignment.domain;

import java.util.*;

public class FillBlanksPOJO extends QuestionPOJO implements FillBlanks {

    /**
     * {@inheritDoc}
     */

    private final List<Blank> answers = new ArrayList<>();

    public FillBlanksPOJO(final String questionID, final String questionText) {
        super(questionID, questionText);
    }

    @Override
    public Type getType() {
        return Type.FILL_BLANKS;
    }

    @Override
    public List<Blank> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    /**
     * Validate function to check that student answer is matching blanks for each index.
     * For this function to return true all blanks must be correct
     *
     * @param stuAnswer answers provided by student for this question
     * @return true if answers are correct
     */
    @Override
    public boolean validate(final String... stuAnswer) {

        for (int i = 0; i < stuAnswer.length; i++) {
            Blank correctAnswer = answers.get(i);
            if (!stuAnswer[i].equals(correctAnswer.getAnswer())) {
                System.out.println("DEBUG: " + getQuestionID() + ":" + getQuestionText() + ":" + Arrays.asList(stuAnswer) + ": INCORRECT");
                return false;
            }

        }

        System.out.println("DEBUG: " + getQuestionID() + ":" + getQuestionText() + ":" + Arrays.asList(stuAnswer) + ": CORRECT");
        return true;
    }

    @Override
    public Blank addAnswer(final Map<String, Object> args) {

        final String blank = (String) args.get("blank");
        if (blank == null || blank.isEmpty()) {
            throw new IllegalArgumentException("Blank is not provided");
        }
        final Blank bl = new BlankPOJO(blank);
        answers.add(bl);
        return bl;
    }

    @Override
    public Optional<Blank> removeAnswer(final int index) {
        if (index >= 0 && index < answers.size()) {
            return Optional.of(answers.remove(index));
        }

        return Optional.empty();
    }
}
