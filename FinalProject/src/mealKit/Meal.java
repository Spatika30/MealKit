package mealKit;

public class Meal {
    private final String mealName;
    private final String ingredients;
    private final String websiteName;

    public Meal(String websiteName, String mealName, String ingredients, double price) {
        this.websiteName = websiteName;
        this.mealName = mealName;
        this.ingredients = ingredients;
    }

    public String getMealName() {
        return mealName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getWebsiteName() {
        return websiteName;
    }
}
