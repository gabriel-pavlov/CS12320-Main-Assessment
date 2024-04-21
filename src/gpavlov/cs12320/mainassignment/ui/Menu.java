package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {

    final List<Module> modules = new ArrayList<>();

    public Menu(final List<Module> modules) {
        if (modules != null) {
            this.modules.addAll(modules);
        }

    }

    public abstract void printMenu(PrintStream printer, Scanner reader);

}
