package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.*;
import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.PrintStream;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * the student menu is the main way students interact with the program
 */

public class StudentMenu extends Menu {


    public StudentMenu(final List<Module> modules) {
        super(modules);
    }

    /**
     * prints the menu and interaction options a student user has
     * @param printer a printstream that is used for printing text to the user
     * @param reader a scanner object that is used for reading user input
     */

    public void printMenu(PrintStream printer, Scanner reader) {

        Module workMod = null;
        Quiz workQuiz = null;
        Instant start = null;
        int questionIndex = 0;
        String choice = "1";

        while (true) {


            switch (choice) {
                case "1":
                    listModules(printer);
                    workMod = selectModByID(printer, reader);
                    listQuestionBanks(printer, workMod);
                    break;
                case "2":
                    listQuestionBanks(printer, workMod);
                    final Optional<Quiz> workQuizSelection = selectQuizByUniqueID(printer, reader);
                    if (workQuizSelection.isPresent()) {
                        //takeQuiz(printer, reader, workQuiz.get());
                        workQuiz = workQuizSelection.get();
                        start = Instant.now();
                    }
                    break;
                case "21": // moves quiz backwards one question
                    if (questionIndex > 0) {
                        questionIndex--;
                    }
                    break;
                case "22": // moves quiz forwards one question
                    if (questionIndex < workQuiz.getQuestions().size() - 1) {
                        questionIndex++;
                    }
                    break;
                case "23":
                    answerQuestion(printer, reader, workQuiz, questionIndex);
                    break;
                case "24":
                    finishQuiz(printer, workQuiz, start);
                    workQuiz = null;
                    questionIndex = 0;
                    break;
                case "3":
                    endProgram(printer);
                    return;
            }
            if (workQuiz == null) {
                printer.println("Welcome to the student quiz taker program");
                printer.println("Please select an option from the following:");
                printer.println("1) Search a specific Module to find Question Bank IDs");
                printer.println("2) Enter Question bank unique ID to begin quiz");
                printer.println("3) quit program");
            } else {
                printer.println("You are now taking a quiz");
                printer.println("Please select an option from the following:");
                if (questionIndex > 0) {
                    printer.println("21) move to previous question");
                }
                if (questionIndex < workQuiz.getQuestions().size() - 1) {
                    printer.println("22) move to next question");
                }
                printer.println("23) answer question");
                printer.println("24) finish quiz");
                printCurrentQuestion(printer, workQuiz, questionIndex);
            }
            choice = reader.nextLine();
        }


    }

    /**
     * this method allows a use to answer a question
     * @param workQuiz the current quiz being taken
     * @param questionIndex the current question the student is on
     */

    private void answerQuestion(final PrintStream printer, final Scanner reader, final Quiz workQuiz, final int questionIndex) {
        final AnswerableQuestion question = workQuiz.getQuestions().get(questionIndex);
        switch (question.getType()) {
            case SINGLE_CHOICE:
                workQuiz.captureAnswer(questionIndex, (q, qi) -> {
                    printer.println("select index of the option");
                    return reader.nextLine();
                });
                break;
            case FILL_BLANKS:
                workQuiz.captureAnswer(questionIndex, (q, qi) -> {
                    printer.println("please enter answer for blank spot " + (qi + 1));
                    return reader.nextLine();
                });
                break;
        }
    }

    /**
     * this method prints the current question
     * @param workQuiz the current quiz being taken
     * @param questionIndex the current question the student is on
     */

    private void printCurrentQuestion(final PrintStream printer, final Quiz workQuiz, final int questionIndex) {
        final AnswerableQuestion question = workQuiz.getQuestions().get(questionIndex);
        printer.println("Current question " + question.getQuestionID() + " (" + (questionIndex +1) + " of " +
                workQuiz.getQuestions().size() + ") : " + question.getQuestionText());

        if (question instanceof SingleAnswer){
            final List<Option> options = ((SingleAnswer) question).getAnswers();
            for (int j = 0; j < options.size(); j++) {
                printer.println((j + 1) + ") " + options.get(j).getAnswer());
            }
        }
    }

