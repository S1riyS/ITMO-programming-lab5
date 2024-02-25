val MAIN_CLASS = "s1riys.lab5.Main"

plugins {
    id("java")
}

group = "s1riys.lab5"
version = "1.0"

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("au.com.bytecode:opencsv:2.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest.attributes["Main-Class"] = MAIN_CLASS
    val dependencies = configurations.runtimeClasspath.get().map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}