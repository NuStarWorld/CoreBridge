import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    java
    idea
    `java-library`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.dokka") version "1.9.20"
}

tasks {
    jar {
        // 构件名
        archiveBaseName.set(rootProject.name)
        // 打包子项目源代码
        rootProject.subprojects.forEach { from(it.sourceSets["main"].output) }
    }
}

allprojects {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.wcpe.top/repository/maven-public/")
        maven("https://jitpack.io")
        maven("https://libraries.minecraft.net")
        maven("https://repo1.maven.org/maven2")
        maven("https://repo.codemc.io/repository/nms/")
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")
    apply(plugin = "java")

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
        withSourcesJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
            apiVersion = KotlinVersion.KOTLIN_2_1
            languageVersion = KotlinVersion.KOTLIN_2_1
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xskip-metadata-version-check")
        }
    }
    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-XDenableSunApiLintControl"))
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
                artifactId = "${rootProject.name}-${project.name}".lowercase()
                groupId = project.group.toString()
                version = "${project.version}"
                from(components["java"])
                println("> Apply \"$groupId:$artifactId:$version\"")
            }
        }
    }

}