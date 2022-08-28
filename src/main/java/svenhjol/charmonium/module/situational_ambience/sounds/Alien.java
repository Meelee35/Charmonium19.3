package svenhjol.charmonium.module.situational_ambience.sounds;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.module.biome_ambience.sounds.TheEnd;
import svenhjol.charmonium.module.situational_ambience.RepeatedSituationalSound;
import svenhjol.charmonium.module.situational_ambience.SituationalSound;
import svenhjol.charmonium.registry.ClientRegistry;

public class Alien {
    public static SoundEvent ALIEN;

    public static void register() {
        ALIEN = ClientRegistry.sound("situational.alien");
    }

    public static void init(SoundHandler<SituationalSound> handler) {

        // Register alien sound.
        handler.getSounds().add(new RepeatedSituationalSound(handler.getPlayer()) {
            @Override
            public boolean isValidSituationCondition() {
                var holder = getBiomeHolder(player.blockPosition());
                return TheEnd.VALID_BIOME.test(holder);
            }

            @Override
            public boolean isValidPlayerCondition() {
                return true;
            }

            @Nullable
            @Override
            public SoundEvent getSound() {
                return ALIEN;
            }

            @Override
            public int getDelay() {
                return level.random.nextInt(400) + 300;
            }

            @Override
            public float getVolume() {
                return 0.85F;
            }
        });
    }
}