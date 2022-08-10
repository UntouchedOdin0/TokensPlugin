package me.untouchedodin0.tokens.enchantment;

import me.untouchedodin0.tokens.Tokens;
import org.bukkit.entity.Player;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.EnchantRegistry;
import redempt.redlib.enchants.trigger.EnchantTrigger;

public class Jackhammer extends CustomEnchant {

    Tokens tokens;
    EnchantRegistry enchantRegistry;

//    public Jackhammer() {
//        super("Jackhammer", 5);
//    }

    public Jackhammer() {
        super("Jackhammer", 1);
        addTrigger(EnchantTrigger.MINE_BLOCK, ((event, integer) -> {
            Player player = event.getPlayer();
            player.sendMessage("Hi");
        }));
    }

    public void onInit() {
        this.tokens = Tokens.tokens;
        this.enchantRegistry = tokens.getEnchantRegistry();
        tokens.getLogger().info(String.format("Registering enchant %s into the registry!", getName()));
    }



//    @Override
//    public void onChat(AsyncPlayerChatEvent event) {
//        //idk
//    }
//
//    @Override
//    public void onBreak(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//        ItemStack itemStack = player.getInventory().getItemInMainHand();
//        Location location = event.getBlock().getLocation();
//        World world = location.getWorld();
//
//        final RandomPattern randomPattern = new RandomPattern();
//
//        boolean canBuild = ProtectionUtils.canBuild(player, location);
//        ProtectedRegion region = ProtectionUtils.getFirstRegion(location);
//        if (!canBuild || region == null) return;
//        if (!ProtectionUtils.isMine(region)) return;
//
//        int minX = region.getMinimumPoint().getBlockX();
//        int minZ = region.getMinimumPoint().getBlockZ();
//        int Y = location.getBlockY();
//        int maxX = region.getMaximumPoint().getBlockX();
//        int maxZ = region.getMaximumPoint().getBlockZ();
//
//        int currentLevel = getLevel(itemStack);
//
//        player.sendMessage("currentLevel: " + currentLevel);
//    }
}
