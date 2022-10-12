plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/scalar-labs/scalardb-sql")
        credentials {
            username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:2.7.4")
    implementation("org.postgresql:postgresql:42.1.4")
    implementation("com.scalar-labs:scalardb-sql-jdbc:3.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
