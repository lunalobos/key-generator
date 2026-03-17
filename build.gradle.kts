plugins {
    kotlin("jvm") version "2.2.20"
    application
}

group = "io.github.lunalobos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "io.github.lunalobos.MainKt"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "io.github.lunalobos.MainKt" // O el nombre de tu archivo con 'Kt' al final
    }
    // Esto es para incluir las librerías de Kotlin dentro del JAR
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}