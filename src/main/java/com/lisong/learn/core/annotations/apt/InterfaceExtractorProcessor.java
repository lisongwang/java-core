package com.lisong.learn.core.annotations.apt;

import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"com.lisong.learn.core.annotations.apt.InterfaceExtractor"})
public class InterfaceExtractorProcessor extends AbstractProcessor {

    private Map<TypeElement, List<ExecutableElement>> workList = new LinkedHashMap<>();
    private Messager msg;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        msg = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            return processImpl(annotations, roundEnv);
        } catch (Exception e) {
            //do not throw any exception to compiler
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            msg.printMessage(Diagnostic.Kind.ERROR, sw.toString());
            return true;
        }
    }

    private boolean processImpl(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(roundEnv.processingOver()) {
            generateSourceFile();
        }else {
            processAnnotation(annotations, roundEnv);
        }
        return true;
    }

    private  void processAnnotation(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(InterfaceExtractor.class)) {
            if(annotatedElement.getKind() != ElementKind.CLASS)
                continue;
            TypeElement te = (TypeElement)annotatedElement;
            List<ExecutableElement> mlist = new ArrayList<>();
            for(ExecutableElement ee : ElementFilter.methodsIn(te.getEnclosedElements())) {
                if(ee.getModifiers().contains(Modifier.PUBLIC) && !ee.getModifiers().contains(Modifier.STATIC))
                    mlist.add(ee);
            }
            workList.put(te, mlist);
        }
    }

    private void generateSourceFile() {
        msg.printMessage(Diagnostic.Kind.WARNING, "method count: " +
                workList.values().toArray(new List[0])[0].size());
        Filer filer = processingEnv.getFiler();
        for(Map.Entry<TypeElement, List<ExecutableElement>> entry : workList.entrySet()) {
            TypeElement te = entry.getKey();
            List<ExecutableElement> ee = entry.getValue();
            List<MethodSpec> mlist = new ArrayList<>();
            for(ExecutableElement method : ee) {
                MethodSpec m = MethodSpec.methodBuilder(method.getSimpleName().toString())
                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                        .addParameters(method.getParameters().stream().map(
                                ParameterSpec::get).collect(Collectors.toList()))
                        .returns(TypeName.get(method.getReturnType()))
                        .build();
                mlist.add((m));
            }
            String name = te.getAnnotation(InterfaceExtractor.class).value();
            if(name.length() < 1)
                name = "I" + te.getSimpleName();
            TypeSpec resultType = TypeSpec.interfaceBuilder(name)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethods(mlist)
                    .build();
            String packageName = processingEnv.getElementUtils().getPackageOf(te).getQualifiedName().toString() + ".apt";
            JavaFile javaFile = JavaFile.builder(packageName, resultType).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}