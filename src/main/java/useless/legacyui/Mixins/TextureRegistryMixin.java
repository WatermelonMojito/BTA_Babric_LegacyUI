package useless.legacyui.Mixins;

import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.TextureRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.LegacyUI;

@Mixin(value = TextureRegistry.class, remap = false)
public class TextureRegistryMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void createIconAtlas(CallbackInfo callbackInfo){
        String[] iconTextures = new String[]{"armor", "bricks", "grass", "health", "lever", "modded", "painting", "planks", "rail", "redstonerail", "tools"};
        LegacyUI.iconAtlas = TextureRegistry.register("legacyui$icon", new AtlasStitcher("icon", true, "/assets/legacyui/icon/Unknown.png"));
        for (String texture: iconTextures) {
            TextureRegistry.getTexture("legacyui:legacyui$icon/" + texture);
        }
    }
}
