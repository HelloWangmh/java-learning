package wang.mh.base.grammer;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompilerApiDemo {
    public static void main(String[] args) throws IOException {
        OutputStream out = Files.newOutputStream(Paths.get("src/main/resources/compiler.txt"));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, out, null, "-sourcepath ","/Users/wmh/mine/IdeaProjects/mine/java-learning/src/main/java/Main.java ");
        System.out.println(result);
    }
}
