package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.QuestionBank;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu extends Menu{


    public TeacherMenu(final List<Module> modules) {
        super(modules);
    }
    public String printMenu(PrintStream printer, Scanner reader) {
        printer.println("The loaded modules are as follows:");
        int i = 0;
        for(Module mod: modules) {
            i++;
            printer.print(i + " ");
            printer.println(mod);

        }

        printer.println("Please select a module from the list to edit or view");

    }

    public void listQuestionBanks(PrintStream printer, Module module) {
        printer.println("The loaded question banks are as follows:");
        int i = 0;
        for(QuestionBank qb: module.getQuestionBanks()) {
            i++;
            printer.print(i + " ");
            printer.println(qb);

        }

    }

    public void launchEditor(PrintStream printer, Scanner reader, Module module) {

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

    public void endProgram(PrintStream printer, Module module) {

    }

}
