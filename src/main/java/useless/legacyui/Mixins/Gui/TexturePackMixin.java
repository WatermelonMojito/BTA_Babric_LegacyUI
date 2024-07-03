package useless.legacyui.Mixins.Gui;

import net.minecraft.client.render.texturepack.TexturePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import static net.minecraft.core.lang.I18n.getFilesInDirectory;

@Mixin(value = TexturePack.class, remap = false)
public interface TexturePackMixin {
    @Invoker("getFilesInDirectory")
    String[] invokeGetFilesInDirectory(String directory);

}
