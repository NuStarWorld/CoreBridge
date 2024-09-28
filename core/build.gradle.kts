tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand(rootProject.properties)
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
dependencies {
    implementation(project(":api"))
    implementation(project(":impl:dragoncore"))
    implementation(project(":impl:easycore"))
    implementation(project(":impl:germplugin"))

}