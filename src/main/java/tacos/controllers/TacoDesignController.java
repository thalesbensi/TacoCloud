package tacos.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.entities.Ingredient;
import tacos.entities.Taco;
import tacos.entities.TacoOrder;
import tacos.enums.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
@Controller
public class TacoDesignController {

    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder(){
        return new TacoOrder();
    }

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

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
                return ingredients
                        .stream()
                        .filter(x -> x.getType().equals(type))
                        .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()){
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
