package com.apiwiz.cseazeem.script_engine.controller;

import com.apiwiz.cseazeem.script_engine.model.ScriptRequest;
import com.apiwiz.cseazeem.script_engine.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.io.IOException;

@RestController
@RequestMapping("/api/script")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @PostMapping("/run")
    public ResponseEntity<Object> runScript(@RequestBody ScriptRequest request) {
        try {
            Object result = scriptService.runScript(
                    request.getLanguage(),
                    request.getScript(),
                    request.isFile()
            );
            return ResponseEntity.ok(result);
        } catch (ScriptException | IOException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
