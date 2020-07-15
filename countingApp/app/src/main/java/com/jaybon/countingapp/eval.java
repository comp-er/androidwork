package com.jaybon.countingapp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class eval {
    public static String cal (String result) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            return engine.eval(result).toString();
        }catch(Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
