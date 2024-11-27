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
import tacos.enums.Type;

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
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP) ,
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP) ,
                new Ingredient("GREF", "Ground Beef", Type.PROTEIN) ,
                new Ingredient("CARN", "Carnitas", Type.PROTEIN) ,
                new Ingredient("TMTO", "Tomato", Type.VEGGIES) ,
                new Ingredient("LETC", "Lettuce", Type.VEGGIES) ,
                new Ingredient("CHED", "Cheddar", Type.CHEESE) ,
                new Ingredient("MOZA", "Mozzarella", Type.CHEESE) ,
                new Ingredient("SLSA", "Salsa", Type.SAUCE) ,
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
                return ingredients
                        .stream()
                        .filter(x -> x.getType().equals(type))
                        .collect(Collectors.toList());
    }
}
