package gpavlov.cs12320.mainassignment.persistence;

import gpavlov.cs12320.mainassignment.domain.*;
import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * this is the wrapper object used for saving and loading modules.
 */

public class ModuleFileStorageWrapper implements Module {

    /**
     * {@inheritDoc}
     */

    private final Module wrapped;

    public ModuleFileStorageWrapper(final Module wrapped) {
        this.wrapped = wrapped;
        this.load();
    }

    /**
     *
     * @return the mod ID of wrapped module
     */

    @Override
    public String getModID() {
        return wrapped.getModID();
    }

    /**
     * this implementation extracts source question bank list from the wrapped insatance and generates a new list
     * where each element is wrapped by {@link QuestionBankFileStorageWrapper} so that save functions can be triggered
     * when object is changed.
     * @return wrapped question bank 
     */

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
        if (qWrapped.isPresent()) {
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
        if (qb.isPresent()) {
            return Optional.of(wrapped(qb.get()));
        }
        return qb;
    }

    /**
     * this method adds a new wrapped question bank when the original module has a question bank added
     * @param qWrapped the question bank to be wrapped
     * @return the wrapped question bank
     */

    private QuestionBankFileStorageWrapper wrapped(final QuestionBank qWrapped) {
        return new QuestionBankFileStorageWrapper(qWrapped, this);
    }

    Module getWrapped() {
        return wrapped;
    }

    /**
     * this method is used to load object data from text files into the program.
     */

    void load() {
        final Module data = getWrapped();
        // load from text file
        // load wrapped version into questionBanks
        System.out.println("===LOAD===");
        final File file = new File(data.getModID() + ".txt");
        if (!file.exists()) {
            return;
        }
        try (Scanner fileRead = new Scanner(file)) {
            QuestionBank qb = null;
            while (fileRead.hasNextLine()) {
                final String line = fileRead.nextLine();
                if ("END".equals(line)) {
                    qb = null;
                } else if (qb == null) {
                    qb = data.createQuestionBank(line);
                } else {
                    // adding question to QB
                    final String[] tokens = line.split("\t");
                    final String qID = tokens[0];
                    final String text = tokens[1];
                    final Question.Type type = Question.Type.valueOf(tokens[2]);
                    final Question q = qb.addQuestion(qID, text, type);

                    switch (type) {
                        case Question.Type.SINGLE_CHOICE:
                            for (int i = 3; i < tokens.length - 1; i += 2) {
                                final String option = tokens[i];
                                final boolean isCorrect = Boolean.parseBoolean(tokens[i + 1]);
                                final Map<String, Object> args = new HashMap<>();
                                args.put("option", option);
                                args.put("isCorrect", isCorrect);
                                ((AnswerableQuestion<Option>) q).addAnswer(args);
                            }
                            break;
                        case Question.Type.FILL_BLANKS:
                            for (int i = 3; i < tokens.length; i++) {
                                final String blank = tokens[i];
                                final Map<String, Object> args = new HashMap<>();
                                args.put("blank", blank);
                                ((AnswerableQuestion<Blank>) q).addAnswer(args);
                            }
                            break;
                    }
                }


            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * this method is used to save object data to a txt file any time a change is made
     */

    void save() {
        final Module data = getWrapped();

        // load from text file
        // 00001.txt <- txt file name is mod ID
        // o1 <- question bank ID
        // A,1+2 =,SINGLE_ANSWER,"2",true,"1",false <- question ID, text, type, number of option (10 max) and repeated options
        // B,"time now is ____",FILL_BLANKS,"6pm" <- question ID, text, number of Blanks (10 max) and repeated blanks
        // END <- end question bank
        // o2 <- start of new qb
        // ... <- repeat until end of file, which is end of module

        System.out.println("===SAVE===");
        final File file = new File(data.getModID() + ".txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            data.getQuestionBanks().stream().forEach(questionBank -> {
                writer.println(questionBank.getBankID());
                questionBank.getQuestions().stream().forEach(question -> {
                    writer.print(question.getQuestionID());
                    writer.print("\t");
                    writer.print(question.getQuestionText());
                    writer.print("\t");
                    writer.print(question.getType());
                    writer.print("\t");


                    switch (question.getType()) {
                        case SINGLE_CHOICE:
                            ((AnswerableQuestion<Option>) question).getAnswers().stream().forEach(option -> {
                                writer.print(option.getAnswer());
                                writer.print("\t");
                                writer.print(option.isCorrect());
                                writer.print("\t");
                            });
                            break;
                        case FILL_BLANKS:
                            ((AnswerableQuestion<Blank>) question).getAnswers().stream().forEach(blank -> {
                                writer.print(blank.getAnswer());
                                writer.print("\t");
                            });
                            break;
                    }

                    writer.println();


                });
                writer.println("END");
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
