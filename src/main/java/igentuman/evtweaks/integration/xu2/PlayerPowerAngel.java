package igentuman.evtweaks.integration.xu2;

import com.rwtema.extrautils2.ExtraUtils2;
import com.rwtema.extrautils2.backend.ClientRunnable;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.hud.HUDHandler;
import com.rwtema.extrautils2.hud.IHudHandler;
import com.rwtema.extrautils2.items.ItemAngelRing;
import com.rwtema.extrautils2.power.ClientPower;
import igentuman.evtweaks.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class PlayerPowerAngel extends ItemAngelRing.PlayerPowerAngelRing {
    public final static int POWER = 32;
    boolean hasHadBreakTime = false;
    private int tickFlight;

    public PlayerPowerAngel(EntityPlayer player, int type) {
        super(player, type);
    }

    public int getMaxFlightTime() {
        return ModConfig.tweaks.xu2_angel_ring_max_flight_time * 20;
    }

    @Override
    public float power(EntityPlayer playerMP) {
        return POWER;
    }

    static {
        ExtraUtils2.proxy.run(new ClientRunnable() {
            @Override
            @SideOnly(Side.CLIENT)
            public void run() {
                HUDHandler.register(new IHudHandler() {
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void render(GuiIngameForge hud, ScaledResolution resolution, float partialTicks) {
                        PlayerPowerAngel client = (PlayerPowerAngel) ClientPower.getClient(XU2Entries.angelRing.value);
                        if (client == null || client.tickFlight == 0) return;
                        EntityPlayerSP player = Minecraft.getMinecraft().player;
                        if (player == null || player.isRiding()) return;

                        Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.ICONS);

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GlStateManager.disableBlend();

                        float charge = MathHelper.clamp(1 - (((float) client.tickFlight) / client.getMaxFlightTime()), 0, 1);

                        final int barWidth = 182;

                        int width = resolution.getScaledWidth();
                        int height = resolution.getScaledHeight();

                        int x = (width / 2) - (barWidth / 2);
                        int filled = (int) (charge * (float) (barWidth + 1));
                        int top = height - 32 + 3 - 18;

                        hud.drawTexturedModalRect(x, top, 0, 84, barWidth, 5);

                        if (filled > 0) {
                            hud.drawTexturedModalRect(x, top, 0, 89, filled, 5);
                        }

                        GlStateManager.enableBlend();

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                    }
                });
            }
        });
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void tickClient() {
        EntityPlayer player = getPlayer();

        boolean isFlying = player.capabilities.isFlying;

        if (ClientPower.isPowered()) {
            boolean onGround = player.onGround;
            if (onGround && !isFlying) {
                hasHadBreakTime = false;
            }

            if (!onGround && !isFlying) {
                hasHadBreakTime = true;
            }

            if (!onGround && isFlying && tickFlight < getMaxFlightTime()) {
                tickFlight++;
            }
            if (!isFlying) {
                if (tickFlight > 0) {
                    tickFlight--;
                }
            }
        }
    }

    @Override
    public void update(boolean selected, ItemStack params) {
        EntityPlayerMP player = getPlayerMP();
        boolean isFlying = player.capabilities.isFlying;
        boolean onGround = player.onGround;
        if (!isFlying && onGround && tickFlight > 0) {
            tickFlight--;
        }
        if (isFlying && !onGround && tickFlight < getMaxFlightTime()) {
            tickFlight++;
        }
        invalid = tickFlight >= getMaxFlightTime();
        if(invalid) {
            player.capabilities.isFlying = false;
            player.capabilities.allowFlying = false;
            player.sendPlayerAbilities();
        }
        super.update(selected, params);
    }

}