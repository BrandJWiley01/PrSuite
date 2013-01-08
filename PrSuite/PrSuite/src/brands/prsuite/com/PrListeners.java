package brands.prsuite.com;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PrListeners implements Listener {
	
	public PrListeners(JavaPlugin plugin) {
	}
	
	public PrListeners(PrMain plugin) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onSleep(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		player.setHealth(20);
		player.sendMessage(ChatColor.GRAY + "You lay down and rest your head.");
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(player);
		Location loc = player.getLocation();
		World world = loc.getWorld();
		Location tSpawn = (new Location(player.getServer().getWorld("world"), -89, 48, 227));
			tSpawn.setYaw(180);
			tSpawn.setPitch(0);
		if (user.has("PrSuite.NormalSpawn")){
			world.playEffect(loc, Effect.GHAST_SHOOT, 10);
			world.playEffect(loc, Effect.ENDER_SIGNAL, 80);
		} else {
			player.teleport(tSpawn);
			world.playEffect(loc, Effect.GHAST_SHOOT, 10);
			world.playEffect(loc, Effect.ENDER_SIGNAL, 80);
		}
	}
		

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		World world = loc.getWorld();
		world.playEffect(loc, Effect.GHAST_SHOOT, 20);
		world.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 80);
	}

	@EventHandler
	public void PlayerInteractUseDragon(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) && (block != null) && (block.getTypeId() == 122)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.ENDER_DRAGON) {
			if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
		        Player killer = event.getEntity().getKiller();
				String killername = killer.getName();
				killer.getInventory().addItem(new ItemStack(Material.DRAGON_EGG));
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + killername + " slayed the Ender Dragon!");
			}
		}
	}

	@EventHandler
	public void onFountainCreating(EntityCreatePortalEvent event) {
		event.setCancelled(true);
		event.getBlocks().clear();
	}
	
	@EventHandler
	public void onMobDropExp(EntityDeathEvent event) {
		event.setDroppedExp(event.getDroppedExp() / 2);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		event.setExpToDrop(0);
	}
	
	@EventHandler
	public void onZombieSpawn(CreatureSpawnEvent event){
		Location loc = event.getLocation();
		World world = loc.getWorld();
		Random r = new Random();
		int Random = 0;
		Random = r.nextInt(100 - 1 + 1) + 1;
		if(event.getEntityType().equals(EntityType.ZOMBIE)){
			if (Random <= 30) {
				event.setCancelled(true);
				world.spawnEntity(loc, EntityType.PIG_ZOMBIE);
			} else 
			if(Random >= 31) {
				event.setCancelled(false);
			}
		}
	}
	
}
