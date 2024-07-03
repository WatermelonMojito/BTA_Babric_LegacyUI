package useless.legacyui.Mixins.Components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.hud.HotbarComponent;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.legacyui.Gui.GuiScreens.UtilGui;
import useless.legacyui.LegacyUI;

@Mixin(value = HotbarComponent.class, remap = false)
public class HotbarComponentMixin {
    @Redirect(method = "render(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/GuiIngame;IIF)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V"))
    private void hotbarFadeout(float red, float green, float blue, float alpha){
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(red, green, blue, UtilGui.getHotbarAlpha());
    }
    @Redirect(method = "renderInventorySlot(Lnet/minecraft/client/Minecraft;IIIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/model/ItemModel;renderItemIntoGui(Lnet/minecraft/client/render/tessellator/Tessellator;Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIF)V"))
    private void fadeIntoGUI(ItemModel instance, Tessellator tessellator, FontRenderer fontrenderer, RenderEngine renderengine, ItemStack itemstack, int x, int y, float alpha){
       instance.renderItemIntoGui(Tessellator.instance, fontrenderer, renderengine, itemstack, x, y, 1, UtilGui.getHotbarAlpha());
    }
    @Redirect(method = "renderInventorySlot(Lnet/minecraft/client/Minecraft;IIIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/model/ItemModel;renderItemOverlayIntoGUI(Lnet/minecraft/client/render/tessellator/Tessellator;Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIF)V"))
    private void fadeIntoGUIOverlay(ItemModel instance, Tessellator tessellator, FontRenderer fontrenderer, RenderEngine renderengine, ItemStack itemstack, int x, int y, float alpha){
        instance.renderItemOverlayIntoGUI(tessellator, fontrenderer, renderengine, itemstack, x, y, UtilGui.getHotbarAlpha());
    }
    @Inject(method = "Lnet/minecraft/client/gui/hud/HotbarComponent;isVisible(Lnet/minecraft/client/Minecraft;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void dontRenderInGuiHotbar(Minecraft mc, CallbackInfoReturnable<Boolean> cir){
        if (LegacyUI.modSettings.getHideHotbarInGUIs().value){
            if (mc.currentScreen instanceof GuiContainer){
                cir.setReturnValue(false);
            }
        }
    }
}
