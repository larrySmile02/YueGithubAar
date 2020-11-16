package com.xgmn.annotation_processor;

import com.google.auto.service.AutoService;
import com.xgmn.annotation_service.MicroService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class ServiceProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
       return Collections.singleton(MicroService.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for(Element element:roundEnvironment.getRootElements()){
            System.out.println("ServiceProcessor_elementName="+element.getSimpleName());
            for(Element enclosedElement : element.getEnclosedElements()){
                if(enclosedElement.getKind()== ElementKind.CLASS){
                    MicroService service = enclosedElement.getAnnotation(MicroService.class);
                    if(service !=null){
                        System.out.println("ServiceProcessor_service exist!");
                        return true;
                    }
                }else if(enclosedElement.getKind()== ElementKind.ANNOTATION_TYPE){
                    MicroService service = enclosedElement.getAnnotation(MicroService.class);
                    if(service !=null){
                        System.out.println("ServiceProcessor_service exist!!!!!");
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
