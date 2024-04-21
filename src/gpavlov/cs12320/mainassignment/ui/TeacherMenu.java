package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.*;
import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.PrintStream;
import java.util.*;

import static jdk.dynalink.StandardNamespace.findFirst;

public class TeacherMenu extends Menu {


    public TeacherMenu(final List<Module> modules) {
        super(modules);
    }

    public void printMenu(PrintStream printer, Scanner reader) {
        Module workMod = null;
        QuestionBank workBank = null;
        String choice = "1";

        while (true) {

            switch (choice) {
                case "1":
                    listModules(printer);
                    workMod = selectModByID(printer, reader);
                    listQuestionBanks(printer, workMod);
                    break;
                case "2":
                    createQBWithMenu(printer, reader, workMod);
                    listQuestionBanks(printer, workMod);
                    break;
                case "3":
                    deleteQBWithMenu(printer, reader, workMod);
                    listQuestionBanks(printer, workMod);
                    break;
                case "4":
                    workBank = selectQuestionBankByID(printer, reader, workMod);
                    listQuestions(printer, workBank);
                    break;
                case "41":
                    createQuestionWithMenu(printer, reader, workBank);
                    listQuestions(printer, workBank);
                    break;
                case "42":
                    deleteQuestionWithMenu(printer, reader, workBank);
                    listQuestions(printer, workBank);
                    break;
                case "43":
                    workBank = null;
                    listQuestionBanks(printer, workMod);

                    break;
                case "5":
                    endProgram(printer);
                    return;
            }
            if (workBank == null) {
                printer.println("Please select from following options");
                printer.println("1) change module");
                printer.println("2) create new question bank");
                printer.println("3) delete existing question bank");
                printer.println("4) edit existing question bank");
                printer.println("5) quit program");
            } else {
                printer.println("Please select an option from following:");
                printer.println("41) add question");
                printer.println("42) delete question");
                printer.println("43) exit to main menu");
            }
            choice = reader.nextLine();

        }

    }

    private QuestionBank selectQuestionBankByID(final PrintStream printer, final Scanner reader, final Module workMod) {
        QuestionBank qb;
        while (true) {
            printer.println("Please enter ID of question bank");
            final String searchID = reader.nextLine();
            final Optional<QuestionBank> optionalQB = workMod.getQuestionBank(searchID);
            if (optionalQB.isEmpty()) {
                printer.println("error, incorrect question bank ID selected");
            } else {
                qb = optionalQB.get();
                printer.println("Selected question bank is " + qb.getUniqueID());
                return qb;

            }

        }
    }

    private void deleteQBWithMenu(final PrintStream printer, final Scanner reader, final Module workMod) {
        printer.println("Please enter ID of question bank to be deleted");
        final String deletionID = reader.nextLine();
        try {
            workMod.deleteQuestionBank(deletionID);
        } catch (IllegalArgumentException incorrectID) {
            printer.println("Error: " + incorrectID.getMessage());
        }
    }

    private void createQBWithMenu(final PrintStream printer, final Scanner reader, final Module workMod) {
        printer.println("Please enter ID of question bank to be created");
        final String newID = reader.nextLine();
        try {
            workMod.createQuestionBank(newID);
        } catch (IllegalArgumentException alreadyUsedID) {
            printer.println("Error " + alreadyUsedID.getMessage());
        }

    }

