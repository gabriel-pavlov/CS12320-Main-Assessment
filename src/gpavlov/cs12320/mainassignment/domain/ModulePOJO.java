package gpavlov.cs12320.mainassignment.domain;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ModulePOJO implements Module {

    private final String modID;
    private final List<QuestionBank> questionBanks = new ArrayList<>();

    public ModulePOJO(final String modID) {
        this.modID = modID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModID() {
        return modID;
    }

    @Override
    public List<QuestionBank> getQuestionBanks() {
        return Collections.unmodifiableList(questionBanks);
    }

    @Override
    public Optional<QuestionBank> getQuestionBank(final String qbId) {
        return questionBanks.stream()
                .filter(qb -> qb.getBankID().equals(qbId)).findFirst();
    }

    public void listQuestionBank(PrintStream os) {

        for(final QuestionBank qb : questionBanks) {
            os.println(qb.getBankID());
        }

    }

    @Override
    public QuestionBank createQuestionBank(final String qbID) {

        final Optional<QuestionBank> qb = getQuestionBank(qbID);
        if (qb.isPresent()) {
            throw new IllegalArgumentException("Duplicate qbID = " + qbID);
        }

        final QuestionBank questionBankPOJO = new QuestionBankPOJO(this, qbID);
        this.questionBanks.add(questionBankPOJO);

        return questionBankPOJO;

    }

    @Override
    public Optional<QuestionBank> deleteQuestionBank(final String qbID) {

        final Optional<QuestionBank> qb = getQuestionBank(qbID);
        if (qb.isPresent()) {
            if (qb.get().hasQuestions()) {
                throw new IllegalArgumentException("Cannot delete bank which contains questions = " + qbID);
            }
            this.questionBanks.remove(qb.get());
            return qb;
        }

        return qb;

    }

//
//    public void listQuestionBank(StringBuilder toAppendTo) {
//
//        for(final QuestionBank qb : questionBanks) {
//            toAppendTo.append(qb.getBankID()).append('\n');
//        }
//
//    }

}
