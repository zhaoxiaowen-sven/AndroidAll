package com.sven.android.plugin;

import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.commons.AdviceAdapter;

class HelloMethodVisitor extends AdviceAdapter {

    HelloMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    //方法进入
    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        //这里的mv是MethodVisitor
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn("Hello World!");
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("before");
        mv.visitLdcInsn("hello world");
        mv.visitLdcInsn("after");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


    }
}
