plugins {
    id("org.springframework.boot") version Dependency.Version.springBoot
}

springBoot {
    mainClass.set("vescame.orderstatuses.App")
}

dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))
    implementation(project(":persistence"))
    implementation(project(":in-memory-persistence"))
    implementation(project(":http-api"))
    implementation(project(":integration"))
}
