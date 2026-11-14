package com.koolhaas.milk_forever.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import com.koolhaas.milk_forever.MilkForever;

public final class WildDogMilkItem {
	private WildDogMilkItem() {
	}

	public static Item register() {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, MilkForever.id("wild_dog_milk"));
		Item item = Registry.register(
				BuiltInRegistries.ITEM,
				itemKey,
				new Item(new Item.Properties()
						.setId(itemKey)
						.stacksTo(16)
						.food(
								new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build(),
								Consumable.builder()
										.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 300)))
										.build()
						)
						.usingConvertsTo(Items.BUCKET))
		);

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
				.register(entries -> entries.accept(item));
		return item;
	}
}
