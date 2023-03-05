import Dependency.Version.jupiterApi
import Dependency.Version.springBootPlugin

object Dependency {
    object Version {
        const val kotlin = "1.7.20"
        const val springBootPlugin = "3.0.0"
        const val springDependencyManagementPlugin = "1.1.0"
        const val jupiterApi = "5.9.0"
    }

    // spring
    const val springBootStarter = "org.springframework.boot:spring-boot-starter:$springBootPlugin"
    const val springBootWeb = "org.springframework.boot:spring-boot-starter-web:$springBootPlugin"

    // test dependencies
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test:$springBootPlugin"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter-api:$jupiterApi"
}