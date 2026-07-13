rootProject.name = "KeybindsPurger"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://maven.msrandom.net/repository/cloche/")
        maven("https://raw.githubusercontent.com/settingdust/maven/main/repository/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
