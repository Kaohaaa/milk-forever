package com.koolhaas.milk_forever;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MilkForever implements ModInitializer {
	public static final String MOD_ID = "milk-forever";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final ResourceKey<Item> WILD_DOG_MILK_KEY =
			ResourceKey.create(Registries.ITEM, id("wild_dog_milk"));
	public static final Item WILD_DOG_MILK = Registry.register(
			BuiltInRegistries.ITEM,
			WILD_DOG_MILK_KEY,
			new Item(new Item.Properties()
					.setId(WILD_DOG_MILK_KEY)
					.stacksTo(1)
					.food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build())
					.usingConvertsTo(Items.BUCKET))
	);

	@Override
	public void onInitialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
				.register(entries -> entries.accept(WILD_DOG_MILK));

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
