//package com.sven.android.plugin;
//
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.Type;
//import org.objectweb.asm.commons.AdviceAdapter;
//import org.objectweb.asm.commons.GeneratorAdapter;
//import org.objectweb.asm.commons.Method;
//
//import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
//
//class FragmentAopClassVisitor extends ClassVisitor {
//    private boolean mIsFragmentClass = false;
//    private boolean mHasResumeMethod = false;
//
//    public FragmentAopClassVisitor(int api, ClassVisitor cv) {
//        super(api, cv);
//    }
//
//    /**
//     * @param version    class文件的jdk版本，如50代表JDK 1.7版本
//     * @param access     类的修饰符，如public、final等
//     * @param name       类名，但是会以路径的形式来表示，如com.growingio.asmdemo.BlankFragment这个类，
//     *                   最后visit方法中的类名为com/growingio/asmdemo/BlankFragment
//     * @param signature  泛型信息，如果未定义泛型，则该参数为null
//     * @param superName  父类名
//     * @param interfaces 该类实现的接口列表
//     */
//    @Override
//    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        super.visit(version, access, name, signature, superName, interfaces);
//        // 如果该类的父类是 android.app.Fragment，说明该类是一个Fragment
//        mIsFragmentClass = "android/app/Fragment".equals(superName);
//        if (mIsFragmentClass) {
//            System.err.println("Find fragment class, it is " + name);
//        }
//    }
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
//        // 如果该类是Fragment子类，且该方法是onResume，就在该方法中进行代码织入
//        if (mIsFragmentClass && "onResume".equals(name) && "()V".equals(desc)) {
//            mHasResumeMethod = true;
//            return new ResumeMethodVisitor(Opcodes.ASM6, methodVisitor, access, name, desc);
//        }
//        return methodVisitor;
//    }
//
//    /**
//     * 类访问结束的时候调用
//     */
//    @Override
//    public void visitEnd() {
//        // 如果该类是Fragment子类，且没有重写onResume方法，那么就添加一个onResume方法
//        if (mIsFragmentClass && !mHasResumeMethod) {
//            // 生成方法 public void onResume()
//            GeneratorAdapter generator = new GeneratorAdapter(ACC_PUBLIC, new Method("onResume", "()V"), null, null, cv);
//            // 将this对象压入操作栈中，这里其实就是这个Fragment对象
//            generator.loadThis();
//            // 调用 super.onResume()
//            generator.invokeConstructor(Type.getObjectType("android/app/Fragment"), new Method("onResume", "()V"));
//
//            // 将this对象压入操作栈中，这里其实就是这个Fragment对象
//            generator.loadThis();
//            // 调用静态方法 com.growingio.asmdemo.FragmentAsmInjector#afterFragmentResume(Fragment fragment)
//            generator.invokeStatic(Type.getObjectType("com/growingio/asmdemo/FragmentAsmInjector"), new Method("afterFragmentResume", "(Landroid/app/Fragment;)V"));
//
//            // 调用return并结束该方法
//            generator.returnValue();
//            generator.endMethod();
//        }
//
//        super.visitEnd();
//    }
//
//    private static final class ResumeMethodVisitor extends AdviceAdapter {
//        protected ResumeMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
//            super(api, mv, access, name, desc);
//        }
//
//        /**
//         * 方法退出前调用
//         */
//        @Override
//        protected void onMethodExit(int opcode) {
//            // 将this对象压入操作栈中，这里其实就是这个Fragment对象
//            loadThis();
//            // 调用静态方法 com.growingio.asmdemo.FragmentAsmInjector#afterFragmentResume(Fragment fragment)
//            invokeStatic(Type.getObjectType("om.sven.androidall.asm/FragmentAsmInjector"), new Method("afterFragmentResume", "(Landroid/app/Fragment;)V"));
//            super.onMethodExit(opcode);
//        }
//    }
//}
//
