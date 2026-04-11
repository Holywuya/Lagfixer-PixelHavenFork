plugins {
    id("java-library")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly(project(":support:common"))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 8
    }
}

configurations {
    compileClasspath {
        attributes {
            attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 25)
        }
    }
}