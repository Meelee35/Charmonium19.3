package svenhjol.charmonium.module.biome_ambience.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.handler.SoundHandler;
import svenhjol.charmonium.helper.WorldHelper;
import svenhjol.charmonium.module.biome_ambience.BiomeSound;
import svenhjol.charmonium.module.biome_ambience.SurfaceBiomeSound;
import svenhjol.charmonium.registry.ClientRegistry;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Savanna {
    public static SoundEvent DAY;
    public static SoundEvent NIGHT;
    public static final Predicate<Holder<Biome>> VALID_BIOME = holder -> holder.is(BiomeTags.IS_SAVANNA);

    public static void register() {
        DAY = ClientRegistry.sound("ambience.savanna.day");
        NIGHT = ClientRegistry.sound("ambience.savanna.night");
    }

    public static void init(SoundHandler<BiomeSound> handler) {
        // Register savanna day.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return DAY;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isDay(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
            }
        });

        // Register savanna night.
        handler.getSounds().add(new SurfaceBiomeSound(handler.getPlayer(), false) {
            @Nullable
            @Override
            public SoundEvent getSound() {
                return NIGHT;
            }

            @Override
            public boolean isValidPlayerCondition() {
                return super.isValidPlayerCondition() && WorldHelper.isNight(player);
            }

            @Override
            public boolean isValidBiomeCondition(Holder<Biome> holder, ResourceKey<Biome> key) {
                return VALID_BIOME.test(holder);
            }
        });
    }
}
