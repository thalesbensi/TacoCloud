package tacos.entities;

import lombok.Data;
import tacos.enums.IngredientType;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final IngredientType type;
}
