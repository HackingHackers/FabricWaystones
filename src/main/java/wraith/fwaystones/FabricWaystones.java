package wraith.fwaystones;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wraith.fwaystones.integration.pinlib.PinlibPlugin;
import wraith.fwaystones.registry.*;
import wraith.fwaystones.util.FWConfig;
import wraith.fwaystones.util.WaystoneStorage;
import wraith.fwaystones.util.WaystonesEventManager;

import java.io.File;

public class FabricWaystones implements ModInitializer {

    public static final FWConfig CONFIG;
    public static final Logger LOGGER = LogManager.getLogger("Fabric-Waystones");
    public static final String MOD_ID = "fwaystones";
    public static WaystoneStorage WAYSTONE_STORAGE;

    static {
        var configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "fwaystones/config.json");
        if (configFile.exists()) {
            FabricWaystones.LOGGER.info("Old config file found, migrating...");
            configFile.renameTo(new File(FabricLoader.getInstance().getConfigDir().toFile(), "fwaystones/config.json5"));
        }
        CONFIG = FWConfig.createAndLoad();
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Is initializing.");
        LOGGER.info(" _    _____   _____   _   _ _  _ _____   __");
        LOGGER.info(" | |  / _ \\ \\ / / __| | | | | \\| |   \\ \\ / /");
        LOGGER.info(" | |_| (_) \\ V /| _|  | |_| | .` | |) \\ V /");
        LOGGER.info(" |____\\___/ \\_/ |___|  \\___/|_|\\_|___/ |_| ");
        LOGGER.info("                                           ");
        BlockRegistry.registerBlocks();
        BlockEntityRegistry.registerBlockEntities();
        ItemRegistry.init();
        CompatRegistry.init();
        WaystonesEventManager.registerEvents();

        if (FabricLoader.getInstance().isModLoaded("pinlib")) {
            PinlibPlugin.init();
        }

        LOGGER.info("Has successfully been initialized.");
    }

}
