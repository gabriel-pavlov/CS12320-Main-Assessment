package gpavlov.cs12320.mainassignment;

import gpavlov.cs12320.mainassignment.domain.*;
import gpavlov.cs12320.mainassignment.domain.Module;
import gpavlov.cs12320.mainassignment.persistence.ModuleFileStorageWrapper;
import gpavlov.cs12320.mainassignment.ui.Menu;
import gpavlov.cs12320.mainassignment.ui.TeacherMenu;

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


//        Module module = new ModulePOJO("x00");
//        try {
//            module.createQuestionBank("qb0001");
//        } catch (IllegalArgumentException iae) {
//            System.out.println("Error: " + iae.getMessage());
//        }
        // TODO make teacher part work in memory

        final List<Module> modules = new ArrayList<>();

        final Module a1 = new ModulePOJO("a1");
        final QuestionBank b1 = a1.createQuestionBank("b1");

        final AnswerableQuestion<Option> c1 = (AnswerableQuestion<Option>) b1.addQuestion("c1", "1+1 = ", Question.Type.SINGLE_CHOICE);
        final Map<String, Object> c1a1 = new HashMap<>();
        c1a1.put("option", "1");
        c1a1.put("isCorrect", false);
        c1.addAnswer(c1a1);
        final Map<String, Object> c1a2 = new HashMap<>();
        c1a2.put("option", "2");
        c1a2.put("isCorrect", true);
        c1.addAnswer(c1a2);
        final Map<String, Object> c1a3 = new HashMap<>();
        c1a3.put("option", "3");
        c1a3.put("isCorrect", false);
        c1.addAnswer(c1a3);


        modules.add(new ModuleFileStorageWrapper(a1));
        // rest of modules added

        final Menu tm = new TeacherMenu(modules);
        // place selection here

        //        printer.println("Welcome to the question bank");
//        printer.println("please input whether you are a teacher (T) or a student (s)");
//        String choice = reader.nextLine();
//        choice = choice.toUpperCase();
//        if (choice == "T") {
//            return "tm";
//        } else if (choice == "S") {
//            return "sm";
//        } else {
//            printer.println("error, incorrect choice");
//            return "error";
//        }

        tm.printMenu(System.out, new Scanner(System.in));

    }

}
