package gpavlov.cs12320.mainassignment.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionBankPOJO implements QuestionBank {

    private final String bankID;
    private final List<Question> questions = new ArrayList<>();
    private final Module module;

    public QuestionBankPOJO(final Module module, final String bankID) {
        this.bankID = bankID;
        this.module = module;
    }

    public String getBankID() {
        return bankID;
    }

    @Override
    public String getModID() {
        return module.getModID();
    }

    @Override
    public String getUniqueID() {
        return getModID().concat(":").concat(getBankID());
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Optional<Question> getQuestion(final String id) {
        return questions.stream()
                .filter(q -> q.getQuestionID().equals(id)).findFirst();
    }

    @Override
    public Quiz createQuiz(final int numberOfQuestions) {
        return null;
        //TODO create quiz (randomiser and question amount)
    }

    public Question addQuestion(final String id, final String questionText, final Question.Type type) {


        final Optional<Question> q = getQuestion(id);
        if (q.isPresent()) {
            throw new IllegalArgumentException("Duplicate question ID, question already present "
                    + id);
        }

        final Question question;

        switch (type) {
            case SINGLE_CHOICE :
                question = new SingleAnswerPOJO(id, questionText);
                break;
            case FILL_BLANKS :
            default:
                question = new FillBlanksPOJO(id, questionText);
                break;

        }

        questions.add(question);
        return question;
    }

    public Optional<Question> removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return Optional.of(questions.remove(index));
        }

        return Optional.empty();

    }


    public boolean hasQuestions() {
        return !questions.isEmpty();
    }
}
