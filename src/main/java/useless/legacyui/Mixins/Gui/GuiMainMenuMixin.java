package useless.legacyui.Mixins.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.Gui.GuiScreens.UtilGui;
import useless.legacyui.LegacyUI;

import java.util.Random;

@Mixin(value = GuiMainMenu.class, remap = false)
public class GuiMainMenuMixin extends GuiScreen {
    @Shadow @Final private static Random rand;
    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void init(CallbackInfo ci){
        TexturePackMixin texturePackMixin = (TexturePackMixin) Minecraft.getMinecraft(this).texturePackList.getHighestPriorityPack();
        UtilGui.panoCount = Math.max(1, texturePackMixin.invokeGetFilesInDirectory("/assets/legacyui/panoramas/").length);
        UtilGui.currentPano = rand.nextInt(UtilGui.panoCount);
    }
    @Inject(method = "drawBackground()V", at = @At("HEAD"), cancellable = true)
    private void panorama(CallbackInfo ci){
        if (LegacyUI.modSettings.getEnablePanorama().value && UtilGui.panoCount != -1 && !mc.gameSettings.alphaMenu.value){
            UtilGui.drawPanorama(this);
            ci.cancel();
        }
    }
}