    private Module selectModByID(final PrintStream printer, final Scanner reader) {
        while (true) {
            printer.println("Please enter module from the list to edit or view");
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

    private void listModules(final PrintStream printer) {
        printer.println("The loaded modules are as follows:");
        int i = 0;
        for (Module mod : modules) {
            i++;
            printer.print(i + " ");
            printer.println(mod.getModID());
        }
    }

    private void listQuestionBanks(PrintStream printer, Module module) {
        printer.println("The loaded question banks are as follows:");
        int i = 0;
        for (QuestionBank qb : module.getQuestionBanks()) {
            i++;
            printer.print(i + " ");
            printer.println(qb.getUniqueID());

        }

    }

    private void deleteQuestionWithMenu(final PrintStream printer, final Scanner reader, final QuestionBank workBank) {
        printer.println("Please enter index of question to be deleted");
        final String deletionIndex = reader.nextLine();
        try {
            final int index = Integer.parseInt(deletionIndex) - 1;
            workBank.removeQuestion(index);
        } catch (NumberFormatException nfe) {
            printer.println("Error: Please provide numeric index");
        } catch (IllegalArgumentException incorrectID) {
            printer.println("Error: " + incorrectID.getMessage());
        }

    }

    private void createQuestionWithMenu(final PrintStream printer, final Scanner reader, final QuestionBank workBank) {

        printer.println("Please enter the question ID");
        final String id = reader.nextLine();


        printer.println("Please enter the question please select from following types:");
        printer.println("1) single answer question");
        printer.println("2) fill in the blanks question");
        final String type = reader.nextLine();

        if ("1".equals(type)) {
            printer.println("Please enter single answer question text");
        } else {
            printer.println("Please enter blanks question text, including underscores where blanks should be");
        }

        final String text = reader.nextLine();

        try {
            final Question q = workBank.addQuestion(id, text, "1".equals(type) ? Question.Type.SINGLE_CHOICE : Question.Type.FILL_BLANKS);
            switch (q.getType()) {
                case SINGLE_CHOICE:
                    createOptions(printer, reader, (AnswerableQuestion<Option>) q);
                    break;
                case FILL_BLANKS:
                    createBlanks(printer, reader, (AnswerableQuestion<Blank>) q);
                default:
            }
        } catch (IllegalArgumentException alreadyUsedID) {
            printer.println("Error " + alreadyUsedID.getMessage());
        }


    }

    private void createOptions(final PrintStream printer, final Scanner reader, final AnswerableQuestion<Option> answer) {
        printer.println("Please enter how many answers you would like to have (1-10)");
        int numberOfAnswers = 0;
        while (true) {
            try {
                String amount = reader.nextLine();
                numberOfAnswers = Integer.parseInt(amount);
                if (numberOfAnswers > 0 && numberOfAnswers < 11) {
                    for (int i = 0; i < numberOfAnswers; i++) {
                        printer.println("Please enter answer contents");
                        final String content = reader.nextLine();
                        printer.println("is this correct answer? (Y/N)");
                        final boolean isCorrect = "Y".equalsIgnoreCase(reader.nextLine());
                        final Map<String, Object> c1a3 = new HashMap<>();
                        c1a3.put("option", content);
                        c1a3.put("isCorrect", isCorrect);
                        answer.addAnswer(c1a3);
                    }
                    break;
                }
            } catch (Exception e) {
                // invalid input
            }
            printer.println("Error: please enter valid number");

        }
    }

    private void createBlanks(final PrintStream printer, final Scanner reader, final AnswerableQuestion<Blank> answer) {
        printer.println("Please enter how many blanks you would like to have (1-10)");
        int numberOfAnswers = 0;
        while (true) {
            try {
                String amount = reader.nextLine();
                numberOfAnswers = Integer.parseInt(amount);
                if (numberOfAnswers > 0 && numberOfAnswers < 11) {
                    for (int i = 0; i < numberOfAnswers; i++) {
                        printer.println("Please enter answer contents");
                        final String content = reader.nextLine();
                        final Map<String, Object> c1a3 = new HashMap<>();
                        c1a3.put("blank", content);
                        answer.addAnswer(c1a3);
                    }
                    break;
                }
            } catch (Exception e) {
                // invalid input
            }
            printer.println("Error: please enter valid number");

        }
    }

    private void launchEditor(PrintStream printer, Scanner reader, Module module) {
        QuestionBank qb;
        while (true) {
            printer.println("Please enter ID of question bank");
            final String searchID = reader.nextLine();
            final Optional<QuestionBank> optionalQB = module.getQuestionBank(searchID);
            if (optionalQB.isEmpty()) {
                printer.println("error, incorrect question bank ID selected");
            } else {
                qb = optionalQB.get();
                printer.println("Selected question bank is " + qb.getUniqueID());
                break;

            }

        }


    }

    private void listQuestions(final PrintStream printer, final QuestionBank qb) {
        printer.println("The loaded questions are as follows:");
        int i = 0;
        for (Question question : qb.getQuestions()) {
            i++;
            printer.print(i + " ");
            printer.println(question.getQuestionID() + " [" + question.getType() + "]: " + question.getQuestionText());
        }
    }

    private void endProgram(PrintStream printer) {
        printer.println("Thank you for using the question bank program");
    }

}
