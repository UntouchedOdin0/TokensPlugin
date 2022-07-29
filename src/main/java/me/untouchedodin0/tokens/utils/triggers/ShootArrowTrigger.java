package me.untouchedodin0.tokens.utils.triggers;

import me.untouchedodin0.tokens.utils.EnchantTrigger;
import me.untouchedodin0.tokens.utils.EventItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ShootArrowTrigger extends EnchantTrigger<ProjectileLaunchEvent> {

    @Override
    protected void register() {
        addListener(ProjectileLaunchEvent.class, e -> {
            if (!(e.getEntity().getShooter() instanceof Player)) {
                return null;
            }
            return new EventItems(e, ((Player) e.getEntity().getShooter()).getItemInHand());
        });
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        return type == Material.BOW;
    }
}