package com.amrelmasry.processorly;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * Created by Amr Elmasry on 22/09/17.
 */

public final class ErrorUtil {

    private ErrorUtil() {
        throw new RuntimeException("No Instances");
    }

    public static String makeErrorMsg(Element element) {
        return makeErrorMsg(element.getSimpleName().toString(),
                element.getKind().toString().toLowerCase());
    }

    public static String makeErrorMsg(String elementName, String elementKind) {
        return String.format("%s %s must be annotated with @NonNull or @Nullable",
                elementName, elementKind);
    }

    static void printError(Messager messenger, Element element, String msg) {
        messenger.printMessage(Diagnostic.Kind.ERROR, msg, element);
    }

}
