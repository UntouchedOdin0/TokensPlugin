package me.untouchedodin0.tokens.utils.triggers;

import me.untouchedodin0.tokens.utils.EnchantTrigger;
import me.untouchedodin0.tokens.utils.EventItems;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillEntityTrigger extends EnchantTrigger<EntityDeathEvent> {

    @Override
    protected void register() {
        addListener(EntityDeathEvent.class, e -> {
            if (e.getEntity().getKiller() == null) {
                return null;
            }
            return new EventItems(e, e.getEntity().getKiller().getItemInHand());
        });
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        return type.toString().endsWith("_SWORD");
    }
}