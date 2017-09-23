import com.amrelmasry.processorly.ErrorUtil;
import com.amrelmasry.processorly.MyProcessor;
import com.google.common.base.Joiner;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;


public class FieldTest {
    @Test
    public void notAnnotatedFields_shouldFail() throws Exception {

        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining(ErrorUtil.makeErrorMsg("name", "field"))
                .in(source)
                .onLine(4);
    }

    @Test
    public void annotatedField_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import android.support.annotation.NonNull;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "@NonNull",
                        "    public String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void annotatedWithIgnore_shouldBeIgnored() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.Ignore;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "@Ignore",
                        "    public String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void annotatedWithIgnoreFields_shouldBeIgnored() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.IgnoreFields;",
                        "@ExplicitNullStatus",
                        "@IgnoreFields",
                        "class Test {",
                        "    public String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void primitiveField_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public int age;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void notAnnotatedStaticField_shouldFail() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public static String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining(ErrorUtil.makeErrorMsg("name", "field"))
                .in(source)
                .onLine(4);
    }

    @Test
    public void annotatedStaticField_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import android.support.annotation.NonNull;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "@NonNull",
                        "    public static String name;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void staticPrimitiveField_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "class Test {",
                        "    public static int age;",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }
}