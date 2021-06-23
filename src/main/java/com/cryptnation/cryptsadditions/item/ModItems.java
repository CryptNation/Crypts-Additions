package com.cryptnation.cryptsadditions.item;

import com.cryptnation.cryptsadditions.CryptsAdditions;
import com.cryptnation.cryptsadditions.block.ModBlocks;
import com.cryptnation.cryptsadditions.utilities.Registration;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import javax.security.auth.callback.Callback;

public class ModItems {

    /*Misc*/

    public static final RegistryObject<Item> CRYPTIC_INGOT =
            Registration.ITEMS.register("cryptic_ingot",
                    () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> CRYPTIC_ROD =
            Registration.ITEMS.register("cryptic_rod",
                    () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> VOID_INGOT =
            Registration.ITEMS.register("void_ingot",
                    () -> new Item(new Item.Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)));

    /*Tools*/

    public static final RegistryObject<Item> CRYPTIC_SHOVEL =
            Registration.ITEMS.register("cryptic_shovel",
                    () -> new ShovelItem(ModItemTier.CRYPTIC_SHOVEL, 0,0,
                            new Item.Properties()
                    .defaultMaxDamage(150)
                    .addToolType(ToolType.SHOVEL, 2)
                    .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> CRYPTIC_PICKAXE =
            Registration.ITEMS.register("cryptic_pickaxe",
                    () -> new PickaxeItem(ModItemTier.CRYPTIC_PICKAXE, 0,0,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.PICKAXE, 4)
                                    .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> CRYPTIC_AXE =
            Registration.ITEMS.register("cryptic_axe",
                    () -> new AxeItem(ModItemTier.CRYPTIC_AXE, 0,0,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.AXE, 4)
                                    .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> CRYPTIC_HOE =
            Registration.ITEMS.register("cryptic_hoe",
                    () -> new HoeItem(ModItemTier.CRYPTIC_HOE, 0,0,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.HOE, 2)
                                    .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static final RegistryObject<Item> CRYPTIC_SWORD =
            Registration.ITEMS.register("cryptic_sword",
                    () -> new SwordItem(ModItemTier.CRYPTIC_SWORD, 0,0,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    /*Crops*/
    public static final RegistryObject<Item> VOID_DE_SEED =
            Registration.ITEMS.register("void_de_seed",
                    () -> new BlockItem(ModBlocks.VOID_DE_CROP.get(),
                            new Item.Properties() .group(CryptsAdditions.CRYPTS_ADDITIONS)));

    public static void register() { }

    public enum ModItemTier implements IItemTier {

        CRYPTIC_SHOVEL(4, 20000, 20.5f, 8, 30, Ingredient.fromStacks(new ItemStack(ModItems.CRYPTIC_INGOT.get()))),
        CRYPTIC_PICKAXE(4, 20000, 30.5f, 12, 30, Ingredient.fromStacks(new ItemStack(ModItems.CRYPTIC_INGOT.get()))),
        CRYPTIC_AXE(4, 20000, 20.5f, 49, 30, Ingredient.fromStacks(new ItemStack(ModItems.CRYPTIC_INGOT.get()))),
        CRYPTIC_HOE(4, 20000, 10.5f, 8, 30, Ingredient.fromStacks(new ItemStack(ModItems.CRYPTIC_INGOT.get()))),
        CRYPTIC_SWORD(4, 20000, 20.5f, 37, 30, Ingredient.fromStacks(new ItemStack(ModItems.CRYPTIC_INGOT.get())));
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

}
