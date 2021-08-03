package com.cryptnation.cryptsadditions.block;

import com.cryptnation.cryptsadditions.CryptsAdditions;
import com.cryptnation.cryptsadditions.utilities.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids
{
    public static final ResourceLocation VOID_PLASMA_STILL_RL = new ResourceLocation(CryptsAdditions.MOD_ID,
            "block/void_plasma_still");
    public static final ResourceLocation VOID_PLASMA_FLOWING_RL = new ResourceLocation(CryptsAdditions.MOD_ID,
            "block/void_plasma_flowing");
    public static final ResourceLocation VOID_PLASMA_OVERLAY_RL = new ResourceLocation(CryptsAdditions.MOD_ID,
            "block/void_plasma_overlay");

    public static final RegistryObject<FlowingFluid> VOID_PLASMA
            = Registration.FLUIDS.register("void_plasma",
            () -> new ForgeFlowingFluid.Source(ModFluids.VOID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> VOID_PLASMA_FLOWING
            = Registration.FLUIDS.register("void_plasma_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.VOID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties VOID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> VOID_PLASMA.get(), () -> VOID_PLASMA_FLOWING.get(), FluidAttributes.builder(VOID_PLASMA_STILL_RL,VOID_PLASMA_FLOWING_RL)
                .density(15).luminosity(2).rarity(Rarity.RARE).sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(VOID_PLASMA_OVERLAY_RL).viscosity(5))
                .slopeFindDistance(3).levelDecreasePerBlock(3).block(() -> ModFluids.VOID_PLASMA_BLOCK.get() ).bucket(() -> );

    public static final RegistryObject<FlowingFluidBlock> VOID_PLASMA_BLOCK = Registration.BLOCKS.register("void",
        () -> new FlowingFluidBlock(() -> ModFluids.VOID_PLASMA.get(), AbstractBlock.Properties.create(Material.WATER)
        .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    public static void register () { }
}
