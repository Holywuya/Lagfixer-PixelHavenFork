plugins {
    id("java-library")
    id("com.gradleup.shadow")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")
    compileOnly(project(":plugin"))
}

tasks {
    reobfJar {
        enabled = false
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 25
    }
}