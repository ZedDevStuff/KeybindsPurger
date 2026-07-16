import earth.terrarium.cloche.api.target.FabricTarget

plugins {
    id("earth.terrarium.cloche") version "0.19.11"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "dev.zeddevstuff"
version = "1.4.0"

repositories {
    mavenCentral()
    cloche {
        librariesMinecraft()
        main()
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenFabric()
        mavenForge()
    }
    maven("https://maven.blamejared.com/") {
        name = "Controlling"
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
cloche {
    metadata {
        modId = "keybindspurger"
        name = rootProject.name
        description = "Unset all keybinds for a clean slate"
        license = "MIT"
        author("ZedDevStuff")
        url = "https://modrinth.com/mod/keybindspurger"
        issues = "https://github.com/ZedDevStuff/KeybindsPurger/issues"
        icon = "icon.png"
    }

    common {
    }

    val common2612 = common("common:26.1.2") {
        dependencies {
            //implementation("com.blamejared.controlling:Controlling-common-26.1.2:26.1.2.4")
        }
    }
    neoforge("neoforge:26.1.2") {
        loaderVersion = "26.1.2.78"
        dependencies {
            implementation("com.blamejared.controlling:Controlling-neoforge-26.1.2:26.1.2.4")
        }

        dependsOn(common2612)
    }
    fabric("fabric:26.1.2") {
        includedClient()
        dependencies {
            fabricApi("0.154.2")
            implementation("com.blamejared.controlling:Controlling-fabric-26.1.2:26.1.2.4")
        }

        dependsOn(common2612)
    }

    val common1211 = common("common:1.21.1") {
        dependencies {
            implementation("com.blamejared.controlling:Controlling-common-1.21:18.0.4")
        }
    }
    neoforge("neoforge:1.21.1") {
        loaderVersion = "21.1.219"

        dependsOn(common1211)
    }
    fabric("fabric:1.21.1") {
        includedClient()
        dependencies {
            fabricApi("0.116.8")
        }

        dependsOn(common1211)
    }

    val common1201 = common("common:1.20.1") {
        dependencies {
            implementation("com.blamejared.controlling:Controlling-common-1.20.1:12.0.2")
        }
    }
    forge("forge:1.20.1") {
        loaderVersion = "47.4.10"

        dependsOn(common1201)
    }
    fabric("fabric:1.20.1") {
        includedClient()
        dependencies {
            fabricApi("0.92.7")
        }

        dependsOn(common1201)
    }

    commonTargets.configureEach {
        mappings {
            official()
        }
        if(name != "common")
            accessWideners.from("src/${name.replace(":", "/")}/main/resources/${cloche.metadata.modId.get()}.accesswidener")
        //accessWideners.from("src/common/${minecraftVersion.get()}/main/resources/${cloche.metadata.modId.get()}.accesswidener")

        dependencies {
            compileOnly("org.spongepowered:mixin:0.8.5")
        }
    }

    targets.withType<FabricTarget>().configureEach {
        loaderVersion = "0.19.3"

        metadata {
            entrypoint("client", "dev.zeddevstuff.keybindspurger.fabric.KeybindsPurgerFabric")
        }
    }
//    listOf(fabric1211, neoforge1211).forEach {
//        it.minecraftVersion = "1.21.1"
//    }
//    listOf(fabric1201, forge1201).forEach {
//        it.minecraftVersion = "1.20.1"
//    }

    targets.configureEach {
        minecraftVersion = name.substringAfter(":")
        dependencies {
            compileOnly("org.jetbrains:annotations:24.0.1")
        }

        metadata {
            mixins.from("keybindspurger.mixins.json")
        }

        accessWideners.from("src/common/${minecraftVersion.get()}/main/resources/${cloche.metadata.modId.get()}.accesswidener")

        runs {
            client()
        }
    }
}