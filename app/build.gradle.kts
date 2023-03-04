plugins {
    application
}

application {
    mainClass.set("vescame.orderstatuses.App")
}

dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))
    implementation(project(":persistence"))
    implementation(project(":http-api"))
}
