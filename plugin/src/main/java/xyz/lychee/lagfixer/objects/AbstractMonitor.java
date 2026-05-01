package xyz.lychee.lagfixer.objects;

public interface IMonitor {
    double cpuProcess();
    double cpuSystem();
    double tps();
    double mspt();
    void start(int interval);
    void stop();
}