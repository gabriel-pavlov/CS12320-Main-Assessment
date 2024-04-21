package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.ModulePOJO;
import gpavlov.cs12320.mainassignment.domain.QuestionBank;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static jdk.dynalink.StandardNamespace.findFirst;

public class TeacherMenu extends Menu {


    public TeacherMenu(final List<Module> modules) {
        super(modules);
    }

    public void printMenu(PrintStream printer, Scanner reader) {
        listModules(printer);
        final Module workMod = selectModByID(printer, reader);


        while (true) {
            listQuestionBanks(printer, workMod);
            printer.println("Please select from following options");
            printer.println("1) create a question bank");
            printer.println("2) edit existing question bank");

            final String choice = reader.nextLine();
            switch (choice) {
                case "1":
                    createQBWithMenu(printer, reader, workMod);
                    break;

            }

        }

    }

    private void createQBWithMenu(final PrintStream printer, final Scanner reader, final Module workMod) {
        printer.println("Please enter ID of question bank");
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

    private void launchEditor(PrintStream printer, Scanner reader, Module module) {

        printer.println("Please select an option from following:");
        printer.println("1) create new question bank for module" + module.getModID());
        printer.println("2) new question bank for existing question bank in " + module.getModID());
        printer.println("3) delete question bank for question bank" + module.getModID());

        int choice = reader.nextInt();
        reader.nextLine();
        switch (choice) {
            case 1:
                //mod.createqb
                break;
            case 2:
                printer.println("Please enter the QBID of the question bank you wish to add a question to");
        }


    }

    private void endProgram(PrintStream printer, Module module) {

    }

}
