package me.efekos.signs.commands;

import me.efekos.signs.Signs;
import me.efekos.signs.config.GameConfig;
import me.efekos.signs.config.LangConfig;
import me.efekos.signs.utils.Glow;
import me.efekos.simpler.annotations.Command;
import me.efekos.simpler.commands.BaseCommand;
import me.efekos.simpler.commands.syntax.Syntax;
import me.efekos.simpler.commands.translation.TranslateManager;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Command(name = "sign",description = "Sign an item!",playerOnly = true,permission = "signs.sign")
public class sign extends BaseCommand {
    public sign(@NotNull String name) {
        super(name);
    }

    public sign(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public @NotNull Syntax getSyntax() {
        return new Syntax();

    }

    @Override
    public void onPlayerUse(Player player, String[] args) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType().equals(Material.AIR)) {
            player.sendMessage(TranslateManager.translateColors(LangConfig.get("no-item")));
            return;
        }

        if(GameConfig.get().getStringList("blocked-items").contains(item.getType().toString())){
            player.sendMessage(TranslateManager.translateColors(LangConfig.get("not-signable")));
            return;
        }

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if(container.has(Signs.key, PersistentDataType.STRING)){
            player.sendMessage(TranslateManager.translateColors(LangConfig.get("already-signed").replace("%player%", container.get(Signs.key, PersistentDataType.STRING))));
            return;
        }

        container.set(Signs.key,PersistentDataType.STRING,player.getName());

        List<String> list = meta.getLore();
        if(list==null) list = new ArrayList<>();
        list.add(TranslateManager.translateColors(LangConfig.get("signed-by").replace("%player%",player.getName())));
        meta.setLore(list);

        if(GameConfig.get().getBoolean("enchanted-sign"))meta.addEnchant(new Glow(),1,true);

        item.setItemMeta(meta);

        player.getInventory().setItemInMainHand(item);

        player.sendMessage(TranslateManager.translateColors(LangConfig.get("signed")));
    }

    @Override
    public void onConsoleUse(ConsoleCommandSender sender, String[] args) {

    }
}
