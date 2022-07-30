package me.untouchedodin0.tokens.utils.triggers;

//import me.untouchedodin0.tokens.utils.EnchantTrigger;
//import me.untouchedodin0.tokens.utils.EventItems;
//import org.bukkit.Material;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerQuitEvent;
//import redempt.redlib.enchants.events.PlayerChangedHeldItemEvent;
//
//public class HoldItemTrigger extends EnchantTrigger<PlayerChangedHeldItemEvent> {
//
//    @Override
//    protected void register() {
//        addListener(PlayerChangedHeldItemEvent.class, e -> new EventItems(e, e.getPreviousItem(), e.getNewItem()));
//        addListener(PlayerJoinEvent.class, e ->
//                new EventItems(new PlayerChangedHeldItemEvent(e.getPlayer(), null, e.getPlayer().getItemInHand()), null, e.getPlayer().getItemInHand()));
//        addListener(PlayerQuitEvent.class, e ->
//                new EventItems(new PlayerChangedHeldItemEvent(e.getPlayer(), e.getPlayer().getItemInHand(), null), e.getPlayer().getItemInHand(), null));
//    }
//
//    @Override
//    public boolean defaultAppliesTo(Material type) {
//        String str = type.toString();
//        return str.endsWith("_PICKAXE");
//    }
//}