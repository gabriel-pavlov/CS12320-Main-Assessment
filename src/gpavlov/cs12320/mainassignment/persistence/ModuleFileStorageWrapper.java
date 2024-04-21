package gpavlov.cs12320.mainassignment.persistence;

import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.QuestionBank;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ModuleFileStorageWrapper implements Module {

    private final Module wrapped;
    private final List<QuestionBank> questionBanks = new ArrayList<>();

    public ModuleFileStorageWrapper(final Module wrapped) {
        this.wrapped = wrapped;
        this.load();
    }

    @Override
    public String getModID() {
        return wrapped.getModID();
    }

    @Override
    public List<QuestionBank> getQuestionBanks() {
        return Collections.unmodifiableList(questionBanks);
    }

    @Override
    public Optional<QuestionBank> getQuestionBank(final String qbId) {
        return wrapped.getQuestionBank(qbId);
    }

    @Override
    public QuestionBank createQuestionBank(final String qbID) {
        final QuestionBank qb = wrapped.createQuestionBank(qbID);
        save();
        return qb;
    }

    @Override
    public Optional<QuestionBank> deleteQuestionBank(final String qbID) {
        final Optional<QuestionBank> qb = wrapped.deleteQuestionBank(qbID);
        save();
        return qb;
    }

    Module getWrapped() {
        return wrapped;
    }

    void load() {
        final Module data = getWrapped();
        // load from text file
        // load wrapped version into questionBanks
    }

    void save() {
        final Module data = getWrapped();
        // load from text file
    }
}
