package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.biome_ambience.sounds.Plains;
import svenhjol.charmonium.module.biome_ambience.sounds.Savanna;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.module.situational_ambience.SurfaceSituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class NightPlains {
    public static SoundEvent NIGHT_PLAINS;

    public static void register() {
        NIGHT_PLAINS = ClientRegistry.sound("situational.night_plains");
    }

    public static void init(SoundHandler<SituationalSound> handler) {
        handler.getSounds().add(new SurfaceSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                var holder = getBiomeHolder(player.blockPosition());
                var key = getBiomeKey(player.blockPosition());
                return Plains.VALID_BIOME.test(holder, key)
                    || Savanna.VALID_BIOME.test(holder);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return WorldHelper.isNight(player)
                    && WorldHelper.isOutside(player)
                    && !WorldHelper.isBelowSeaLevel(player);
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return NIGHT_PLAINS;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(500) + 500;
            }

            @Override
            public float getVolume() {
                return 0.6F;
            }
        });
    }

}