import earth.terrarium.cloche.api.target.CommonTarget
import earth.terrarium.cloche.api.target.FabricTarget

plugins {
    id("earth.terrarium.cloche") version "0.18.2"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "dev.zeddevstuff"
version = "1.5.0"

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
        mappings {
            official()
        }
    }

    val common1211 = common("common:1.21.1") {}
    val neoforge1211 = neoforge("neoforge:1.21.1") {
        loaderVersion = "21.1.219"

        dependsOn(common1211)
    }
    val fabric1211 = fabric("fabric:1.21.1") {
        includedClient()
        dependencies {
            fabricApi("0.116.8")
        }

        dependsOn(common1211)
    }

    val common1201 = common("common:1.20.1") {}
    val forge1201 = forge("forge:1.20.1") {
        loaderVersion = "47.4.10"

        dependsOn(common1201)
    }
    val fabric1201 = fabric("fabric:1.20.1") {
        includedClient()
        dependencies {
            fabricApi("0.92.7")
        }

        dependsOn(common1201)
    }


    listOf(fabric1201, fabric1211).forEach {
        it.loaderVersion = "0.18.4"

        it.metadata {
            entrypoint("main", "dev.zeddevstuff.keybindspurger.fabric.KeybindsPurgerFabric")
        }
    }
    listOf(fabric1211, neoforge1211).forEach {
        it.minecraftVersion = "1.21.1"
    }
    listOf(fabric1201, forge1201).forEach {
        it.minecraftVersion = "1.20.1"
    }

    targets.forEach {
        it.dependencies {
            compileOnly("org.jetbrains:annotations:24.0.1")
        }

        if (it !is CommonTarget) {
            it.mixins.from("src/" + it.target.name.replace(":", "/") + "/main/" + metadata.modId.get() + ".mixins.json")
        }
        it.runs {
            client()
            server()
        }
    }
}