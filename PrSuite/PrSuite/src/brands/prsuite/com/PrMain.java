package brands.prsuite.com;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PrMain extends JavaPlugin implements Listener {
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PrListeners(this), this);
		
		ShapedRecipe web = new ShapedRecipe(new ItemStack(Material.WEB, 1));
		web.shape("SSS", "SSS", "SSS");
		web.setIngredient('S', Material.STRING);
		
		ShapedRecipe netherbrick = new ShapedRecipe(new ItemStack(Material.NETHER_BRICK, 2));
		netherbrick.shape("NNN", "NNN", "NNN");
		netherbrick.setIngredient('N', Material.NETHERRACK);
		
		ShapedRecipe mosscobble = new ShapedRecipe(new ItemStack(Material.MOSSY_COBBLESTONE, 1));
		mosscobble.shape("MMM", "MCM", "MMM");
		mosscobble.setIngredient('M', Material.VINE);
		mosscobble.setIngredient('C', Material.COBBLESTONE);
		
		ShapedRecipe mossstonebrick = new ShapedRecipe(new ItemStack(Material.SMOOTH_BRICK, 1, (byte)1));
		mossstonebrick.shape("MMM", "MBM", "MMM");
		mossstonebrick.setIngredient('M', Material.VINE);
		mossstonebrick.setIngredient('B', Material.SMOOTH_BRICK);
		
		ShapedRecipe crackstonebrick = new ShapedRecipe(new ItemStack(Material.SMOOTH_BRICK, 1, (byte)2));
		crackstonebrick.shape("FFF", "FBF", "FFF");
		crackstonebrick.setIngredient('F', Material.FLINT);
		crackstonebrick.setIngredient('B', Material.SMOOTH_BRICK);
		
		ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE, 1));
		saddle.shape("ILI", "L L", "S S");
		saddle.setIngredient('L', Material.LEATHER);
		saddle.setIngredient('I', Material.IRON_INGOT);
		saddle.setIngredient('S', Material.STRING);
		
		ShapedRecipe skelehead = new ShapedRecipe(new ItemStack(Material.SKULL_ITEM));
		skelehead.shape("III", "GIG", "IGI");
		skelehead.setIngredient('I', Material.IRON);
		skelehead.setIngredient('G', Material.GRAVEL);
		
		ShapedRecipe sponge = new ShapedRecipe(new ItemStack(Material.SPONGE));
		sponge.shape("LLL", "LYL", "LLL");
		sponge.setIngredient('L', Material.LEAVES);
		sponge.setIngredient('Y', Material.INK_SACK);
				
				
		getServer().addRecipe(web);
		getServer().addRecipe(netherbrick);
		getServer().addRecipe(mosscobble);
		getServer().addRecipe(mossstonebrick);
		getServer().addRecipe(crackstonebrick);
		getServer().addRecipe(saddle);
		getServer().addRecipe(skelehead);
		getServer().addRecipe(sponge);
		
		File dir = getDataFolder();
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sing")) {
			if (args.length == 0) {
		        sender.sendMessage(ChatColor.RED + "Please specify a player name!");
		    } else if (args.length == 1) {
		        if (sender.getServer().getPlayer(args[0]) != null) {
		        	Player targetplayer = sender.getServer().getPlayer(args[0]);
		        	Location tloc = targetplayer.getLocation();
		        	targetplayer.getWorld().playSound(tloc, Sound.ENDERMAN_STARE, 1, 0);
		        	sender.sendMessage(ChatColor.LIGHT_PURPLE + "You sing a song to " + targetplayer.getDisplayName());
		    } else {
		        sender.sendMessage(ChatColor.RED + "That player is not online!");
		        }
		    } else if (args.length > 1) {
		        sender.sendMessage(ChatColor.RED + "Too Many Arguments!");
		        }
		    }
		
		if (cmd.getName().equalsIgnoreCase("attackdog")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please specify a player.");
			}
			if (args.length == 1) {
		        if (sender.getServer().getPlayer(args[0]) != null) {
		        	Player targetplayer = sender.getServer().getPlayer(args[0]);
		        	Wolf e = (Wolf)targetplayer.getWorld().spawnEntity(targetplayer.getLocation(), EntityType.WOLF);
		        	e.setAngry(true);
		        	e.damage(0, targetplayer);
		        	sender.sendMessage(ChatColor.GRAY + "Target is now dealing with an attack dog!");
		        } else {
		        	sender.sendMessage(ChatColor.RED + "That player is not online.");
		        }
			}
			if (args.length > 1) {
				sender.sendMessage(ChatColor.RED + "Too Many Arguments!");
			}
		}
		
		return true;
	}
}
