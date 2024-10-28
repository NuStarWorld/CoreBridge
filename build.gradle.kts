import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    java
    id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}


tasks {
    jar {
        // 构件名
        archiveBaseName.set(rootProject.name)
        // 打包子项目源代码
        rootProject.subprojects.forEach { from(it.sourceSets["main"].output) }
    }
}
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")
    apply(plugin = "java")

    repositories {
        mavenLocal()
        maven("https://maven.wcpe.top/repository/maven-public/")
        maven("https://libraries.minecraft.net")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://repo1.maven.org/maven2")
        maven("https://repo.codemc.io/repository/nms/")
        mavenCentral()
    }

    val wcpelibVersion = "1.8.1"
    dependencies {
        compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
        compileOnly("top.wcpe:wcpelib-common:${wcpelibVersion}")
        compileOnly("top.wcpe:wcpelib-bukkit:${wcpelibVersion}")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

        compileOnly(kotlin("stdlib"))
        testImplementation(kotlin("test"))
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-XDenableSunApiLintControl"))
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xskip-metadata-version-check")
        }
    }

    publishing {
        repositories {
            maven {
                credentials {
                    username = project.findProperty("username").toString()
                    password = project.findProperty("password").toString()
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
                val releasesRepoUrl = uri("https://maven.wcpe.top/repository/maven-releases/")
                val snapshotsRepoUrl = uri("https://maven.wcpe.top/repository/maven-snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            }
            mavenLocal()
        }
        publications {

            create<MavenPublication>("maven") {
                artifactId = project.name.toLowerCase()
                groupId = project.group.toString()
                version = "${project.version}"
                from(components["java"])
                println("> Apply \"$groupId:$artifactId:$version\"")
            }
        }
    }

}