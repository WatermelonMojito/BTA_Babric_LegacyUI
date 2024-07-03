package useless.legacyui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.core.sound.SoundCategory;
import java.util.Random;

public class LegacySoundManager {
    public static Random rand = new Random();
    public static Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
    public static SoundManager sndManager = mc.sndManager;
    public static float volume = 1f;
    public static float getPitch(boolean randomPitch){
        return (randomPitch && LegacyUI.modSettings.getUseRandomPitch().value) ? 1f + ((rand.nextFloat()-0.5f)/16f) : 1f;
    }
    public static class play {
        public static void press(boolean randomPitch){
            if (LegacyUI.modSettings.getUseLegacySounds().value){
                sndManager.playSound("legacyui.ui.press", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
            } else {
                sndManager.playSound("random.ui_click", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
            }
        }
        public static void back(boolean randomPitch){
            if (LegacyUI.modSettings.getUseLegacySounds().value) {
                sndManager.playSound("legacyui.ui.back", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
            } else {
                sndManager.playSound("random.ui_back", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
            }
        }
        public static void craft(boolean randomPitch){
            sndManager.playSound("legacyui.ui.craft", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void craftfail(boolean randomPitch){
            sndManager.playSound("legacyui.ui.craftfail", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void focus(boolean randomPitch){
            sndManager.playSound("legacyui.ui.focus", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void scroll(boolean randomPitch){
            sndManager.playSound("legacyui.ui.scroll", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void achievement(boolean randomPitch){
            sndManager.playSound("legacyui.ui.achievement", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
    }
}
