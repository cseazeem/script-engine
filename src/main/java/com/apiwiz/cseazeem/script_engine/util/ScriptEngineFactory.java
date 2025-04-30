package com.apiwiz.cseazeem.script_engine.util;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.python.util.PythonInterpreter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;

public class ScriptEngineFactory {
    private static final Engine GRAAL_ENGINE = Engine.newBuilder()
            .option("engine.WarnInterpreterOnly", "false")
            .build();

    public static ScriptEngine getJavaScriptEngine() {
        return new ScriptEngineManager().getEngineByName("graal.js");
    }

    public static Context getJavaScriptContext() {
        return Context.newBuilder("js")
                .engine(GRAAL_ENGINE)
                .allowAllAccess(true)
                .build();
    }

    public static PythonInterpreter getPythonInterpreter() {
        return new PythonInterpreter();
    }

    public static void loadJavaScriptLibrary(Context context, String libraryPath) throws IOException {
        context.eval(Source.newBuilder("js", new java.io.File(libraryPath)).build());
    }

    public static void loadPythonLibrary(PythonInterpreter interpreter, String libraryPath) {
        interpreter.execfile(libraryPath);
    }
}
