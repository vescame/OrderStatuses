plugins {
    java
    kotlin("jvm") version Dependency.Version.kotlin
    id("io.spring.dependency-management") version Dependency.Version.springDependencyManagementPlugin
}

repositories {
    mavenCentral()
}

allprojects {
    group = "vescame.orderstatuses"
    version = "0.0.1-SNAPSHOT"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(Dependency.springBootStarter)
        testImplementation(Dependency.springBootStarterTest)
        testImplementation(Dependency.junitJupiter)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
