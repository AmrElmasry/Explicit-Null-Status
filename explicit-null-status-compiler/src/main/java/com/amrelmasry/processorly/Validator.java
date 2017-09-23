package com.amrelmasry.processorly;


import java.util.List;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import static com.amrelmasry.processorly.ErrorUtil.printError;

/**
 * Created by Amr Elmasry on 16/09/17.
 */

final class Validator {

    private Validator() {
        throw new RuntimeException("No Instances");
    }

    static void validateFields(List<VariableElement> fields,
                               Messager messager) {
        for (VariableElement field : fields) {
            if (field.asType().getKind().isPrimitive()) continue;
            if (!nullAnnotationIsPresent(field)) {
                printError(messager, field, ErrorUtil.makeErrorMsg(field));
            }
        }
    }

    static void validateMethods(List<ExecutableElement> methods,
                                Messager messager) {
        for (ExecutableElement method : methods) {
            validateMethodReturnType(method, messager);
            validateParameters(method.getParameters(), messager);
        }
    }

    private static void validateMethodReturnType(ExecutableElement method, Messager messager) {
        if (method.getReturnType().getKind().isPrimitive() ||
                method.getReturnType().getKind() == TypeKind.VOID)
            return;
        if (!nullAnnotationIsPresent(method)) {
            printError(messager, method, ErrorUtil.makeErrorMsg(method));
        }
    }

    static void validateConstructors(List<ExecutableElement> constructors,
                                     Messager messager) {
        for (ExecutableElement constructor : constructors) {
            if (constructor.getParameters().isEmpty())
                continue;
            validateParameters(constructor.getParameters(), messager);
        }
    }

    private static void validateParameters(List<? extends VariableElement> parameters,
                                           Messager messager) {

        if (parameters.isEmpty()) return;

        for (VariableElement parameter : parameters) {
            if (parameter.asType().getKind().isPrimitive())
                continue;

            if (!nullAnnotationIsPresent(parameter)) {
                printError(messager, parameter, ErrorUtil.makeErrorMsg(parameter));
            }
        }
    }

    private static boolean nullAnnotationIsPresent(Element annotatedConstruct) {
        List<? extends AnnotationMirror> annotations = annotatedConstruct.getAnnotationMirrors();
        if (annotations.isEmpty()) return false;

        for (AnnotationMirror annotation : annotations) {
            String annotationName = annotation.getAnnotationType().toString();
            if (SupportedAnnotations.NOT_NULL_ANNOTATIONS.contains(annotationName) ||
                    SupportedAnnotations.NULLABLE_ANNOTATIONS.contains(annotationName))
                return true;
        }
        return false;
    }
}
