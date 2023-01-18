package com.accenture.wizardinfo.controller;

import brave.Tracer;
import com.accenture.wizardinfo.repository.WizardInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/WizardInfoApi")
public class WizardInfoController {

    private final WizardInfoRepository repo;

    Tracer tracer;

    public WizardInfoController(WizardInfoRepository repo, Tracer tracer) {
        this.repo = repo;
        this.tracer = tracer;
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        System.out.println(tracer.currentSpan().context().traceIdString());
        return ResponseEntity.ok(repo.findById(id).get());
    }
}
