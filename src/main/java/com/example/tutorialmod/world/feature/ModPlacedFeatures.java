package com.example.tutorialmod.world.feature;

import java.util.List;

import com.example.tutorialmod.TutorialMod;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TutorialMod.MODID);

    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED = PLACED_FEATURES.register("zircon_ore_placed", 
            () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(), 
                    commoneOrePlacement(15, // veins per chunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> ENDSTONE_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("endstone_zircon_ore_placed", 
            () -> new PlacedFeature(ModConfiguredFeatures.ENDSTONE_ZIRCON_ORE.getHolder().get(), 
                    commoneOrePlacement(15, // veins per chunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> NETHERRACK_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("netherrack_zircon_ore_placed", 
            () -> new PlacedFeature(ModConfiguredFeatures.NETHERRACK_ZIRCON_ORE.getHolder().get(), 
                    commoneOrePlacement(15, // veins per chunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier a, PlacementModifier b) {
        return List.of(a, InSquarePlacement.spread(), b, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commoneOrePlacement(int veinsPerChunk, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(veinsPerChunk), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int a, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(a), modifier);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
