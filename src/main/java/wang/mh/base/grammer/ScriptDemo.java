package wang.mh.base.grammer;

import javax.script.*;
import java.util.List;

public class ScriptDemo {

    private static ScriptEngineManager manager = new ScriptEngineManager();

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        testFun();
    }

    public static void testFun() throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval("function greet(how, whom) { return how + ', ' + whom + '!' }");
        Object result = ((Invocable) engine).invokeFunction("greet", "hello", "wmh");
        System.out.println(result);
    }


    public static void testJs() throws ScriptException {
        ScriptEngine js = manager.getEngineByName("js");
        js.eval(" a = 10");
        Object result = js.eval(" a += 100");
        System.out.println(result);

        js.put("b", "bbb");
        System.out.println(js.eval("b"));
    }

    public static void testBasic(){
        ScriptEngine engine = manager.getEngineByName("nashorn");
        System.out.println(engine);
        manager.getEngineFactories().forEach(f -> {
            System.out.println(f.getParameter("THREADING"));
            System.out.println(f.getNames());
            System.out.println(f.getExtensions());
            System.out.println(f.getMimeTypes());
        });
    }
}
