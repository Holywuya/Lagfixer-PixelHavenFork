package xyz.lychee.lagfixer.objects;

import com.sun.management.OperatingSystemMXBean;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import xyz.lychee.lagfixer.LagFixer;
import xyz.lychee.lagfixer.Language;
import xyz.lychee.lagfixer.managers.ModuleManager;
import xyz.lychee.lagfixer.managers.SupportManager;
import xyz.lychee.lagfixer.menu.ConfigMenu;
import xyz.lychee.lagfixer.utils.ItemBuilder;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Getter
@Setter
public class StandardMonitor {
    private double cpuProcess;
    private double cpuSystem;
    private double tps;
    private double mspt;
    private long ramFree;
    private long ramUsed;
    private long ramMax;
    private long ramTotal;
    private BukkitTask task;

    private final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    protected double cpuProcess() {
        return this.osBean.getProcessCpuLoad() * 100.0;
    }

    protected double cpuSystem() {
        return this.osBean.getSystemCpuLoad() * 100.0;
    }

    protected double tps() {
        return SupportManager.getInstance().getNms().getTps();
    }

    protected double mspt() {
        return SupportManager.getInstance().getFork().getMspt();
    }

    public double format(double d) {
        return (double) ((int) (d * 100.0)) / 100.0;
    }

    public long formatBytes(long bytes) {
        return bytes / 1024L / 1024L;
    }

    public void start(int interval) {
        this.task = SupportManager.getInstance().getFork().runTimer(true, () -> {
            this.cpuProcess = this.format(this.cpuProcess());
            this.cpuSystem = this.format(this.cpuSystem());
            this.tps = Math.min(this.format(this.tps()), 20.0);
            this.mspt = this.format(this.mspt());
            Runtime r = Runtime.getRuntime();
            this.ramFree = this.formatBytes(r.freeMemory());
            this.ramUsed = this.formatBytes(r.totalMemory() - r.freeMemory());
            this.ramMax = this.formatBytes(r.maxMemory());
            this.ramTotal = this.formatBytes(r.totalMemory());
        }, 5L, interval, TimeUnit.SECONDS);
    }

    public void stop() {
        if (this.task != null && !this.task.isCancelled()) {
            this.task.cancel();
        }
    }
}