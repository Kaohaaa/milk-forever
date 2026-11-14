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

import com.koolhaas.milk_forever.MilkForever;

public final class WolfMilking {
	private WolfMilking() {
	}

	public static void register(Item carton, Item wildDogMilk) {
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
					|| !player.getItemInHand(hand).is(carton)) {
				return InteractionResult.PASS;
			}

			if (level.isClientSide()) {
				return InteractionResult.SUCCESS;
			}

			ItemStack wildDogMilkStack = ItemUtils.createFilledResult(
					player.getItemInHand(hand),
					player,
					wildDogMilk.getDefaultInstance()
			);
			player.setItemInHand(hand, wildDogMilkStack);
			wolf.playSound(sound, 1.0F, 1.0F);
			return InteractionResult.SUCCESS;
		});
	}
}
