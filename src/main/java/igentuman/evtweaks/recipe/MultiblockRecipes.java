package igentuman.evtweaks.recipe;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.stream.JsonReader;
import igentuman.evtweaks.EvTweaks;

import igentuman.evtweaks.util.JarExtract;
import igentuman.evtweaks.util.ResourceLoader;
import igentuman.evtweaks.util.SerializationHelper;

public class MultiblockRecipes {
    private static List<MultiblockRecipe> recipes = new ArrayList<>();

    private static  List<MultiblockRecipe> avaliableRecipes = new ArrayList<>();

    public static List<MultiblockRecipe> getRecipes() {
        return recipes;
    }

    public static List<MultiblockRecipe> getAvaliableRecipes() {
        if(avaliableRecipes.size() < 1) {
            for (MultiblockRecipe recipe: recipes) {
                if(recipe.isValid()) {
                    avaliableRecipes.add(recipe);
                }
            }
        }
        return avaliableRecipes;
    }

    public static void init() {
        loadRecipes();
        File evTweaks = new File("config", "evtweaks");
        File recipeDirectory = new File("config/evtweaks", "recipes");
        if(!recipeDirectory.exists()) {
            if(!evTweaks.exists()) {
                evTweaks.mkdir();
            }
            recipeDirectory.mkdir();
            JarExtract.copy("assets/evtweaks/config/recipes", recipeDirectory);
        }

    }



    public static MultiblockRecipe getRecipeByName(String name) {
        for (MultiblockRecipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }

        return null;
    }

    private static void loadRecipes() {
        File recipeDirectory = new File("config/evtweaks", "recipes");
        if (!recipeDirectory.exists()) {
            recipeDirectory.mkdir();
        }
        MultiblockRecipe recipe;
        ResourceLoader loader = new ResourceLoader(EvTweaks.class, recipeDirectory, "assets/evtweaks/config/recipes/");
        for(Map.Entry<String, InputStream> entry : loader.getResources().entrySet()) {
            String filename = entry.getKey();
            InputStream is = entry.getValue();

            if (!filename.endsWith(".json")) {
                continue;
            }
            JsonReader reader = new JsonReader(new InputStreamReader(is));

            try {
                recipe = SerializationHelper.GSON.fromJson(reader, MultiblockRecipe.class);
            } catch (NullPointerException e) {
                recipe = null;
            }

            if (recipe == null) {
                continue;
            }

            recipes.add(recipe);
        }
    }
}