package gpavlov.cs12320.mainassignment.persistence;

import gpavlov.cs12320.mainassignment.domain.AnswerableQuestion;
import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.QuestionBank;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ModuleFileStorageWrapper implements Module {

    private final Module wrapped;

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
        final List<QuestionBank> out = new ArrayList<>();
        wrapped.getQuestionBanks().stream().forEach(questionBank -> {
            out.add(wrapped(questionBank));
        });
        return Collections.unmodifiableList(out);
    }

    @Override
    public Optional<QuestionBank> getQuestionBank(final String qbId) {
        final Optional<QuestionBank> qWrapped = wrapped.getQuestionBank(qbId);
        if (qWrapped.isPresent()){
            return Optional.of(wrapped(qWrapped.get()));
        }
        return qWrapped;
    }

    @Override
    public QuestionBank createQuestionBank(final String qbID) {
        final QuestionBank qb = wrapped.createQuestionBank(qbID);
        save();
        return wrapped(qb);
    }

    @Override
    public Optional<QuestionBank> deleteQuestionBank(final String qbID) {
        final Optional<QuestionBank> qb = wrapped.deleteQuestionBank(qbID);
        save();
        if (qb.isPresent()){
            return Optional.of(wrapped(qb.get()));
        }
        return qb;
    }

    private QuestionBankFileStorageWrapper wrapped(final QuestionBank qWrapped) {
        return new QuestionBankFileStorageWrapper(qWrapped, this);
    }

    Module getWrapped() {
        return wrapped;
    }

    void load() {
        final Module data = getWrapped();
        // load from text file
        // load wrapped version into questionBanks
        System.out.println("===LOAD===");
    }

    void save() {
        final Module data = getWrapped();
        // load from text file
        // 00001.txt <- txt file name is mod ID
        // o1 <- question bank ID
        // A,"1+2 =",SINGLE_ANSWER,"2",true,"1",false <- question ID, text, type, and repeating option to max of 10
        // B,"time now is ____",FILL_BLANKS,"6pm" <- question ID, text, type and repeating blanks up to max of 10
        // END <- end question bank
        // o2 <- start of new qb
        // ... <- repeat
        System.out.println("===SAVE===");
    }
}
