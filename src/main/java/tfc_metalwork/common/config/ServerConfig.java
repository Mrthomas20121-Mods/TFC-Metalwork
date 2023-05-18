package tfc_metalwork.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Function;

public class ServerConfig {

    public ForgeConfigSpec.IntValue blockTicks;

    protected ServerConfig(ForgeConfigSpec.Builder innerBuilder) {

        Function<String, ForgeConfigSpec.Builder> builder = (name) -> innerBuilder.translation("tfc_metalwork.config.server." + name);
        blockTicks = (builder.apply("blockTicks")).comment("Number of ticks required for a metal block to oxidize (1000 = 1 in game hour = 50 seconds), default is 24 hours. Set to -1 to disable metal block oxidation.").defineInRange("blockTicks", 24000, -1, Integer.MAX_VALUE);
    }
}
