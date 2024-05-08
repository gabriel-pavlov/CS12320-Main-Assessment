package gpavlov.cs12320.mainassignment.persistence;

import gpavlov.cs12320.mainassignment.domain.AnswerableQuestion;
import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.Question;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * the wrapper object for question banks
 */

public class QuestionFileStorageWrapper<E> implements AnswerableQuestion<E> {

    /**
     * {@inheritDoc}
     */

    private final AnswerableQuestion<E> wrapped;

    private final ModuleFileStorageWrapper module;

    public QuestionFileStorageWrapper(final AnswerableQuestion<E> wrapped, final ModuleFileStorageWrapper module) {
        this.wrapped = wrapped;
        this.module = module;
    }

    @Override
    public String getQuestionID() {
        return wrapped.getQuestionID();
    }

    @Override
    public String getQuestionText() {
        return wrapped.getQuestionText();
    }

    @Override
    public Type getType() {
        return wrapped.getType();
    }

    @Override
    public boolean validate(final String... stuAnswer) {
        return wrapped.validate(stuAnswer);
    }

    @Override
    public List<E> getAnswers() {
        return wrapped.getAnswers();
    }

    @Override
    public E addAnswer(final Map<String, Object> args) {
        final E answer = wrapped.addAnswer(args);
        module.save();
        return answer;
    }

    @Override
    public Optional<E> removeAnswer(final int index) {
        final Optional<E> answer = wrapped.removeAnswer(index);
        module.save();
        return answer;
    }
}
