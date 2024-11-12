plugins {
    id("java")
    id("idea")
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
}

base.archivesBaseName = "InterfaceEditor"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libs") { include("substance.jar") })
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("InterfaceGui")
}

tasks.register<Copy>("copyConfig") {
    from("src/main/resources/config.properties")
    into("$rootDir/build/libs")

    doLast {
        val configFile = file("$rootDir/build/libs/config.properties")
        if (configFile.exists()) {
            val content = configFile.readText()
            val updatedContent = content.replace(
                "cache_path=",
                "cache_path=${rootDir.absolutePath.replace("\\", "\\\\")}\\\\data\\\\cache\\\\"
            )
            configFile.writeText(updatedContent)
        }
    }
}

tasks.register<Jar>("buildJar") {
    archiveBaseName.set(base.archivesBaseName)

    from(sourceSets.main.get().output)

    manifest {
        attributes["Main-Class"] = "InterfaceGui"
    }
}

tasks.named("build") {
    dependsOn("copyConfig")
    dependsOn("buildJar")
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java")
        }
        resources {
            srcDirs("libs")
        }
    }
}
