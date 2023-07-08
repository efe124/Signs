package me.efekos.signs.config;

import me.efekos.signs.Signs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangConfig {
    private static File file;
    private static FileConfiguration fileConfiguration;

    public static void setup(){
        file = new File(Signs.getPlugin().getDataFolder(),"lang.yml");

        if(!file.exists()){
            try{
                Signs.getPlugin().saveResource("lang.yml", false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static String get(String path) {
        return fileConfiguration.getString(path)!=null?fileConfiguration.getString(path):path;
    }

    public static void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
