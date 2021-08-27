package com.cryptnation.cryptsadditions.item;

import com.cryptnation.cryptsadditions.CryptsAdditions;
import com.cryptnation.cryptsadditions.block.ModBlocks;
import com.cryptnation.cryptsadditions.block.ModFluids;
import com.cryptnation.cryptsadditions.item.food.deBar;
import com.cryptnation.cryptsadditions.utilities.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

	/*Misc*/

	public static final RegistryObject<Item> CRYPTIC_INGOT = Registration.ITEMS.register("cryptic_ingot", () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_ROD = Registration.ITEMS.register("cryptic_rod", () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> VOID_INGOT = Registration.ITEMS.register("void_ingot", () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	/*Tools*/

	public static final RegistryObject<Item> CRYPTIC_SHOVEL = Registration.ITEMS.register("cryptic_shovel", () -> new PickaxeItem(ModItemTier.CRYPTIC_SHOVEL, 0, 0, new Item.Properties().addToolType(ToolType.SHOVEL, 4).group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_PICKAXE = Registration.ITEMS.register("cryptic_pickaxe", () -> new PickaxeItem(ModItemTier.CRYPTIC_PICKAXE, 0, 0, new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_AXE = Registration.ITEMS.register("cryptic_axe",	() -> new AxeItem(ModItemTier.CRYPTIC_AXE, 0, 0, new Item.Properties().addToolType(ToolType.AXE, 4).group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_HOE = Registration.ITEMS.register("cryptic_hoe", () -> new HoeItem(ModItemTier.CRYPTIC_HOE, 0, 0, new Item.Properties().addToolType(ToolType.HOE, 4).group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_SWORD = Registration.ITEMS.register("cryptic_sword", () -> new SwordItem(ModItemTier.CRYPTIC_SWORD, 0, 0, new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	/*Crops*/
	public static final RegistryObject<Item> VOID_DE_SEED = Registration.ITEMS.register("void_de_seed", () -> new BlockItem(ModBlocks.VOID_DE_CROP.get(), new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> DE_BAR = Registration.ITEMS.register("de_bar",() -> new deBar());

	public static final RegistryObject<Item> VOID_PLASMA_BUCKET = Registration.ITEMS.register("void_plasma_bucket", () -> new BucketItem(ModFluids.VOID_PLASMA::get, new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS).maxStackSize(16)));
	public static final RegistryObject<Item> CRYPTIC_HELMET = Registration.ITEMS.register("cryptic_helmet", () -> new ArmorItem(ModArmorMaterial.CRYPTIC, EquipmentSlotType.HEAD, new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	public static final RegistryObject<Item> CRYPTIC_CHESTPLATE = Registration.ITEMS.register("cryptic_chestplate", () -> new ArmorItem(ModArmorMaterial.CRYPTIC, EquipmentSlotType.CHEST, new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

	/*Armor*/
	public static final RegistryObject<Item> CRYPTIC_LEGGINGS = Registration.ITEMS.register("cryptic_leggings", () -> new ArmorItem(ModArmorMaterial.CRYPTIC, EquipmentSlotType.LEGS, new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));
	public static final RegistryObject<Item> CRYPTIC_BOOTS = Registration.ITEMS.register("cryptic_boots", () -> new ArmorItem(ModArmorMaterial.CRYPTIC, EquipmentSlotType.FEET,new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));


	public enum ModItemTier implements IItemTier {

		CRYPTIC_SHOVEL(4, 3000, 20, 19, 30, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get()))),
		CRYPTIC_PICKAXE(4, 3000, 20.5f, 29, 30, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get()))),
		CRYPTIC_AXE(4, 3000, 20.5f, 79, 30, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get()))),
		CRYPTIC_HOE(4, 3000, 20.5f, 9, 30, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get()))),
		CRYPTIC_SWORD(4, 3000, 10.5f, 59, 30, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get())));
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final Ingredient repairMaterial;

		ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = repairMaterial;
		}

		@Override
		public int getMaxUses() {
			return maxUses;
		}

		@Override
		public float getEfficiency() {
			return efficiency;
		}

		@Override
		public float getAttackDamage() {
			return attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return repairMaterial;
		}
	}


	public enum ModArmorMaterial implements IArmorMaterial {
		CRYPTIC(3000, new int[] {50, 60, 80, 70}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, Ingredient.fromStacks(new ItemStack(ModItems.VOID_INGOT.get())),
				CryptsAdditions.MOD_ID + ":cryptic", 20, 40);

		private final int durability;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final Ingredient repairMaterial;
		private final String name;
		private final float toughness;
		private final float knockbackResistance;

		ModArmorMaterial(int durability, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, Ingredient repairMaterial, String name, float toughness, float knockbackResistance) {
			this.durability = durability;
			this.damageReductionAmountArray = damageReductionAmountArray;
			this.enchantability = enchantability;
			this.soundEvent = soundEvent;
			this.repairMaterial = repairMaterial;
			this.name = name;
			this.toughness = toughness;
			this.knockbackResistance = knockbackResistance;
		}

		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return durability;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return damageReductionAmountArray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			return enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return soundEvent;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return repairMaterial;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public float getToughness() {
			return toughness;
		}

		@Override
		public float getKnockbackResistance() {
			return knockbackResistance;
		}
	}

}
