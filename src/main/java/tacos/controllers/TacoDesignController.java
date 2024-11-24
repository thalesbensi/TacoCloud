package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.entities.Ingredient;
import tacos.entities.Taco;
import tacos.enums.IngredientType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
@Controller
public class TacoDesignController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP) ,
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP) ,
                new Ingredient("GREF", "Ground Beef", IngredientType.PROTEIN) ,
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN) ,
                new Ingredient("TMTO", "Tomato", IngredientType.VEGGIES) ,
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES) ,
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE) ,
                new Ingredient("MOZA", "Mozzarella", IngredientType.CHEESE) ,
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE) ,
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
        );

        IngredientType[] ingredientTypes = IngredientType.values();
        for (IngredientType ingredientType: ingredientTypes) {
            model.addAttribute(ingredientType.toString().toLowerCase(),
                    filterByType(ingredients, ingredientType));

        }
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, IngredientType type) {
                return ingredients
                        .stream()
                        .filter(x -> x.getType().equals(type))
                        .collect(Collectors.toList());
    }
}
