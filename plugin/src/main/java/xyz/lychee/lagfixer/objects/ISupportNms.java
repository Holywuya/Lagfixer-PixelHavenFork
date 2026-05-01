package xyz.lychee.lagfixer.objects;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ISupportNms {
    double getTps();

    ItemStack createSkull(String base64);

    int getTileEntitiesCount(Chunk chunk);

    int getPlayerPing(Player player);
}