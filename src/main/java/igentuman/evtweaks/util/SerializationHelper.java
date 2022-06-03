package igentuman.evtweaks.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipeSerializer;
import org.dave.compactmachines3.schema.BlockInformation;
import org.dave.compactmachines3.schema.BlockInformationSerializer;
import org.dave.compactmachines3.schema.Schema;
import org.dave.compactmachines3.schema.SchemaSerializer;

public class SerializationHelper {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(BlockInformation.class, new BlockInformationSerializer())
            .registerTypeAdapter(Schema.class, new SchemaSerializer())
            .registerTypeAdapter(MultiblockRecipe.class, new MultiblockRecipeSerializer())
            .create();

}