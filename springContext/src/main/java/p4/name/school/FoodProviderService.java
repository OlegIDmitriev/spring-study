package p4.name.school;

import p4.name.Food;
import p4.name.IFoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderService implements IFoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("Coke"));
        lunchSet.add(new Food("Hamburger"));
        lunchSet.add(new Food("French fries"));

        return lunchSet;
    }
}
