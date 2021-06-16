package svenhjol.charmonium.module.sounds.ambience;

import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;
import svenhjol.charmonium.module.sounds.IBiomeAmbience;

import javax.annotation.Nullable;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class SwampAmbientSounds {
    public static class Day extends BaseAmbientSounds implements IBiomeAmbience {
        public Day(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_SWAMP_DAY_LONG;
        }

        @Override
        public float getLongSoundVolume() {
            return super.getLongSoundVolume() - 0.2F;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.SWAMP
                && isOutside()
                && PlayerStateClient.INSTANCE.isDaytime;
        }
    }

    public static class Night extends BaseAmbientSounds implements IBiomeAmbience {
        public Night(Player player, SoundManager soundHandler) {
            super(player, soundHandler);
        }

        @Nullable
        @Override
        public SoundEvent getLongSound() {
            return CharmoniumSounds.AMBIENCE_SWAMP_NIGHT_LONG;
        }

        @Override
        public boolean validBiomeConditions(Biome.BiomeCategory biomeCategory) {
            return biomeCategory == Biome.BiomeCategory.SWAMP
                && isOutside()
                && !PlayerStateClient.INSTANCE.isDaytime;
        }
    }
}