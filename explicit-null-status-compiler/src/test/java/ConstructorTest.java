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

public class ConstructorTest {

    @Test
    public void classWithNoConstructor_shouldCompileSuccessfully() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void classWithNoArgsConstructor_shouldCompileSuccessfully() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    public Test() {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void classWithPrimitiveArgsConstructor_shouldCompileSuccessfully() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    public Test(boolean b, int num) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void classWithAnnotatedArgsConstructor_shouldCompileSuccessfully() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import android.support.annotation.NonNull;",
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    public Test(@NonNull String name) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void classWithUnAnnotatedOneArgumentConstructor_shouldFail() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    public Test(String name) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(1)
                .withErrorContaining(ErrorUtil.makeErrorMsg("name", "parameter"));
    }

    @Test
    public void classWithUnAnnotatedMultiArgsConstructor_shouldFail() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    public Test(String name, Integer age) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .failsToCompile()
                .withErrorCount(2)
                .withErrorContaining(ErrorUtil.makeErrorMsg("name", "parameter"))
                .and()
                .withErrorContaining(ErrorUtil.makeErrorMsg("age", "parameter"));
    }

    @Test
    public void classWithUnAnnotatedArgumentConstructor_allConstructorsIgnored_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.IgnoreConstructors;",
                        "",
                        "@ExplicitNullStatus",
                        "@IgnoreConstructors",
                        "public class Test {",
                        "    public Test(String name) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }

    @Test
    public void classWithUnAnnotatedArgumentConstructor_constructorIgnored_shouldCompile() throws Exception {
        JavaFileObject source = JavaFileObjects.forSourceString("test.Test",
                Joiner.on('\n').join(
                        "import com.amrelmasry.processly.annotations.ExplicitNullStatus;",
                        "import com.amrelmasry.processly.annotations.Ignore;",
                        "",
                        "@ExplicitNullStatus",
                        "public class Test {",
                        "    @Ignore",
                        "    public Test(String name) {",
                        "    }",
                        "}"));

        assertAbout(javaSource())
                .that(source)
                .processedWith(new MyProcessor())
                .compilesWithoutError();
    }
}

