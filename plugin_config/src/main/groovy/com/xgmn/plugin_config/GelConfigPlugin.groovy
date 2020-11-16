package com.xgmn.plugin_config

import org.gradle.api.Plugin
import org.gradle.api.Project


class GelConfigPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.add("versionConfig",GelVersionConfig)
        project.task("configTest"){
            doLast {
                println "config version = ${configVersions.glideVersion}"
            }
        }
    }
}
