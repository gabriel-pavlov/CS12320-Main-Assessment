package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    final List<Module> modules = new ArrayList<>();

    public Menu(final List<Module> modules) {
        if (modules != null) {
            this.modules.addAll(modules);
        }

    }


    public String printMenu(PrintStream printer, Scanner reader) {

        printer.println("Welcome to the question bank");
        printer.println("please input whether you are a teacher (T) or a student (s)");
        String choice = reader.nextLine();
        choice = choice.toUpperCase();
        if (choice == "T") {
            return "tm";
        } else if (choice == "S") {
            return "tm";
        } else {
            printer.println("error, incorrect choice");
            return "error";
        }


    }

}
