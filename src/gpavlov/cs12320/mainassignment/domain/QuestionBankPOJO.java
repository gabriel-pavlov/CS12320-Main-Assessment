package gpavlov.cs12320.mainassignment.domain;

import java.util.*;

/**
 * {@inheritDoc}
 */

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
    public QuizPOJO createQuiz(final int numberOfQuestions) {
        if (numberOfQuestions < 0){
            throw new IllegalArgumentException("Please enter a non-negative amount of questions to answer");
        }
        final List<AnswerableQuestion> quizList = new ArrayList<>((List) questions);
        final Random random = new Random();
        while (numberOfQuestions < quizList.size()) {
            quizList.remove(random.nextInt(quizList.size()));
        }
        Collections.shuffle(quizList);
        return new QuizPOJO(quizList);

    }

    public Question addQuestion(final String id, final String questionText, final Question.Type type) {

        final Optional<Question> q = getQuestion(id);
        if (q.isPresent()) {
            throw new IllegalArgumentException("Duplicate question ID, question already present "
                    + id);
        }

        final Question question;

        switch (type) {
            case SINGLE_CHOICE:
                question = new SingleAnswerPOJO(id, questionText);
                break;
            case FILL_BLANKS:
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
