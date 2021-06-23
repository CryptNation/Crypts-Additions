package com.cryptnation.cryptsadditions.item.food;

import com.cryptnation.cryptsadditions.CryptsAdditions;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class deBar extends Item
{
    public deBar()
    {
        super(new Properties().group(CryptsAdditions.CRYPTS_ADDITIONS)
        .food(new Food.Builder().hunger(20).saturation(1.5f)
                .effect(() -> new EffectInstance(Effects.GLOWING, 500, 2),0.8f).build()));
    }
}
