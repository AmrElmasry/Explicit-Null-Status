import com.amrelmasry.processorly.ErrorUtil;
import com.amrelmasry.processorly.MyProcessor;
import com.google.common.base.Joiner;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

/**
 * Created by Amr Elmasry on 20/09/17.
 */

public class MethodTest {

    @Test
    public void voidReturnType_NoArgs_notAnnotated_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public void printAge() {",
                        "System.out.println(10);",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void notVoidReturnType_NoArgs_annotated_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import android.support.annotation.Nullable;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    @Nullable",
                        "    public String getName() {",
                        "       return \"Foo\"; ",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void primitiveReturnType_NoArgs_notAnnotated_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public int getAge() {",
                        "       return 40; ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void notVoidReturnType_noArgs_annotatedWithIgnore_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.Ignore;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    @Ignore",
                        "    public String getName() {",
                        "return \"Alex\"; ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void notVoidReturnType_noArgs_annotatedWithIgnoreMethods_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.IgnoreMethods;",
                        "@ExplicitNullStatus",
                        "    @IgnoreMethods",
                        "class Test {",
                        "    public String getName() {",
                        "return \"Alex\"; ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void voidReturnType_primitiveArgs_notAnnotated_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public void mul(int x, int y) {",
                        "       System.out.println(x * y); ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void voidReturnType_nonPrimitiveArgs_notAnnotated_shouldFail() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public void print(String msg) {",
                        "       System.out.println(msg); ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining(ErrorUtil.makeErrorMsg("msg", "parameter"))
                .in(source).onLine(4);
    }

    @Test
    public void voidReturnType_nonPrimitiveArgs_annotatedWithIgnore_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.Ignore;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    @Ignore",
                        "    public void print(String msg) {",
                        "       System.out.println(msg); ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void voidReturnType_nonPrimitiveArgs_annotatedWithIgnoreMethods_shouldCompile() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.IgnoreMethods;",
                        "@ExplicitNullStatus",
                        "@IgnoreMethods",
                        "class Test {",
                        "    public void print(String msg) {",
                        "       System.out.println(msg); ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void notVoidReturnType_noArgs_notAnnotated_shouldFail() {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public String getName() {",
                        "       return \"Mark\"; ",
                        "}",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining(ErrorUtil.makeErrorMsg("getName", "method"))
                .in(source).onLine(4);
    }

}
