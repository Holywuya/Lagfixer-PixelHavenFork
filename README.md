![logo](https://i.imgur.com/hElpNHD.png)

**LagFixer** 是一款极致性能优化的 Minecraft 插件，旨在优化服务器并消除
不必要的延迟。通过微调服务器的各项参数并精简冗余功能，LagFixer 确保所有玩家
获得更流畅、更愉快的游戏体验。

## 运行要求：

- Java 8 或更高版本
- 服务端版本 1.13.2 - 26.1.2

## 支持版本：

- 1.16.5, 1.17.1, 1.18.2, 1.19.4, 1.20-26.1.2
- 大多数模块支持更广泛的版本范围 [1.16.5 - 26.1.2]
- 基于 Forge 的 Spigot 分支：[Mohist](https://mohistmc.com/)、[Arclight](https://github.com/IzzelAliz/Arclight) 等

<details>
<summary>支持的插件</summary>

- [PlaceholderAPI](https://www.spigotmc.org/resources/6245/)
- [WildStacker](https://bg-software.com/wildstacker/)
- [UltimateStacker](https://songoda.com/product/16)
- [RoseStacker](https://www.spigotmc.org/resources/82729/)
- [LevelledMobs](https://www.spigotmc.org/resources/74304/)
- [Spark](https://spark.lucko.me/download)

</details>

<details>
<summary>变量占位符</summary>

- %lagfixer_tps% - 当前每秒刻数（TPS）
- %lagfixer_mspt% - 当前每刻毫秒数（MSPT）
- %lagfixer_cpuprocess% - 当前进程 CPU 使用率
- %lagfixer_cpusystem% - 当前系统 CPU 使用率
- %lagfixer_worldcleaner% - 世界清理倒计时

</details>

<details>
<summary>命令</summary>

- /lagfixer - 插件主命令
- /abyss - 被删除物品的存放处

</details>

## 下载地址：

- [Modrinth](https://modrinth.com/plugin/lagfixer) -（推荐使用此渠道获取最新版本）
- [SpigotMC](https://www.spigotmc.org/resources/lagfixer-1-13-1-21-10-%E2%9A%A1%EF%B8%8Fbest-performance-solution-%EF%B8%8F-2100-servers-%E2%9C%85-lightweight-and-asynchronous.111684/)

## 模块：

### ⭐ MobAiReducer（性能影响：极高）

- 替换生物移动逻辑以优化和减少行为运算。
- 解决默认动物行为带来的低效问题，如不必要的随机移动或持续四处张望。
- LagFixer 通过禁用不必要的寻路器（PathFinder）或替换为更高效的寻路器来干预。
- 在拥有大量生物的场景中至关重要，因为即使是微小的移动也会消耗服务器资源。

### ⭐ ItemsCleaner（性能影响：中等）

- 清理地面上的过期物品以提升服务器性能。
- 物品随时间累积会导致服务器延迟，尤其在人口密集或活跃的服务器上更为明显。
- 及时清除多余物品以减轻服务器负担。
- 玩家可通过 /abyss 命令从深渊背包中取回物品。

### ⭐ EntityLimiter（性能影响：高）

- 限制每个区块的实体数量。
- 对拥有大型动物农场的生存服务器至关重要。
- 防止实体过度累积及由此引发的性能问题。
- 即使在高实体密度环境中也能维持稳定的性能水平。

### ⭐ LagShield（性能影响：高）

- 监控服务器负载并在延迟峰值时自动调整设置。
- 应对服务器性能波动以缓解延迟和卡顿。
- 动态调整设置、禁用不必要的功能并优化资源分配。
- 通过最小化性能波动的影响确保流畅的游戏体验。

### ⭐ RedstoneLimiter（性能影响：中等）

- 禁用高消耗的红石时钟以防止服务器过载。
- 某些红石配置可能导致性能下降甚至崩溃。
- 启用 AntiRedstone 可保持服务器稳定性和响应能力。
- 即使在复杂的红石装置下也能保障不间断的游戏体验。

### ⭐ ExplosionOptimizer（性能影响：高）

- 限制爆炸威力并防止连锁反应，以减少延迟和破坏。
- 适用于频繁使用 TNT、爬行者或末影水晶的服务器。
- 防止过度爆炸引发性能问题。
- 在控制破坏性事件的同时维持稳定的服务器性能。

### ⭐ ConsoleFilter（性能影响：仅视觉）

- 根据预定义规则过滤控制台消息。
- 通过选择性显示关键消息来提升清晰度。
- 减少杂乱信息，改善多人服务器中的可读性。
- 促进高效的服务器管理，提升管理员和玩家的使用体验。

### ⭐ VehicleMotionReducer（性能影响：中等）

- 优化所有载具，如船和矿车。
- 移除在废弃矿井中生成的运输矿车。
- 在服务器频繁使用矿车时尤为实用。
- 通过优化载具机制和移除不必要实体来提升服务器性能。

### ⭐ AbilityLimiter（性能影响：中等）

- 限制三叉戟和鞘翅的快速使用，以防止过度的区块加载。
- 频繁的高速移动会导致服务器延迟和不稳定。
- AbilityLimiter 允许调整减速幅度，以平衡性能与玩家体验。
- 启用 AbilityLimiter 可确保更流畅的世界加载、稳定的服务器性能和可控的移动能力。

## 统计数据 bStats：

![bStats:](https://bstats.org/signatures/bukkit/LagFixer.svg)

# 其他插件：

[![gatekeeper](https://i.imgur.com/YHGjHR4.png)](https://modrinth.com/plugin/gatekeeper-mc)

[![dynamicdns](https://i.imgur.com/BikoONq.png)](https://modrinth.com/plugin/dynamicdns)
