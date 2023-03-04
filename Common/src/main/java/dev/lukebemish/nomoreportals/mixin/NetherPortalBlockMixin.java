/*
 * Copyright (C) 2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.nomoreportals.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
    @Unique
    private static final TagKey<EntityType<?>> PORTAL_ALLOWED = TagKey.create(ResourceKey.createRegistryKey(new ResourceLocation("entity_type")), new ResourceLocation("nomoreportals", "portal_allowed"));

    @Inject(method = "entityInside", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/Entity.handleInsidePortal(Lnet/minecraft/core/BlockPos;)V"), cancellable = true)
    private void nomoreportals$onEntityInside(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (level.isClientSide) return;
        if (entity.getType().is(PORTAL_ALLOWED)) return;
        if (entity instanceof Player player && !player.isCreative()) ci.cancel();
    }
}
