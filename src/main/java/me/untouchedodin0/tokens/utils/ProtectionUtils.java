package me.untouchedodin0.tokens.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ProtectionUtils {

    public static boolean canBuild(@NotNull final Player player, @NotNull final Location location) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        return query.testState(BukkitAdapter.adapt(location), localPlayer, Flags.BLOCK_BREAK);
    }

    public static ApplicableRegionSet getProtectedRegion(@NotNull final Location location) {
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = regionContainer.createQuery();
        return query.getApplicableRegions(BukkitAdapter.adapt(location));
    }

    public static ProtectedRegion getFirstRegion(@NotNull final Location location) {
        if (getProtectedRegion(location).getRegions().stream().findFirst().isPresent()) {
            return getProtectedRegion(location).getRegions().stream().findFirst().get();
        }
        return null;
    }

    public static boolean isMine(ProtectedRegion protectedRegion) {
        return protectedRegion.getId().startsWith("mine-");
    }
}