package igentuman.evtweaks.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipeSerializer;

public class SerializationHelper {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(BlockInformation.class, new BlockInformationSerializer())
            .registerTypeAdapter(MultiblockRecipe.class, new MultiblockRecipeSerializer())
            .create();
}