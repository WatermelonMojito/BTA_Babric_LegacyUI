package useless.legacyui;

import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.TextureRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import useless.legacyui.Settings.ILegacyOptions;

public class LegacyUI implements ClientStartEntrypoint {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ILegacyOptions modSettings;
    public static AtlasStitcher iconAtlas; //TODO probably crashes because OpenGL thread isn't

    @Override
    public void beforeClientStart() {
        SoundHelper.addSound(MOD_ID, "ui/back.wav");
        SoundHelper.addSound(MOD_ID, "ui/craft.wav");
        SoundHelper.addSound(MOD_ID, "ui/craftfail.wav");
        SoundHelper.addSound(MOD_ID, "ui/focus.wav");
        SoundHelper.addSound(MOD_ID, "ui/press.wav");
        SoundHelper.addSound(MOD_ID, "ui/scroll.wav");
        SoundHelper.addSound(MOD_ID, "ui/achievement.wav");
        LOGGER.info("LegacyUI initialized.");
    }

    @Override
    public void afterClientStart() {

    }

}
