package de.xxschrandxx.awm.api.worldcreation;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.util.TaskManager;

import de.xxschrandxx.api.minecraft.Config;
import de.xxschrandxx.awm.AsyncWorldManager;
import de.xxschrandxx.awm.api.config.WorldConfigManager;
import de.xxschrandxx.awm.api.config.WorldData;
import de.xxschrandxx.awm.api.event.PreWorldCreateEvent;
import de.xxschrandxx.awm.api.event.WorldCreateEvent;

public class fawe {
  /**
   * Creates a World as {@link AsyncWorld}.
   * @param preworlddata The {@link WorldData} to use.
   */
  public static void faweworld(WorldData preworlddata) {
    TaskManager.IMP.async( new Runnable() {
      public void run() {
        WorldCreator preworldcreator = new WorldCreator(preworlddata.getWorldName());
        PreWorldCreateEvent preworldcreateevent = new PreWorldCreateEvent(preworlddata, true);
        Bukkit.getPluginManager().callEvent(preworldcreateevent);
        if (preworldcreateevent.isCancelled()) {
          return;
        }
        WorldData worlddata = preworldcreateevent.getWorldData();
        if (Bukkit.getWorld(preworldcreator.name()) == null) {
          preworldcreator.environment(worlddata.getEnviroment());
          preworldcreator.seed(worlddata.getSeed());
          preworldcreator.generator(worlddata.getGenerator());
          preworldcreator.type(worlddata.getWorldType());
          preworldcreator.generateStructures(worlddata.getGenerateStructures());
        }
        WorldCreateEvent worldcreateevent = new WorldCreateEvent(preworldcreator, true);
        Bukkit.getPluginManager().callEvent(worldcreateevent);
        if (worldcreateevent.isCancelled()) {
          return;
        }
        File worldconfigfolder = new File(AsyncWorldManager.getInstance().getDataFolder(), "worldconfigs");
        if (!worldconfigfolder.exists())
          worldconfigfolder.mkdir();
        File worldconfigfile = new File(worldconfigfolder, worlddata.getWorldName() + ".yml");
        Config config = new Config(worldconfigfile);
        WorldConfigManager.save(config, worlddata);

        WorldCreator worldcreator = worldcreateevent.getWorldCreator();
        if (Bukkit.getWorld(worldcreator.name()) == null) {
          AsyncWorld.create(worldcreator);
        } else {
          AsyncWorld.wrap(Bukkit.getWorld(worldcreator.name()));
        }
      }
    });
  }
}
