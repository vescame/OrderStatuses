plugins {
    id("org.springframework.boot") version Dependency.Version.springBootPlugin
}

springBoot {
    mainClass.set("vescame.orderstatuses.App")
}

dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))
    implementation(project(":persistence"))
    implementation(project(":http-api"))
}
