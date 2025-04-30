package com.apiwiz.cseazeem.script_engine.model;

public class ScriptRequest {
    private String language;
    private String script;
    private boolean isFile;

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}
