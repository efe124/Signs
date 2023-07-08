package me.efekos.signs.config;

import me.efekos.signs.Signs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class GameConfig {
    private static File file;
    private static FileConfiguration fileConfiguration;

    public static void setup(){
        file = new File(Signs.getPlugin().getDataFolder(),"config.yml");

        if(!file.exists()){
            try{
                Signs.getPlugin().saveResource("config.yml", false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return fileConfiguration;
    }

    public static void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
