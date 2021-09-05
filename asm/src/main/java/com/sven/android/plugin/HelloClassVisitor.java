package com.sven.android.plugin;

import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Opcodes;

class HelloClassVisitor extends ClassVisitor {

    HelloClassVisitor(ClassVisitor cv) {
        //这里需要指定一下版本Opcodes.ASM7
        super(Opcodes.ASM7, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        return new HelloMethodVisitor(api, methodVisitor, access, name, descriptor);
    }
}
