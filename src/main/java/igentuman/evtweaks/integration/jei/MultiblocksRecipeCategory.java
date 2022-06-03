package igentuman.evtweaks.integration.jei;

import igentuman.evtweaks.ModInfo;
import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.render.RecipeRenderManager;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import org.dave.compactmachines3.misc.RenderTickCounter;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiblocksRecipeCategory implements IRecipeCategory, ITooltipCallback<ItemStack> {
    public static final String UID = ModInfo.MODID + "_multiblocks";
    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawableStatic slotDrawable;

    public MultiblocksRecipeCategory(IGuiHelper guiHelper) {
        localizedName = I18n.format(ModInfo.MODID+".jei.category.multiblocks");
        background = guiHelper.createBlankDrawable(180, 150);
        slotDrawable = guiHelper.getSlotDrawable();
    }


    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    /**
     * Return the name of the mod associated with this recipe category.
     * Used for the recipe category tab's tooltip.
     *
     * @since JEI 4.5.0
     */
    @Override
    public String getModName() {
        return ModInfo.MODID;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }


    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

        slotDrawable.draw(minecraft, 19 * 0, 0);
        slotDrawable.draw(minecraft, 19 * 1, 0);
        slotDrawable.draw(minecraft, 19 * 2, 0);
        slotDrawable.draw(minecraft, 19 * 3, 0);
        slotDrawable.draw(minecraft, 19 * 4, 0);
        slotDrawable.draw(minecraft, 19 * 5, 0);
        slotDrawable.draw(minecraft, 19 * 6, 0);
        slotDrawable.draw(minecraft, 19 * 7, 0);
        slotDrawable.draw(minecraft, 19 * 8, 0);

        slotDrawable.draw(minecraft, 19 * 0, 130);
        slotDrawable.draw(minecraft, 19 * 1, 130);
        slotDrawable.draw(minecraft, 19 * 2, 130);
        slotDrawable.draw(minecraft, 19 * 3, 130);
        slotDrawable.draw(minecraft, 19 * 4, 130);
        slotDrawable.draw(minecraft, 19 * 5, 130);
        slotDrawable.draw(minecraft, 19 * 6, 130);
        slotDrawable.draw(minecraft, 19 * 7, 130);
        slotDrawable.draw(minecraft, 19 * 8, 130);

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 19 * 0, 0);
        recipeLayout.getItemStacks().init(1, true, 19 * 1, 0);
        recipeLayout.getItemStacks().init(2, true, 19 * 2, 0);
        recipeLayout.getItemStacks().init(3, true, 19 * 3, 0);
        recipeLayout.getItemStacks().init(4, true, 19 * 4, 0);
        recipeLayout.getItemStacks().init(5, true, 19 * 5, 0);
        recipeLayout.getItemStacks().init(6, true, 19 * 6, 0);
        recipeLayout.getItemStacks().init(7, true, 19 * 7, 0);
        recipeLayout.getItemStacks().init(8, true, 19 * 8, 0);

        recipeLayout.getItemStacks().init(9, true, 19 * 0, 130);
        recipeLayout.getItemStacks().init(10, true,19 * 1, 130);
        recipeLayout.getItemStacks().init(11, true,19 * 2, 130);
        recipeLayout.getItemStacks().init(12, true,19 * 3, 130);
        recipeLayout.getItemStacks().init(13, true,19 * 4, 130);
        recipeLayout.getItemStacks().init(14, true,19 * 5, 130);
        recipeLayout.getItemStacks().init(15, true,19 * 6, 130);
        recipeLayout.getItemStacks().init(16, true,19 * 7, 130);
        recipeLayout.getItemStacks().init(17, true,19 * 8, 130);

        recipeLayout.getItemStacks().addTooltipCallback(this);
        recipeLayout.getItemStacks().set(ingredients);
    }


    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {

        String last = tooltip.get(tooltip.size()-1);
        tooltip.remove(tooltip.size()-1);
        if(slotIndex >= 0 && slotIndex <= 17) {
            tooltip.add(TextFormatting.YELLOW + I18n.format("tooltip.compactmachines3.jei.shape"));
        }
        tooltip.add(last);
    }

    public static class Wrapper implements IRecipeWrapper {
        public final MultiblockRecipe recipe;
        private final List<ItemStack> input = new ArrayList<>();
        public int layers;

        public Wrapper(MultiblockRecipe recipe) {
            this.recipe = recipe;

            int added = 0;
            for(ItemStack stack : this.recipe.getRequiredItemStacks()) {
                this.input.add(stack);
                added++;
            }

            for(int emptySlot = 0; emptySlot < 17 - added; emptySlot++) {
                this.input.add(null);
            }
            layers = recipe.getHeight();
        }
        public boolean zoom = false;

        public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {

            if(mouseX > 0 && mouseX < 150 && mouseY > 20 && mouseY < 170) {
                if(mouseButton == 0) {
                    layers--;
                    if (layers < 1) {
                        layers = recipe.getHeight();
                    }
                    recipe.setLevels(layers);
                } else {
                    zoom = !zoom;

                }
                return true;
            }
            return false;
        }

        @Override
        public void getIngredients(IIngredients ingredients) {
            ingredients.setInputs(ItemStack.class, input);
            ingredients.setOutput(ItemStack.class, this.recipe.getTargetStack());
        }

        @Override
        public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0F, 0F, 216.5F);

            mc.fontRenderer.drawString(recipe.getLabel(), 180-mc.fontRenderer.getStringWidth(recipe.getLabel()), -13, 0x444444);
            mc.fontRenderer.drawString(recipe.getDimensionsString(), 180-mc.fontRenderer.getStringWidth(recipe.getDimensionsString()), 153, 0x444444);

            GlStateManager.popMatrix();

            float angle = RenderTickCounter.renderTicks * 45.0f / 128.0f;
            TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
            textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            textureManager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableBlend();
            GlStateManager.enableCull();
            GlStateManager.enableAlpha();
            if (Minecraft.isAmbientOcclusionEnabled()) {
                GlStateManager.shadeModel(7425);
            } else {
                GlStateManager.shadeModel(7424);
            }

            GlStateManager.pushMatrix();

            // Center on recipe area
            GlStateManager.translate((float)(recipeWidth / 2), (float)(recipeHeight / 2), 100.0f);

            // Shift it a bit down so one can properly see 3d
            GlStateManager.rotate(-25.0f, 1.0f, 0.0f, 0.0f);

            // Rotate per our calculated time
            GlStateManager.rotate(angle, 0.0f, 0.5f, 0.0f);

            // Scale down to gui scale
            GlStateManager.scale(16.0f, -16.0f, 16.0f);

            // Calculate the maximum size the shape has
            BlockPos mn = recipe.getMinPos();
            BlockPos mx = recipe.getMaxPos();
            int diffX = mx.getX() - mn.getX();
            int diffY = mx.getY() - mn.getY();
            int diffZ = mx.getZ() - mn.getZ();

            // We have big recipes, we need to adjust the size accordingly.
            int maxDiff = Math.max(Math.max(diffZ + 1, diffX), diffY+3) + 1;

            float scale = 1.0f / ((float)maxDiff / 7.0f);
            if(zoom) {
                // scale *= 2;
            }
            GlStateManager.enableCull();
            GlStateManager.scale(scale, scale, scale);

            // Move the shape to the center of the crafting window
            GlStateManager.translate(
                    (diffX + 1) / -2.0f,
                    (diffY + 1) / -2.0f,
                    (diffZ + 1) / -2.0f
            );

            // If the client holds down the shift button, render everything as wireframe
            boolean renderWireframe = false;
            if(GuiScreen.isShiftKeyDown()) {
                renderWireframe = true;
                GlStateManager.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            }
            recipe.setLevels(layers);
            RecipeRenderManager.instance.renderRecipe(recipe, 0.0f, layers);

            if(renderWireframe) {
                GlStateManager.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            }

            textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            textureManager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();

            GlStateManager.popMatrix();
        }
    }
}
