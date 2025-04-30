package com.apiwiz.cseazeem.script_engine.service;
//
//import com.apiwiz.cseazeem.script_engine.util.ScriptEngineFactory;
//import org.graalvm.polyglot.Context;
//import org.graalvm.polyglot.Value;
//import org.python.util.PythonInterpreter;
//import org.springframework.stereotype.Service;
//
//import javax.script.ScriptException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Service
//public class ScriptService {
//
//    private static final String JS_LIBRARY_PATH = "src/main/resources/scripts/lodash.js";
//    private static final String PY_LIBRARY_PATH = "src/main/resources/scripts/math.py";
//
//    public Object runScript(String language, String script, boolean isFile) throws ScriptException, IOException {
//        if (isFile) {
//            script = new String(Files.readAllBytes(Paths.get(script)));
//        }
//
//        if ("JavaScript".equalsIgnoreCase(language)) {
//            return runJavaScript(script);
//        } else if ("python".equalsIgnoreCase(language)) {
//            return runPython(script);
//        } else {
//            throw new IllegalArgumentException("Unsupported language: " + language);
//        }
//    }
//
//    private Object runJavaScript(String script) throws ScriptException, IOException {
//        try (Context context = ScriptEngineFactory.getJavaScriptContext()) {
//            // Load external library (e.g., lodash)
//            ScriptEngineFactory.loadJavaScriptLibrary(context, JS_LIBRARY_PATH);
//
//            // Execute script
//            Value result = context.eval("js", script);
//            return result.as(Object.class);
//        }
//    }
//
//    private Object runPython(String script) throws IOException {
//        try (PythonInterpreter interpreter = ScriptEngineFactory.getPythonInterpreter()) {
//            // Load external library (e.g., math)
//            ScriptEngineFactory.loadPythonLibrary(interpreter, PY_LIBRARY_PATH);
//
//            // Execute script and get result
//            interpreter.exec(script);
//            org.python.core.PyObject pyResult = interpreter.get("result");
//            return pyResult != null ? pyResult.__tojava__(Object.class) : null;
//        }
//    }
//}

import com.apiwiz.cseazeem.script_engine.util.ScriptEngineFactory;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScriptService {

    private static final String JS_LIBRARY_PATH = "src/main/resources/scripts/lodash.js";
    private static final String PY_LIBRARY_PATH = "src/main/resources/scripts/math.py";

    public Object runScript(String language, String script, boolean isFile) throws ScriptException, IOException {
        if (isFile) {
            script = new String(Files.readAllBytes(Paths.get(script)));
        }

        if ("JavaScript".equalsIgnoreCase(language)) {
            return runJavaScript(script);
        } else if ("python".equalsIgnoreCase(language)) {
            return runPython(script);
        } else {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    private Object runJavaScript(String script) throws ScriptException, IOException {
        try (Context context = ScriptEngineFactory.getJavaScriptContext()) {
            // Load external library (e.g., lodash)
            ScriptEngineFactory.loadJavaScriptLibrary(context, JS_LIBRARY_PATH);

            // Execute script
            Value result = context.eval("js", script);
            // Convert result to a plain Java object before closing context
            return convertValueToJavaObject(result);
        }
    }

    private Object runPython(String script) throws IOException {
        try (PythonInterpreter interpreter = ScriptEngineFactory.getPythonInterpreter()) {
            // Load external library (e.g., math)
            ScriptEngineFactory.loadPythonLibrary(interpreter, PY_LIBRARY_PATH);

            // Execute script and get result
            interpreter.exec(script);
            org.python.core.PyObject pyResult = interpreter.get("result");
            return pyResult != null ? pyResult.__tojava__(Object.class) : null;
        }
    }

    private Object convertValueToJavaObject(Value value) {
        if (value.isNull()) {
            return null;
        } else if (value.isBoolean()) {
            return value.asBoolean();
        } else if (value.isNumber()) {
            if (value.fitsInInt()) {
                return value.asInt();
            } else if (value.fitsInLong()) {
                return value.asLong();
            } else {
                return value.asDouble();
            }
        } else if (value.isString()) {
            return value.asString();
        } else if (value.hasArrayElements()) {
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < value.getArraySize(); i++) {
                list.add(convertValueToJavaObject(value.getArrayElement(i)));
            }
            return list;
        } else if (value.hasMembers()) {
            Map<String, Object> map = new HashMap<>();
            for (String key : value.getMemberKeys()) {
                map.put(key, convertValueToJavaObject(value.getMember(key)));
            }
            return map;
        } else {
            return value.as(Object.class); // Fallback for other types
        }
    }
}
