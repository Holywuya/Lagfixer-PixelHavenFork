package xyz.lychee.lagfixer.objects;

import com.sun.management.OperatingSystemMXBean;
import lombok.Getter;
import lombok.Setter;
import xyz.lychee.lagfixer.managers.SupportManager;

import java.lang.management.ManagementFactory;

@Getter
@Setter
public class MXBeanMonitor extends AbstractMonitor {
    private final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    @Override
    public double cpuProcess() {
        return this.osBean.getProcessCpuLoad() * 100.0;
    }

    @Override
    public double cpuSystem() {
        return this.osBean.getSystemCpuLoad() * 100.0;
    }

    @Override
    public double tps() {
        return SupportManager.getInstance().getNms().getTps();
    }

    @Override
    public double mspt() {
        return SupportManager.getInstance().getFork().getMspt();
    }
}