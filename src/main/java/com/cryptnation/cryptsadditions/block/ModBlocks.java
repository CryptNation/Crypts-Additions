package com.cryptnation.cryptsadditions.block;

import com.cryptnation.cryptsadditions.CryptsAdditions;
import com.cryptnation.cryptsadditions.crops.VoidDeCrop;
import com.cryptnation.cryptsadditions.utilities.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

	public static final RegistryObject<Block> CRYPTIC_BLOCK = register("cryptic_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3f, 10f).sound(SoundType.METAL)));

	public static final RegistryObject<Block> CRYPTIC_ORE = register("cryptic_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).tickRandomly().setLightLevel((state) -> 2).hardnessAndResistance(10f, 10f)	.harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().sound(SoundType.STONE)));

	public static final RegistryObject<Block> VOID_ORE = register("void_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).tickRandomly().setLightLevel((state) -> 4).hardnessAndResistance(18f, 10f).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().sound(SoundType.STONE)));


	/*Crops Block*/

	public static final RegistryObject<Block> VOID_DE_CROP = Registration.BLOCKS.register("void_de_crop",	() -> new VoidDeCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));

	public static void register() {}

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
		Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));
		return toReturn;
	}

}
