package xyz.lychee.lagfixer.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.lychee.lagfixer.managers.CommandManager;
import xyz.lychee.lagfixer.managers.SupportManager;
import xyz.lychee.lagfixer.utils.MessageUtils;

import java.lang.management.ManagementFactory;

public class FreeCommand extends CommandManager.Subcommand {
    private boolean explicitGCDisabled = false;

    public FreeCommand(CommandManager commandManager) {
        super(commandManager, "free", "运行垃圾回收器");
    }

    @Override
    public void load() {
        this.explicitGCDisabled = ManagementFactory.getRuntimeMXBean().getInputArguments().contains("-XX:+DisableExplicitGC");
    }

    @Override
    public void unload() {}

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String[] args) {
        if (this.explicitGCDisabled) {
            MessageUtils.sendMessage(true, sender, "&7无法释放内存，需要移除 JVM 参数：&e&n-XX:+DisableExplicitGC&7！");
            return false;
        }

        SupportManager.getInstance().getFork().runNow(true, null, () -> {
            Runtime runtime = Runtime.getRuntime();

            long before = runtime.totalMemory() - runtime.freeMemory();

            try {
                runtime.gc();
                Thread.sleep(300);
            } catch (InterruptedException ignored) {}

            long after = runtime.totalMemory() - runtime.freeMemory();

            long diff = before - after;
            if (diff <= 0) {
                MessageUtils.sendMessage(true, sender, "&7没有可清理的内存！");
                return;
            }

            long freedMB = diff / (1024 * 1024);
            MessageUtils.sendMessage(true, sender, "&7成功释放 &e" + freedMB + " &7MB 内存。");
        });
        return true;
    }
}