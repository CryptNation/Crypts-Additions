package com.cryptnation.cryptsadditions;

import com.cryptnation.cryptsadditions.block.ModBlocks;
import com.cryptnation.cryptsadditions.block.ModFluids;
import com.cryptnation.cryptsadditions.item.ModItems;
import com.cryptnation.cryptsadditions.utilities.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(CryptsAdditions.MOD_ID)
public class CryptsAdditions {
	public static final String MOD_ID = "cryptsadditions";

	public static final ItemGroup CRYPTS_ADDITIONS = new ItemGroup("Crypt's Additions") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.CRYPTIC_INGOT.get());
		}
	};


	private static final Logger LOGGER = LogManager.getLogger();

	public CryptsAdditions() {

		Registration.register();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(ModBlocks.VOID_DE_CROP.get(), RenderType.getCutout());
	}
}
