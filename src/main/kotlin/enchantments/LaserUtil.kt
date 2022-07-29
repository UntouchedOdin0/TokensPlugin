package enchantments

import com.sk89q.worldedit.math.BlockVector3
import me.untouchedodin0.tokens.utils.ProtectionUtils
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player

fun getBlocksX(center: Location, radius: Int): Set<Block> {
    val block = center.block
    return (1..radius).flatMap {
        setOf(
            block.getRelative(BlockFace.NORTH_EAST, it),
            block.getRelative(BlockFace.NORTH_WEST, it),
            block.getRelative(BlockFace.SOUTH_EAST, it),
            block.getRelative(BlockFace.SOUTH_WEST, it)
        )
    }.toSet() + block
}

fun toBlockVector3Set(player: Player, blocks: Set<Block>): Set<BlockVector3> {
    val updated: MutableSet<BlockVector3> = mutableSetOf()
    blocks.forEach {
        val location = it.location
        if (ProtectionUtils.canBuild(player, location)) {
            updated += BlockVector3.at(it.x, it.y, it.z)
        }
    }
    return updated
}