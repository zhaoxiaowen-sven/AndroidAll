package com.sven.android.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;
import com.google.common.collect.FluentIterable;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Set;

class MyTransform extends Transform {
    @Override
    public String getName() {
        return "autotrackTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        // 只处理java class文件
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        // 处理整个project，包括依赖
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws IOException {
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        // 遍历所有输入文件
        for (TransformInput input : inputs) {
            // 遍历所有文件夹
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                FluentIterable<File> allFiles = FileUtils.getAllFiles(directoryInput.getFile());
                // 遍历文件夹下面的所有文件
                for (File fileInput : allFiles) {
                    // 获取文件输出的目标文件夹
                    File outDir = outputProvider.getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(), directoryInput.getScopes(), Format.DIRECTORY);
                    outDir.mkdirs();
                    File fileOut = new File(outDir.getAbsolutePath(), fileInput.getName());
                    // 如果是java class文件，将进行AOP处理
                    if (fileInput.getName().endsWith(".class")) {
                        if (transformClassFile(fileInput, fileOut)) {
                            System.err.println("Transformed class file " + fileInput.getName() + " successfully");
                        } else {
                            System.err.println("Failed to transform class file " + fileInput.getName());
                        }
                    }
                }
            }

            // 遍历所有jar包
            for (JarInput jarInput : input.getJarInputs()) {
                // 获取jar包输出的目标文件
                File jarOut = outputProvider.getContentLocation(jarInput.getName(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
                // 将jar包拷贝到目标文件
                FileUtils.copyFile(jarInput.getFile(), jarOut);
            }
        }
    }

    private boolean transformClassFile(File from, File to) {
        boolean result;
        File toParent = to.getParentFile();
        toParent.mkdirs();
        try (FileInputStream fileInputStream = new FileInputStream(from); FileOutputStream fileOutputStream = new FileOutputStream(to)) {
            result = transformClass(fileInputStream, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    private boolean transformClass(InputStream from, OutputStream to) {
        try {
            byte[] bytes = IOUtils.toByteArray(from);
            byte[] modifiedClass = visitClassBytes(bytes);
            if (modifiedClass != null) {
                IOUtils.write(modifiedClass, to);
                return true;
            } else {
                IOUtils.write(bytes, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private byte[] visitClassBytes(byte[] bytes) {
        // 解析该java字节码
        ClassReader classReader = new ClassReader(bytes);
        // 通ClassWriter修改java字节码
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        // 对java字节码逐个访问
        FragmentAopClassVisitor fragmentAopClassVisitor = new FragmentAopClassVisitor(Opcodes.ASM6, classWriter);
        classReader.accept(fragmentAopClassVisitor, ClassReader.SKIP_FRAMES | ClassReader.EXPAND_FRAMES);
        // 返回修改后的java字节码
        return classWriter.toByteArray();
    }
}
