package me.untouchedodin0.tokens.utils.triggers;

import me.untouchedodin0.tokens.utils.EnchantTrigger;
import me.untouchedodin0.tokens.utils.EventItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author Redempt
 */
public class AttackEntityTrigger extends EnchantTrigger<EntityDamageByEntityEvent> {

    @Override
    protected void register() {
        addListener(EntityDamageByEntityEvent.class, e -> {
            if (!(e.getDamager() instanceof Player)) {
                return null;
            }
            return new EventItems(e, ((Player) e.getDamager()).getInventory().getItemInMainHand());
        });
    }

    @Override
    public EventPriority getPriority() {
        return EventPriority.MONITOR;
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        return type.toString().endsWith("_SWORD");
    }
}