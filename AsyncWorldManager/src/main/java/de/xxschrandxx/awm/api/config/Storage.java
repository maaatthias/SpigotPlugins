package de.xxschrandxx.awm.api.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;

import de.xxschrandxx.api.minecraft.Config;
import de.xxschrandxx.api.minecraft.message.*;
import de.xxschrandxx.awm.AsyncWorldManager;
import de.xxschrandxx.awm.api.gamerulemanager.Rule;

public class Storage {

  public static void start() {
    //Lade config.yml
    AsyncWorldManager.config = new Config(AsyncWorldManager.getInstance(), "config.yml");
    AsyncWorldManager.messages = new Config(AsyncWorldManager.getInstance(), "messages.yml");
    AsyncWorldManager.config.reload();
    AsyncWorldManager.messages.reload();
    AsyncWorldManager.config.get().options().copyHeader(true);
    AsyncWorldManager.config.get().options().header(AsyncWorldManager.getInstance().getDescription().getFullName() + "");
    AsyncWorldManager.config.get().options().copyDefaults(true);
    List<String> logs = new ArrayList<String>();
    logs.add("INFO");
    logs.add("WARNING");
    AsyncWorldManager.config.get().addDefault("logging.show", logs);
    AsyncWorldManager.config.get().addDefault("logging.debug", false);
    String Mainworldname;
    boolean enablecommandblock;
    try {
      BufferedReader is = new BufferedReader(new FileReader("server.properties"));
      Properties props = new Properties();
      props.load(is);
      is.close();
      Mainworldname = String.valueOf(props.getProperty("level-name"));
      enablecommandblock = Boolean.valueOf(props.getProperty("enable-command-block"));
    }
    catch (IOException | NullPointerException e) {
      enablecommandblock = false;
    	Mainworldname = "world";
    }
    AsyncWorldManager.config.get().addDefault("mainworld", Mainworldname);
    AsyncWorldManager.config.get().addDefault("fastasyncworldedit.faweworld", true);
    AsyncWorldManager.config.get().addDefault("Listener.CreatureSpawn", true);
    AsyncWorldManager.config.get().addDefault("Listener.EntitySpawn", true);
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.main", "wm.command.main");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.create", "wm.command.create");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.import", "wm.command.import");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.delete", "wm.command.delete");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.remove", "wm.command.remove");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.info", "wm.command.info");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.teleport.main", "wm.command.teleport");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.teleport.self", "wm.command.teleport.self");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.teleport.other", "wm.command.teleport.other");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.teleport.bypass", "wm.command.teleport.bypass");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.reload", "wm.command.reload");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.list", "wm.command.list");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.load", "wm.command.load");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.unload", "wm.command.unload");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.main", "wm.command.modify");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.list", "wm.command.modify.list");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.autoload", "wm.command.modify.autoload");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.addname", "wm.command.modify.addname");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.removename", "wm.command.modify.removename");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.modify.setspawn", "wm.command.modify.setspawn");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.plugin.main", "wm.command.plugin.main");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.plugin.info", "wm.command.plugin.info");
    AsyncWorldManager.config.get().addDefault("command.permissions.worldmanager.plugin.set", "wm.command.plugin.set");
    AsyncWorldManager.config.get().addDefault("event.permissions.worldmanager.gamemode.bypass", "wm.event.gamemode.bypass");
    AsyncWorldManager.config.get().addDefault("worldsettings.faweworld", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.autoload", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.autosave", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.seed", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.enviroment", "NORMAL");
    AsyncWorldManager.config.get().addDefault("worldsettings.difficulty", "EASY");
    AsyncWorldManager.config.get().addDefault("worldsettings.pvp", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.generator", "null");
    AsyncWorldManager.config.get().addDefault("worldsettings.worldtype", "NORMAL");
    AsyncWorldManager.config.get().addDefault("worldsettings.generatestructures", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamemode", "SURVIVAL");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.keepinmemory", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.x", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.y", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.z", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.yaw", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawn.pitch", "none");
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.allowanimalspawning", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.allowmonsterspawning", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.ambientlimit", 15);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.animallimit", 15);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.wateranimallimit", 5);
    AsyncWorldManager.config.get().addDefault("worldsettings.spawning.monsterlimit", 70);
    AsyncWorldManager.config.get().addDefault("worldsettings.weather.storm", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.weather.thundering", false);
    for (Rule<?> r : Rule.values()) {
      if (r == null)
        continue;
      if (r.getType() == null || r.getName() == null)
        continue;
      AsyncWorldManager.config.get().addDefault("worldsettings.gamerules." + r.getName(), r.getDefaultValue());
    }
/* Old Gamerules
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.announceadvancements", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.commandblockoutput", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.disableelytramovementcheck", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.disableraids", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.dodaylightcycle", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.doentitydrops", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.dofiretick", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.dolimitedcrafting", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.domobloot", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.domobspawning", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.dotiledrops", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.doweathercycle", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.keepinventory", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.logadmincommands", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.maxcommandchainlength", 65536);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.maxentitycramming", 24);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.mobgriefing", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.naturalregeneration", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.randomtickspeed", 3);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.reduceddebuginfo", false);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.sendcommandfeedback", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.showdeathmessages", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.spawnradius", 10);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.spectatorsgeneratechunks", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.enablecommandblocks", true);
    AsyncWorldManager.config.get().addDefault("worldsettings.gamerules.disabledentitys", new ArrayList<String>());
*/
    AsyncWorldManager.config.save();
    //Lade messages.yml
    AsyncWorldManager.messages.get().options().copyHeader(true);
    AsyncWorldManager.messages.get().options().header("Explenation: https://github.com/xXSchrandXx/Async-WorldManager/wiki/Messages\nCommand description:\n  Required arguments: []\n  Additional arguments: {}\nPlaceholder:\n  Permission: %perm%\n  Worldname: %world%\n  Commandsender: %name%\n  Targetplayer: %player%\n  Foldername: %folder%\n  Autoloadvalue: %autoload%\n  Addedname: %addedname%\n  Removedname: %removedname%\n  Worldaliases: %aliases%\n  Seed: %seed%\n  Enviroment: %enviroment%\n  Generator: %generator%\n  WorldType: %worldtype%\n  Generatestructurevalue: %generatestructurs%\n  X: %x%\n  Y: %y%\n  Z: %z%\n  Yaw: %yaw%\n  Pitch: %pitch%\n  Configkey: %key%\n  Configvalue: %value%");
    AsyncWorldManager.messages.get().options().copyDefaults(true);
    AsyncWorldManager.messages.get().addDefault("prefix", "&8[&6WM&8] &7");
    AsyncWorldManager.messages.get().addDefault("nopermission", "You don't have permission to use that.");
    AsyncWorldManager.messages.get().addDefault("command.AsyncWorldManager.header", "&8&m[]&6&m------------------------WM------------------------&8&m[]");
    AsyncWorldManager.messages.get().addDefault("command.AsyncWorldManager.footer", "&8&m[]&6&m--------------------------------------------------&8&m[]");
    AsyncWorldManager.messages.get().addDefault("command.AsyncWorldManager.hover", "Klick to suggest the command.");
    AsyncWorldManager.messages.get().addDefault("command.create.usage", "Usage: /wm create [worldname] [NORMAL/NETHER/THE_END] {optionals}");
    AsyncWorldManager.messages.get().addDefault("command.create.success.chat", "The world %world% sucsessfully created.");
    AsyncWorldManager.messages.get().addDefault("command.create.success.hover", "Click to teleport into world.");
    AsyncWorldManager.messages.get().addDefault("command.create.folderexist.chat", "The worldfolder already exists.");
    AsyncWorldManager.messages.get().addDefault("command.create.folderexist.hover", "Click to import the world.");
    AsyncWorldManager.messages.get().addDefault("command.create.worldexist.chat", "The world already exists.");
    AsyncWorldManager.messages.get().addDefault("command.create.worldexist.hover", "Click to teleport into world.");
    AsyncWorldManager.messages.get().addDefault("command.import.usage", "Usage: /wm import [worldname] [NORMAL/NETHER/THE_END] {optionals}");
    AsyncWorldManager.messages.get().addDefault("command.import.success.chat", "The world %world% sucsessfully imported.");
    AsyncWorldManager.messages.get().addDefault("command.import.success.hover", "Click to teleport into world.");
    AsyncWorldManager.messages.get().addDefault("command.import.foldernotexist", "The worldfolder does not exists.");
    AsyncWorldManager.messages.get().addDefault("command.import.alreadyimport.chat", "The worldfolder is already imported.");
    AsyncWorldManager.messages.get().addDefault("command.import.alreadyimport.hover", "Clock to load %world%");
    AsyncWorldManager.messages.get().addDefault("command.delete.usage", "Usage: /wm delete [worldname]");
    AsyncWorldManager.messages.get().addDefault("command.delete.success", "%world% successfully deleted.");
    AsyncWorldManager.messages.get().addDefault("command.delete.failed.chat", "%world% does not exist.");
    AsyncWorldManager.messages.get().addDefault("command.delete.failed.hover", "Click to list all worlds.");
    AsyncWorldManager.messages.get().addDefault("command.delete.teleport", "You got teleported into the Main serverworld, because the world %world% got deleted.");
    AsyncWorldManager.messages.get().addDefault("command.remove.usage", "Usage: /wm remove [worldname]");
    AsyncWorldManager.messages.get().addDefault("command.remove.success", "%world% successfully removed.");
    AsyncWorldManager.messages.get().addDefault("command.remove.failed.chat", "%world% does not exist.");
    AsyncWorldManager.messages.get().addDefault("command.remove.failed.hover", "Click to list all worlds.");
    AsyncWorldManager.messages.get().addDefault("command.remove.teleport", "You got teleported into the Main serverworld, because the world %world% got removed.");
    AsyncWorldManager.messages.get().addDefault("command.teleport.console", "Usage:/wp tp [worldname] [player]");
    AsyncWorldManager.messages.get().addDefault("command.teleport.usage", "Usage: /wm tp [worldname] {player}");
    AsyncWorldManager.messages.get().addDefault("command.teleport.success.self", "You successfully teleported yourself into %world%.");
    AsyncWorldManager.messages.get().addDefault("command.teleport.success.other", "You successfully teleported %player% into %world%.");
    AsyncWorldManager.messages.get().addDefault("command.teleport.other", "You got teleported by %player% into %world%.");
    AsyncWorldManager.messages.get().addDefault("command.teleport.worldnotfound", "The world %world% doesn't exist.");
    AsyncWorldManager.messages.get().addDefault("command.teleport.playernotfound", "The player %player% is not online. (Did you write the name right?)");
    AsyncWorldManager.messages.get().addDefault("command.info.usage", "Usage: /wm info [worldname]");
    AsyncWorldManager.messages.get().addDefault("command.info.worldnotfound", "The world %world% does not exist.");
    AsyncWorldManager.messages.get().addDefault("command.info.worldnotinconfig.chat", "The world %world% does not exist in the plugins config.");
    AsyncWorldManager.messages.get().addDefault("command.info.worldnotinconfig.hover", "Click to suggest the importation of the world.");
    AsyncWorldManager.messages.get().addDefault("command.info.worldinfo", "&8&m[]&6&m-----------------------INFO-----------------------&8&m[]\n&7 | Fodler: &7%folder%\n&7 | Autoload: &7%autoload%\n&7 | Names: &7%aliases%\n&7 | Enviroment: &7%enviroment%\n&7 | Seed: &7%seed%\n&7 | Generator: &7%generator%\n&7 | Worldtype: &7%worldtype%\n&7 | Geneatestructurs: &7%generatestructurs%\n&7 | Spawnlocation:\n&7 |   X: %x%\n&7 |   Y: %y%\n&7 |   Z: %z%\n&7 |   Yaw: %yaw%\n&7 |   Pitch: %pitch%\n&8&m[]&6&m-----------------------INFO-----------------------&8&m[]");
    AsyncWorldManager.messages.get().addDefault("command.list.aliases", ", ");
    AsyncWorldManager.messages.get().addDefault("command.list.usage", "Usage: /wm list");
    AsyncWorldManager.messages.get().addDefault("command.list.loaded", "&a");
    AsyncWorldManager.messages.get().addDefault("command.list.unloaded", "&c");
    AsyncWorldManager.messages.get().addDefault("command.list.unknown", "&7&o");
    AsyncWorldManager.messages.get().addDefault("command.list.main", "Worlds:");
    AsyncWorldManager.messages.get().addDefault("command.list.hover", "Click to teleport into world.");
    AsyncWorldManager.messages.get().addDefault("command.list.chat", "- ");
    AsyncWorldManager.messages.get().addDefault("command.load.usage", "Usage: /wm load [world]");
    AsyncWorldManager.messages.get().addDefault("command.load.success.chat", "The world %world% loaded.");
    AsyncWorldManager.messages.get().addDefault("command.load.success.hover", "The world %world% loaded.");
    AsyncWorldManager.messages.get().addDefault("command.load.fail", "The world %world% failed to load.");
    AsyncWorldManager.messages.get().addDefault("command.load.alreadyloaded.chat", "The world %world% is already loaded.");
    AsyncWorldManager.messages.get().addDefault("command.load.alreadyloaded.hover", "Click to teleport into world.");
    AsyncWorldManager.messages.get().addDefault("command.load.worldnotfound", "The world %world% does not exist.");
    AsyncWorldManager.messages.get().addDefault("command.unload.usage", "Usage: /wm unload [world]");
    AsyncWorldManager.messages.get().addDefault("command.unload.success", "The world %world% got unloaded.");
    AsyncWorldManager.messages.get().addDefault("command.unload.teleport", "You got teleported into the Main serverworld, because the world %world% got unloaded.");
    AsyncWorldManager.messages.get().addDefault("command.unload.failed.chat", "%world% does not exist.");
    AsyncWorldManager.messages.get().addDefault("command.unload.failed.hover", "Click to list all worlds.");
    AsyncWorldManager.messages.get().addDefault("command.modify.usage", "Usage: /wm modify [list/world] [modifier] [value]");
    AsyncWorldManager.messages.get().addDefault("command.modify.list", "&8&m[]&6&m-----------------------LIST-----------------------&8&m[]\n&7 | Usage:\n&7 | - /wm modify [world] addalias [alias]\n&7 | - /wm modify [world] removealias [alis]\n&7 | - wm modify [world] autoload [true/false]\n&7 | - wm modify [world] autosave [true/false]\n&7 | - wm modify [world] difficulty [PEACEFUL/EASY/NORMAL/HARD]\n&7 | - wm modify [world] allowmonsterspawning [true/false]\n&7 | - wm modify [world] allowanimalspawning [true/false]\n&7 |  -/wm modify [world] ambientspawnlimit [Number]\n&7 |  -/wm modify [world] animalspawnlimit [Number]\n&7 |  -/wm modify [world] monsterspawnlimit [Number]\n&7 |  -/wm modify [world] wateranimalspawnlimit [Number]\n&7 | - wm modify [world] storm [true/false]\n&7 | - wm modify [world] thunder [true/false]\n&7 | - wm modify [world] keepspawninmemory [true/false]\n&7 |  -/wm modify [world] x [Number]\n&7 |  -/wm modify [world] y [Number]\n&7 |  -/wm modify [world] z [Number]\n&7 |  -/wm modify [world] yaw [Number]\n&7 |  -/wm modify [world] pitch [Number]\n&7 | - wm modify [world] announceadvancements [true/false]\n&7 | - wm modify [world] commandblockoutput [true/false]\n&7 | - wm modify [world] disableelytramovementcheck [true/false]\n&7 | - wm modify [world] dodaylightcycle [true/false]\n&7 | - wm modify [world] doentitydrops [true/false]\n&7 | - wm modify [world] dofiretick [true/false]\n&7 | - wm modify [world] dolimitedcrafting [true/false]\n&7 | - wm modify [world] domobloot [true/false]\n&7 | - wm modify [world] domobspawning [true/false]\n&7 | - wm modify [world] dotiledrops [true/false]\n&7 | - wm modify [world] doweatherchange [true/false]\n&7 | - wm modify [world] keepinventory [true/false]\n&7 | - wm modify [world] logadmincommands [true/false]\n&7 | - wm modify [world] maxcommandchainlength [Number]\n&7 | - wm modify [world] maxentitycramming [Number]\n&7 | - wm modify [world] naturalregeneration [true/false]\n&7 | - wm modify [world] randomtickspeed [Number]\n&7 | - wm modify [world] reduceddebuginfo [true/false]\n&7 | - wm modify [world] sendcommandfeedback [true/false]\n&7 | - wm modify [world] showdeathmessages [true/false]\n&7 | - wm modify [world] spawnradius [Number]\n&7 | - wm modify [world] spectatorsgeneratechunks [true/false]\n&8&m[]&6&m-----------------------LIST-----------------------&8&m[]");
    AsyncWorldManager.messages.get().addDefault("command.modify.worldnotfound.chat", "The world %world% doesn't exist.");
    AsyncWorldManager.messages.get().addDefault("command.modify.worldnotfound.hover", "Click to import world.");
    AsyncWorldManager.messages.get().addDefault("command.modify.worldnotload.chat", "The world %world% isn't laoded.");
    AsyncWorldManager.messages.get().addDefault("command.modify.worldnotload.hover", "Click to load world.");
    AsyncWorldManager.messages.get().addDefault("command.modify.world.success", "You successfully set %key% to %value%.");
    AsyncWorldManager.messages.get().addDefault("command.modify.world.usage", "Usage: /wm modify %world% %key% [%value%]");
    AsyncWorldManager.messages.get().addDefault("command.modify.world.alias.alreadyalias", "%value% is already a alias of %key%.");
    AsyncWorldManager.messages.get().addDefault("command.modify.world.alias.notalias", "%value% is not a alias of %key%.");
    AsyncWorldManager.messages.get().addDefault("command.reload.usage", "Usage: /wm reload");
    AsyncWorldManager.messages.get().addDefault("command.reload.success", "Reloaded AsyncWorldManager.config.yml, AsyncWorldManager.messages.yml and worldconfigs.");
    AsyncWorldManager.messages.get().addDefault("command.plugin.usage", "/wm plugin [info/set] [config/AsyncWorldManager.messages(/worlds)] {path} {value}");
    AsyncWorldManager.messages.get().addDefault("command.plugin.info.head", "&8&m[]&6&m------------------------WM------------------------&8&m[]");
    AsyncWorldManager.messages.get().addDefault("command.plugin.info.format", "&7%key%: %value%");
    AsyncWorldManager.messages.get().addDefault("command.plugin.set.success", "You successfully set %key% to %value%.");
    AsyncWorldManager.messages.get().addDefault("command.plugin.set.failure", "A error applied while setting %key% to %value%. Please have a look into your console.");
    AsyncWorldManager.messages.get().addDefault("command.plugin.set.usage", "/wm plugin [set] [config/Main.messages] [path] [value]");
    AsyncWorldManager.messages.save();
    
    AsyncWorldManager.mh = new MessageHandler(
        AsyncWorldManager.messages.get().getString("prefix"),
        AsyncWorldManager.messages.get().getString("command.AsyncWorldManager.header"),
        AsyncWorldManager.messages.get().getString("command.AsyncWorldManager.footer"),
        AsyncWorldManager.config.get().getBoolean("logging.debug"),
        AsyncWorldManager.config.get().getStringList("logging.show"));
    
    if (!enablecommandblock) {
      AsyncWorldManager.getLogHandler().log(false, Level.INFO, "EnableCommandBlocks will not work if 'enable-command-block' in server.properties is on 'false'.");
    }
  }
  public static void stop() {
    File worldconfigfolder = new File(AsyncWorldManager.getInstance().getDataFolder(), "worldconfigs");
    if (!worldconfigfolder.exists())
      worldconfigfolder.mkdir();
  }
  public static ArrayList<WorldData> loadAllWorlddatas() {
    ArrayList<WorldData> list = new ArrayList<WorldData>();
    File worldconfigfolder = new File(AsyncWorldManager.getInstance().getDataFolder(), "worldconfigs");
    if (!worldconfigfolder.exists())
      worldconfigfolder.mkdir();
    for (File worldconfigfile : worldconfigfolder.listFiles()) {
      Config config = new Config(worldconfigfile);
      WorldData worlddata = WorldConfigManager.getWorlddataFromConfig(config);
      list.add(worlddata);
    }
    return list;
  }
  public static WorldData getWorlddataFromName(String name) {
    WorldData worlddata = null;
    for (WorldData testworlddata : loadAllWorlddatas()) {
      if (testworlddata.getWorldName().equals(name)) {
        worlddata = testworlddata;
      }
    }
    return worlddata;
  }
  public static WorldData getWorlddataFromAlias(String alias) {
    WorldData worlddata = null;
    for (WorldData testworlddata : loadAllWorlddatas()) {
      if (testworlddata.getWorldName().equals(alias))
        worlddata = testworlddata;
      if (testworlddata.getAliases().contains(alias))
        worlddata = testworlddata;
    }
    return worlddata;
  }
  public static void loadworlds() {
    for (WorldData worlddata : loadAllWorlddatas()) {
      if (worlddata.getAutoLoad()) {
        AsyncWorldManager.getLogHandler().log(false, Level.WARNING, "Loading world: " + worlddata.getWorldName());
        WorldConfigManager.createWorld(worlddata);
      }
    }
    String mainworldname = AsyncWorldManager.config.get().getString("mainworld");
    World mainworld = Bukkit.getWorld(mainworldname);
    if (mainworld == null)
      return;
    WorldData worlddata = getWorlddataFromName(mainworldname);
    if (worlddata == null) {
      worlddata = WorldConfigManager.getWorlddataFromWorld(mainworld);
    }
    if (worlddata != null) {
      WorldConfigManager.setWorldsData(mainworld, worlddata);
      File worldconfigfolder = new File(AsyncWorldManager.getInstance().getDataFolder(), "worldconfigs");
      File worldconfigfile = new File(worldconfigfolder, worlddata.getWorldName() + ".yml");
      Config worldconfig = new Config(worldconfigfile);
      WorldConfigManager.save(worldconfig, worlddata);
    }
  }
  public static void setallworlddatas() {
    for (World world : Bukkit.getWorlds()) {
      WorldData worlddata = getWorlddataFromName(world.getName());
      if (worlddata != null) {
        AsyncWorldManager.getLogHandler().log(false, Level.WARNING, "Setting up world: " + worlddata.getWorldName());
        WorldConfigManager.setWorldsData(world, worlddata);
      }
    }
  }
  public static List<String> getAllUnknownWorlds() {
    List<String> list = new ArrayList<String>();
    for (String worldname : Bukkit.getWorldContainer().list()) {
      if (getWorlddataFromName(worldname) == null) {
        list.add(worldname);
      }
    }
    return list;
  }
  public static List<String> getAllKnownWorlds() {
    List<String> list = new ArrayList<String>();
    for (String worldname : Bukkit.getWorldContainer().list()) {
      if (getWorlddataFromName(worldname) != null) {
        list.add(worldname);
      }
    }
    return list;
  }
  public static List<String> getAllUnloadedWorlds() {
    List<String> list = new ArrayList<String>();
    for (String worldname : getAllKnownWorlds()) {
      if (Bukkit.getWorld(worldname) == null) {
        list.add(worldname);
      }
    }
    return list;
  }
  public static List<String> getAllLoadedWorlds() {
    List<String> list = new ArrayList<String>();
    for (String worldname : getAllKnownWorlds()) {
      if (Bukkit.getWorld(worldname) != null) {
        list.add(worldname);
      }
    }
    return list;
  }
  public static Config getWorldConfig(String name) {
    Config worldconfig = null;
    File worldconfigfolder = new File(AsyncWorldManager.getInstance().getDataFolder(), "worldconfigs");
    if (!worldconfigfolder.exists())
      worldconfigfolder.mkdir();
    for (File worldconfigfile : worldconfigfolder.listFiles()) {
      if (worldconfigfile.getName().equals(name + ".yml")) {
        worldconfig = new Config(worldconfigfile);
      }
    }
    return worldconfig;
  }
}
