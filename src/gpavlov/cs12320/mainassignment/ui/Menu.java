package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this is the main menu
 */

public abstract class Menu {

    final List<Module> modules = new ArrayList<>();

    /**
     * when menu is first created, it loads all modules to be used in the program if none are loaded
     * @param modules the list of modules the program has access to
     */

    public Menu(final List<Module> modules) {
        if (modules != null) {
            this.modules.addAll(modules);
        }

    }

    public abstract void printMenu(PrintStream printer, Scanner reader);

}
