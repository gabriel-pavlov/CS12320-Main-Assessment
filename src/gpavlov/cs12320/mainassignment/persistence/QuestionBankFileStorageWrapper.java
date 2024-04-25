package gpavlov.cs12320.mainassignment.persistence;

import gpavlov.cs12320.mainassignment.domain.AnswerableQuestion;
import gpavlov.cs12320.mainassignment.domain.Question;
import gpavlov.cs12320.mainassignment.domain.QuestionBank;
import gpavlov.cs12320.mainassignment.domain.QuizPOJO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionBankFileStorageWrapper implements QuestionBank {

    private final QuestionBank wrapped;

    private  final ModuleFileStorageWrapper module;

    public QuestionBankFileStorageWrapper(final QuestionBank wrapped, final ModuleFileStorageWrapper module) {
        this.wrapped = wrapped;
        this.module = module;
    }

    @Override
    public String getBankID() {
        return wrapped.getBankID();
    }

    @Override
    public String getModID() {
        return wrapped.getModID();
    }

    @Override
    public String getUniqueID() {
        return wrapped.getUniqueID();
    }

    @Override
    public List<Question> getQuestions() {
        final List<Question> out = new ArrayList<>();
        wrapped.getQuestions().stream().forEach(question -> {
            out.add(wrapped((AnswerableQuestion) question));
        });
        return Collections.unmodifiableList(out);
    }

    @Override
    public Optional<Question> getQuestion(final String id) {
        final Optional<Question>qWrapped = wrapped.getQuestion(id);
        if (qWrapped.isPresent()){
            return Optional.of(wrapped((AnswerableQuestion) qWrapped.get()));
        }
        return qWrapped;
    }

    @Override
    public QuizPOJO createQuiz(final int numberOfQuestions) {
        return wrapped.createQuiz(numberOfQuestions);
    }

    @Override
    public Question addQuestion(final String id, final String questionText, final Question.Type type) {
        final Question qWrapped = wrapped.addQuestion(id, questionText, type);
        module.save();
        return wrapped((AnswerableQuestion) qWrapped);
    }

    private QuestionFileStorageWrapper wrapped(final AnswerableQuestion qWrapped) {
        return new QuestionFileStorageWrapper(qWrapped, module);
    }

    @Override
    public Optional<Question> removeQuestion(final int index) {
        final Optional<Question> qWrapped = wrapped.removeQuestion(index);
        module.save();
        if (qWrapped.isPresent()){
            return Optional.of(wrapped((AnswerableQuestion) qWrapped.get()));
        }
        return qWrapped;
    }

    @Override
    public boolean hasQuestions() {
        return wrapped.hasQuestions();
    }
}
