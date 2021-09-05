//package com.sven.android.plugin;
//
//import com.android.build.gradle.AppExtension;
//
//import org.gradle.api.Plugin;
//import org.gradle.api.Project;
//
//public class MyAsmPlugin implements Plugin<Project> {
//    @Override
//    public void apply(Project project) {
//        AppExtension android = project.getExtensions().findByType(AppExtension.class);
//        // 注册Transform
//        android.registerTransform(new MyTransform());
//    }
//}
