package it.hurts.sskirillss.relics.items.relics.base.handlers;

import it.hurts.sskirillss.relics.items.RelicContractItem;
import it.hurts.sskirillss.relics.utils.NBTUtils;
import it.hurts.sskirillss.relics.utils.RelicUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TooltipHandler {
    @SubscribeEvent
    public static void onTooltipRender(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        long time = NBTUtils.getLong(stack, RelicContractItem.TAG_DATE, -1);

        if (event.getPlayer() == null || stack.isEmpty() || time <= -1)
            return;

        World world = event.getPlayer().getCommandSenderWorld();
        PlayerEntity owner = RelicUtils.Owner.getOwner(stack, world);
        time = (time + (3600 * 20) - world.getGameTime()) / 20;

        if (time > 0 && owner != null) {
            long hours = time / 3600;
            long minutes = (time % 3600) / 60;
            long seconds = (time % 3600) % 60;

            event.getToolTip().add(new TranslationTextComponent("tooltip.relics.contract", owner.getDisplayName(), hours, minutes, seconds));
        }
    }
}