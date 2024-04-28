package gpavlov.cs12320.mainassignment;

import gpavlov.cs12320.mainassignment.domain.*;
import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.persistence.ModuleFileStorageWrapper;
import gpavlov.cs12320.mainassignment.ui.Menu;
import gpavlov.cs12320.mainassignment.ui.StudentMenu;
import gpavlov.cs12320.mainassignment.ui.TeacherMenu;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * This is the gpavlov.cs12320.mainassignment.domain.Question Bank project
 *
 * @author Gabriel Pavlov
 * @version 0.1
 */

public class Application {

    public static void main(String[] args) {

        final List<Module> modules = new ArrayList<>();

        final Module a1 = new ModulePOJO("a1");
        final Module a2 = new ModulePOJO("a2");

        modules.add(new ModuleFileStorageWrapper(a1));
        modules.add(new ModuleFileStorageWrapper(a2));


        Scanner reader = new Scanner(System.in);
        PrintStream printer = System.out;
        printer.println("Welcome to the question bank");
        printer.println("please input whether you are a teacher (T) or a student (s)");
        String choice = reader.nextLine();
        if (choice.equalsIgnoreCase("T")) {
            final Menu tm = new TeacherMenu(modules);
            tm.printMenu(printer, reader);
        } else if (choice.equalsIgnoreCase("S")) {
            final Menu sm = new StudentMenu(modules);
            sm.printMenu(printer, reader);
        } else {
            printer.println("error, incorrect choice");
        }

    }

}
