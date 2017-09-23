package com.amrelmasry.processorly;

import com.amrelmasry.processly.annotations.ExplicitNullStatus;
import com.amrelmasry.processly.annotations.IgnoreConstructors;
import com.amrelmasry.processly.annotations.IgnoreFields;
import com.amrelmasry.processly.annotations.IgnoreMethods;
import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(ExplicitNullStatus.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment re) {
        Set<? extends Element> elements = extractElements(re);
        for (Element element : elements) {
            processElement(element);
        }
        return false;
    }

    private Set<? extends Element> extractElements(RoundEnvironment re) {
        return re.getElementsAnnotatedWith(ExplicitNullStatus.class);
    }

    private void processElement(Element element) {
        if (element.getKind().equals(ElementKind.PACKAGE)) {
            for (Element innerElement : element.getEnclosedElements()) {
                if (innerElement.getAnnotation(ExplicitNullStatus.class) == null)
                    processType((TypeElement) innerElement);
            }
        } else {
            processType((TypeElement) element);
        }
    }

    private void processType(TypeElement element) {
        List<? extends Element> allMembers = element.getEnclosedElements();

        // fields
        if (element.getAnnotation(IgnoreFields.class) == null) {
            List<VariableElement> fields = ElementFilter.fieldsIn(allMembers);
            fields.removeIf(new Predicate<VariableElement>() {
                @Override
                public boolean test(VariableElement variableElement) {
                    return SuppressUtil.shouldSkipField(variableElement);
                }
            });
            Validator.validateFields(fields, messager);
        }

        // methods
        if (element.getAnnotation(IgnoreMethods.class) == null) {
            List<ExecutableElement> methods = ElementFilter.methodsIn(allMembers);
            methods.removeIf(new Predicate<ExecutableElement>() {
                @Override
                public boolean test(ExecutableElement method) {
                    return SuppressUtil.shouldSkipMethod(method);
                }
            });
            Validator.validateMethods(methods, messager);
        }

        // constructors
        if (element.getAnnotation(IgnoreConstructors.class) == null) {
            final List<ExecutableElement> constructors = ElementFilter.constructorsIn(allMembers);
            constructors.removeIf(new Predicate<ExecutableElement>() {
                @Override
                public boolean test(ExecutableElement constructor) {
                    return SuppressUtil.shouldSkipConstructor(constructor);
                }
            });
            Validator.validateConstructors(constructors, messager);
        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
