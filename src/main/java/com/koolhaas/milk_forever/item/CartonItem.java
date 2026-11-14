package com.koolhaas.milk_forever.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import com.koolhaas.milk_forever.MilkForever;

public final class CartonItem {
	private CartonItem() {
	}

	public static Item register() {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, MilkForever.id("carton"));
		Item item = Registry.register(
				BuiltInRegistries.ITEM,
				itemKey,
				new Item(new Item.Properties().setId(itemKey).stacksTo(16))
		);

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
				.register(entries -> entries.accept(item));
		return item;
	}
}
