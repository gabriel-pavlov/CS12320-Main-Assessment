package gpavlov.cs12320.mainassignment;

import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.domain.ModulePOJO;
import gpavlov.cs12320.mainassignment.ui.Menu;
import gpavlov.cs12320.mainassignment.ui.TeacherMenu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the gpavlov.cs12320.mainassignment.domain.Question Bank project
 *
 * @author Gabriel Pavlov
 * @version 0.1
 */

public class Application {

    public static void main(String[] args) {


//        Module module = new ModulePOJO("x00");
//        try {
//            module.createQuestionBank("qb0001");
//        } catch (IllegalArgumentException iae) {
//            System.out.println("Error: " + iae.getMessage());
//        }
        // TODO make teacher part work in memory

        final List<Module> modules = new ArrayList<>();

        final Module a1 = new ModulePOJO("a1");
        a1.createQuestionBank("b1");





        modules.add(a1);
        // rest of modules added

        final Menu tm = new TeacherMenu(modules);
        PrintStream printer = new PrintStream(System.out);
        tm.printMenu(printer);

    }

}
