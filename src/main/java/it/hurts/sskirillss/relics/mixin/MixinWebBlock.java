package it.hurts.sskirillss.relics.mixin;

import it.hurts.sskirillss.relics.init.ItemRegistry;
import it.hurts.sskirillss.relics.utils.EntityUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.WebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WebBlock.class)
public class MixinWebBlock {
    @Inject(at = @At(value = "HEAD"), method = "entityInside", cancellable = true)
    protected void ignoreWeb(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo info) {
        if (!(entity instanceof LivingEntity))
            return;

        if (!EntityUtils.findEquippedCurio((LivingEntity) entity, ItemRegistry.SPIDER_NECKLACE.get()).isEmpty())
            info.cancel();
    }
}