    /**
     * this method ends the quiz and prints the student's score
     * @param workQuiz the current quiz being taken
     * @param start the starting time of the quiz
     */

    private void finishQuiz(final PrintStream printer, final Quiz workQuiz, final Instant start) {
        final Instant finish = Instant.now();
        final float score = workQuiz.validate();
        final long durationSeconds = (finish.getEpochSecond() - start.getEpochSecond());

        printer.println("Your score is: " + score);
        printer.println("Your time in seconds was: " + durationSeconds);
        printer.println("Total questions: " + workQuiz.getQuestions().size());
        printer.println("Total questions left unanswered: " + workQuiz.getUnansweredQuestionCount());
    }

    /**
     * this method prints the list of modules currently loaded
     *
     */

    private void listModules(final PrintStream printer) {
        printer.println("The loaded modules are as follows:");
        int i = 0;
        for (Module mod : modules) {
            i++;
            printer.print(i + " ");
            printer.println(mod.getModID());
        }
    }

    /**
     * this method prints the list of question banks currently loaded
     *
     */

    private void listQuestionBanks(PrintStream printer, Module module) {
        printer.println("The loaded question banks are as follows:");
        int i = 0;
        for (QuestionBank qb : module.getQuestionBanks()) {
            i++;
            printer.print(i + " ");
            printer.println(qb.getUniqueID());

        }

    }

    /**
     * this method allows the user to select a module by its ID
     * @return module to be viewed
     */

    private Module selectModByID(final PrintStream printer, final Scanner reader) {
        while (true) {
            printer.println("Please enter module from the list to view");
            final String choice = reader.nextLine();
            Optional<Module> optionalWorkMod = modules.stream().filter(mod -> mod.getModID().equals(choice)).findFirst();
            if (optionalWorkMod.isEmpty()) {
                printer.println("error, incorrect modID selected");
            } else {
                printer.println("Selected module is " + choice);
                return optionalWorkMod.get();

            }
        }

    }

    /**
     * this method allows the user to begin a quiz by inputting its Unique ID
     * @return quiz to be used by student
     */

    private Optional<Quiz> selectQuizByUniqueID(final PrintStream printer, final Scanner reader) {

        printer.println("Please enter unique ID of quiz to be taken");
        final String searchID = reader.nextLine();
        if (!searchID.contains(":")) {
            printer.println("Invalid unique ID format");
            return Optional.empty();
        }
        final String[] splitID = searchID.split(":");
        final String modID = splitID[0];
        final String qbID = splitID[1];
        final Optional<Module> workMod = modules.stream().filter(mod -> mod.getModID().equals(modID)).findFirst();
        if (workMod.isEmpty()) {
            printer.println("error, incorrect mod ID portion of unique ID selected");
            return Optional.empty();
        }
        final Optional<QuestionBank> workBank = workMod.get().getQuestionBank(qbID);
        if (workBank.isEmpty()) {
            printer.println("error, incorrect bank ID portion of unique ID selected");
            return Optional.empty();
        }
        printer.println("How many questions would you like to answer?");
        String numberOfQuestions = reader.nextLine();
        try {
            final int quizLength = Integer.parseInt(numberOfQuestions);
            try {
                final Quiz quiz = workBank.get().createQuiz(quizLength);
                printer.println("Selected question bank is " + workBank.get().getUniqueID());
                return Optional.of(quiz);
            } catch (IllegalArgumentException negativeNumber){
                printer.println("Error: " + negativeNumber.getMessage());
            }
        } catch (NumberFormatException nfe) {
            printer.println("Error: Please provide numeric value for amount of questions");
        }

        return Optional.empty();

    }

    /**
     * this method ends the program
     */

    private void endProgram(PrintStream printer) {
        printer.println("Thank you for using the question bank program");
    }


}
