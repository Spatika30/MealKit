package mealKit;

import java.io.*;
import java.util.*;

public class ExclusionHandler {

    private static List<String> exclusionList = new ArrayList<>();
    private static Trie ingredientTrie = new Trie();

    public static void handleMeals(Scanner scanner) {
        System.out.println("\nMeal Options:");
        System.out.println("1. Show meals based on exclusions");
        System.out.println("2. View top 10 cheapest meals");

        int choice = MealKit.getUserChoice(scanner, 2);
        switch (choice) {
            case 1:
                collectExclusionList(scanner);
                filterMealsByExclusion();
                break;
            case 2:
                MealPlanHandler.findTop10CheapestMeals();
                break;
        }
    }

    private static void collectExclusionList(Scanner scanner) {
        System.out.println("Please list the ingredients you want to exclude (type 'done' when finished):");
        exclusionList.clear();
        while (true) {
            String ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("done"))
                break;
            exclusionList.add(ingredient);
            ingredientTrie.insert(ingredient);
            System.out.println("Excluded ingredient added: " + ingredient);
        }
        System.out.println("Exclusion list: " + exclusionList);
    }

    private static void filterMealsByExclusion() {
        String filePath = "meals.csv";
        System.out.println("\nSuggested Meals for you:");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (values.length < 7)
                    continue;

                Meal meal = new Meal(values[0], values[1], values[6], 0);

                if (containsExcludedItem(meal.getMealName()) || containsExcludedItem(meal.getIngredients())) {
                    continue;
                }

                System.out.printf("%-85s | %-20s%n", meal.getMealName(), meal.getWebsiteName());
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    private static boolean containsExcludedItem(String text) {
        for (String exclusion : exclusionList) {
            if (text.toLowerCase().contains(exclusion.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
