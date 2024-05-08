package gpavlov.cs12320.mainassignment.domain;

import java.util.*;

public class SingleAnswerPOJO extends QuestionPOJO implements SingleAnswer {

    /**
     * {@inheritDoc}
     */

    private final List<Option> answers = new ArrayList<>();


    public SingleAnswerPOJO(final String questionID, final String questionText) {
        super(questionID, questionText);
    }

    @Override
    public Type getType() {
        return Type.SINGLE_CHOICE;
    }

    public List<Option> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    @Override
    public boolean validate(final String... stuAnswer) {

        for (int i = 0; i < stuAnswer.length; i++) {
            Option correctAnswer = answers.get(i);
            if (stuAnswer[i].equals(correctAnswer.getAnswer())) {
                if (correctAnswer.isCorrect()) {
                    System.out.println("DEBUG: " + getQuestionID() + ":" + getQuestionText() + ":" + Arrays.asList(stuAnswer) + ": CORRECT");
                    return true;
                }
            }
        }
        System.out.println("DEBUG: " + getQuestionID() + ":" + getQuestionText() + ":" + Arrays.asList(stuAnswer) + ": INCORRECT");
        return false;
    }

    @Override
    public Option addAnswer(final Map<String, Object> args) {
        final String option = (String) args.get("option");
        if (option == null || option.isEmpty()) {
            throw new IllegalArgumentException("Option is not provided");
        }
        final Boolean isCorrect = (Boolean) args.get("isCorrect");
        if (isCorrect == null) {
            throw new IllegalArgumentException("isCorrect is not provided");
        }
        final Option opt = new OptionPOJO(option, isCorrect);
        answers.add(opt);
        return opt;
    }

    @Override
    public Optional<Option> removeAnswer(final int index) {
        if (index >= 0 && index < answers.size()) {
            return Optional.of(answers.remove(index));
        }

        return Optional.empty();
    }

}
