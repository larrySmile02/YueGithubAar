package com.xgmn.annotation_processor;

import com.google.auto.service.AutoService;
import com.xgmn.annotation_service.MicroService;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class ServiceProcessor extends AbstractProcessor {
    private Messager messager;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //getCanonicalName()返回的包括包名+类名，与getName()区别在返回的格式上，
        // 例如int[],前者返回int[]后者返回[|
        // 内部类，前者返回com.getname.pkg.Main.Demo1.Demo2 后者返回com.getname.pkg.Main$Demo1$Demo2
       return Collections.singleton(MicroService.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


            if (set != null && set.size() > 0) {
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    messager.printMessage(Diagnostic.Kind.NOTE, "TypeElement = " + iterator.next());
                }
            }
            Set<? extends Element> typeElements = roundEnvironment.getElementsAnnotatedWith(MicroService.class);
            if (typeElements != null && typeElements.size() > 0) {
                for (Element element : typeElements) {
                    processElement(element);
                }

                return true;
            }


        //注解处理过程是有序循环过程，process方法可能会被多次调用，一次循环中processor可能会被要求处理上次循环产生的注解
        // 返回true说明注解被声明了，接下来processor就不会再处理这些注解了
        //返回false说明注解没有被声明，接下来processor还会继续处理这些注解
        // 参考 https://docs.oracle.com/javase/7/docs/api/javax/annotation/processing/AbstractProcessor.html
        // 参考 https://yuweiguocn.github.io/java-annotation-processor/
        return false;
    }

    /**
     * @param element : 被annotation标记的元素，MyMaid限制Target是Type，所以这里是类
     *                打印出的是被标记类的类名
     */
    private void processElement(Element element) {
        TypeElement typeElement = (TypeElement) element;
        String clsName = typeElement.getQualifiedName().toString();
        //如果Diagnostic.Kind.ERROR直接编译失败
        messager.printMessage(Diagnostic.Kind.NOTE, "clazzName = " + clsName);
    }

}
