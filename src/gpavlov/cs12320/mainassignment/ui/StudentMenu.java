package gpavlov.cs12320.mainassignment.ui;

import gpavlov.cs12320.mainassignment.domain.Module;
import java.util.Scanner;

public class StudentMenu extends Menu{


    public void printMenu() {
        System.out.println("Welcome to the student quiz taker program");
        System.out.println("Please select an option from the following:");
        System.out.println("1) Search a specific Module to find Question Bank IDs");
        System.out.println("2) ");

    }

    public void searchMod() {

        System.out.println("Please enter a Module ID from the following list");
        Scanner reader = new Scanner(System.in);
        String searchID = reader.nextLine();
//        if (Module.getModID == searchID) {
//           System.out.println(Module.getQuestionBanks);
//        } else {
//           System.out.println("Error: invalid bank ID");
//        }

        printMenu();
    }

    public void takeQuiz(String bankUniqueID) {

    }

    public void endProgram() {

    }

}
