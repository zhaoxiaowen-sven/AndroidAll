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
//        System.out.println("===================");
//        System.out.println("I am com.dgplugin.AsmPlugin");
//        System.out.println("===================");
//
//        AppExtension android = project.getExtensions().findByType(AppExtension.class);
//        // 注册Transform
//        android.registerTransform(new MyTransform(project));
//    }
//}
