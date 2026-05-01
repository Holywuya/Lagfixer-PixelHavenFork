package xyz.lychee.lagfixer.commands;

import org.jetbrains.annotations.NotNull;
import xyz.lychee.lagfixer.LagFixer;
import xyz.lychee.lagfixer.managers.CommandManager;
import xyz.lychee.lagfixer.managers.ConfigManager;
import xyz.lychee.lagfixer.managers.ModuleManager;
import xyz.lychee.lagfixer.utils.MessageUtils;
import xyz.lychee.lagfixer.utils.TimingUtil;

public class ReloadCommand extends CommandManager.Subcommand {
    private volatile boolean reload = false;

    public ReloadCommand(CommandManager commandManager) {
        super(commandManager, "reload", "重载所有插件配置");
    }

    @Override
    public void load() {}

    @Override
    public void unload() {}

    @Override
    public boolean execute(@NotNull org.bukkit.command.CommandSender sender, @NotNull String[] args) {
        if (this.reload) {
            return MessageUtils.sendMessage(true, sender, "&7正在重载，请在控制台等待结果！");
        }

        this.reload = true;
        Thread thread = new Thread(() -> {
            TimingUtil t = TimingUtil.startNew();

            LagFixer plugin = this.getCommandManager().getPlugin();
            plugin.sendHeader();

            plugin.reloadConfig();
            try {
                ConfigManager.getInstance().load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            ModuleManager.getInstance().getModules().forEach((clazz, m) -> {
                boolean enabled = m.getConfig().getBoolean(m.getName() + ".enabled");

                try {
                    if (enabled) {
                        if (!m.isLoaded()) {
                            m.load();
                            m.setLoaded(true);
                        }
                        m.loadAllConfig();
                        plugin.getLogger().info("&r&e" + m.getName() + " &r的配置已成功重载！");
                    } else if (m.isLoaded()) {
                        m.disable();
                        m.setLoaded(false);
                        plugin.getLogger().info("&r成功禁用模块 &e" + m.getName() + "&r！");
                    }
                    m.getMenu().updateAll();
                } catch (Exception ex) {
                    plugin.printError(ex);
                    plugin.getLogger().info("&r重载 &c" + m.getName() + "&r 的配置时出错！");
                }
            });

            MessageUtils.sendMessage(true, sender, "&7模块配置已重载，耗时 &f" + t.stop().getExecutingTime() + "&7ms。" +
                    "\n " +
                    "\n &7应用所有更改的有效方法：" +
                    "\n  &8{*} &7服务器重启（&f推荐&7）" +
                    "\n  &8{*} &7所有插件重载，命令：&f/reload confirm" +
                    "\n  &8{*} &7Plugman 重载，命令：&f/plugman reload LagFixer");
            this.reload = false;
        });
        thread.setName("LagFixer Reload");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        return true;
    }
}