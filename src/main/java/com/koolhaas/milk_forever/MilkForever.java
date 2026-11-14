package com.koolhaas.milk_forever;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.koolhaas.milk_forever.entity.WolfMilking;
import com.koolhaas.milk_forever.item.CartonItem;
import com.koolhaas.milk_forever.item.WildDogMilkItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MilkForever implements ModInitializer {
	public static final String MOD_ID = "milk-forever";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Item carton = CartonItem.register();
		Item wildDogMilk = WildDogMilkItem.register(carton);
		WolfMilking.register(carton, wildDogMilk);

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Wild Dog Milk!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
