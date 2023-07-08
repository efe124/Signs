package me.efekos.signs;

import me.efekos.signs.commands.sign;
import me.efekos.signs.config.GameConfig;
import me.efekos.signs.config.LangConfig;
import me.efekos.signs.utils.Glow;
import me.efekos.signs.utils.Logger;
import me.efekos.simpler.commands.CommandManager;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class Signs extends JavaPlugin {

    private static Signs plugin;

    public static Signs getPlugin() {
        return plugin;
    }

    public static NamespacedKey key;
    public static NamespacedKey glowKey;

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Glow glow = new Glow(glowKey);
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException ignored){}
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        try {
            Logger.info("Starting plugin");
            plugin = this;
            key = new NamespacedKey(this,"signed_by");
            glowKey = new NamespacedKey(this,"glow");
            registerGlow();

            Logger.log("Loading config");
            LangConfig.setup();
            GameConfig.setup();

            Logger.log("Loading commands");
            CommandManager.registerBaseCommand(this, sign.class);
        } catch (Exception e){
            Logger.error("An error occoured while trying to load the plugin:");
            e.printStackTrace();

            Logger.error("Disabling plugin");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
    }


}
