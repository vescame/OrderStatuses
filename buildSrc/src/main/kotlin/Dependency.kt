import Dependency.Version.jupiterApi
import Dependency.Version.springBoot

object Dependency {
    object Version {
        const val kotlin = "1.7.20"
        const val springBoot = "3.0.6"
        const val springDependencyManagementPlugin = "1.1.0"
        const val jupiterApi = "5.9.0"
    }

    // spring
    const val springBootStarter = "org.springframework.boot:spring-boot-starter:$springBoot"
    const val springBootWeb = "org.springframework.boot:spring-boot-starter-web:$springBoot"
    const val springBootValidation = "org.springframework.boot:spring-boot-starter-validation:$springBoot"
    const val springBootSecurity = "org.springframework.boot:spring-boot-starter-security:$springBoot"
    const val springBootSecurityResourceServer = "org.springframework.boot:spring-boot-starter-oauth2-resource-server:$springBoot"

    // test dependencies
    const val springBootStarterTest = "org.springframework.boot:spring-boot-starter-test:$springBoot"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter-api:$jupiterApi"
}