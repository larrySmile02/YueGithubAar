package com.xgmn.plugin_config

import org.gradle.api.Plugin
import org.gradle.api.Project


class GelConfigPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        def configVersions = project.extensions.create("versionConfig",GelVersionConfig)
        project.task("configTest"){
            doLast {
                println "config version = ${configVersions.glideVersion}"
            }
        }
    }
}
