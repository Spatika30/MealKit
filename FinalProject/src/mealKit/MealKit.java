package mealKit;

import java.util.Scanner;

public class MealKit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Meal Planner!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Search for meals");
            System.out.println("2. View top 10 cheapest meal plans");
            System.out.println("3. Exit");

            int choice = getUserChoice(scanner, 3);
            switch (choice) {
                case 1:
                    ExclusionHandler.handleMeals(scanner);
                    break;
                case 2:
                    MealPlanHandler.findTop10CheapestMeals();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }

    static int getUserChoice(Scanner scanner, int maxOption) {
        while (true) {
            System.out.print("Enter your choice (1-" + maxOption + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
