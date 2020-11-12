package com.xgmn.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

 class ConfigGelPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        def configVersions = project.extensions.create("configVersions",VersionConfig)
        project.task("taskTest"){
            doLast {
                println "test version = ${configVersions.glideVersion}"
            }
        }
    }
}
