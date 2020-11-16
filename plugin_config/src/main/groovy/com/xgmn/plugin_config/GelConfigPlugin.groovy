package com.xgmn.plugin_config

import org.gradle.api.Plugin
import org.gradle.api.Project


class GelConfigPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        def versionConfig = project.extensions.create("versionConfig",GelVersionConfig)
        project.extensions.add("versionConfig",versionConfig)
        project.task("configTest"){
            doLast {
                println "config version = ${configVersions.glideVersion}"
            }
        }
    }
}
