package com.enderdevelopment.autocart;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RailBlock;
import net.fabricmc.fabric.api.event.player.PlayerBlockPlaceEvent;

public class AutocartMod implements ModInitializer {
    @Override
    public void onInitialize() {
        PlayerBlockPlaceEvent.AFTER.register((world, player, hand, blockPos, blockState) -> {
            if (blockState.is(Blocks.RAIL)) {
                placeMinecart(world, blockPos, blockState);
            }
        });
    }

    private void placeMinecart(Level world, BlockPos blockPos, BlockState blockState) {
        if (!world.isClientSide) {
            Minecart minecart = new Minecart(world, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
            world.addFreshEntity(minecart);
        }
    }
}