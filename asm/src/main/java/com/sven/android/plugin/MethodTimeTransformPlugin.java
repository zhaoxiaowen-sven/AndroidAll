package com.sven.android.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

class MethodTimeTransformPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        //注册方式1
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new MethodTimeTransform(project));

        //注册方式2
        //project.android.registerTransform(new MethodTimeTransform())
    }
}
