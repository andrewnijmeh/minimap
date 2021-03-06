package minimap.minimap.listener;

import minimap.minimap.MiniMapMod;
import minimap.minimap.gui.MiniMapRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class MinimapListener {
  private Minecraft mc = Minecraft.getMinecraft();
  private MiniMapRenderer miniMapRenderer;

  public void MiniMapListener() {
    miniMapRenderer = MiniMap.getInstance().getMiniMapRenderer();
  }

  @SubscribeEvent
  public void onClientTick(TickEvent.ClientTickEvent event) {
    if (mc.theWorld != null && mc.thePlayer != null) {
      miniMapRenderer.updateMapData(mc.theWorld);
      miniMapRenderer.updateMapTexture();
      
    }
  }

  @SubscribeEvent
  public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
    if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;

    if (mc.currentScreen == null || mc.ingameGUI.getChatGUI().getChatOpen()) {
      ScaledResolution scaledResolution = new ScaledResolution(mc);
      int screenWidth =  scaledResolution.getScaledWidth(), hudSpacing = 5, miniMapSize = 45;

      miniMapRenderer.renderMiniMap(screenWidth - hudSpacing - miniMapSize, hudSpacing + miniMapSize, miniMapSize);
    }
  }

}