package com.koolhaas.milk_forever.entity;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;

import com.koolhaas.milk_forever.MilkForever;

public final class WolfMilking {
	private WolfMilking() {
	}

	public static void register(Item wildDogMilk) {
		Identifier soundId = MilkForever.id("entity.wolf.milk");
		SoundEvent sound = Registry.register(
				BuiltInRegistries.SOUND_EVENT,
				soundId,
				SoundEvent.createVariableRangeEvent(soundId)
		);

		UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
			if (player.isSpectator()
					|| !(entity instanceof Wolf wolf)
					|| wolf.isBaby()
					|| !player.getItemInHand(hand).is(Items.BUCKET)) {
				return InteractionResult.PASS;
			}

			if (level.isClientSide()) {
				return InteractionResult.SUCCESS;
			}

			ItemStack filledBucket = ItemUtils.createFilledResult(
					player.getItemInHand(hand),
					player,
					wildDogMilk.getDefaultInstance()
			);
			player.setItemInHand(hand, filledBucket);
			wolf.playSound(sound, 1.0F, 1.0F);
			return InteractionResult.SUCCESS;
		});
	}
}
