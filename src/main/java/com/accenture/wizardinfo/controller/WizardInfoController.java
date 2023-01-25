package com.accenture.wizardinfo.controller;

import brave.Tracer;
import com.accenture.wizardinfo.exception.WizardException;
import com.accenture.wizardinfo.repository.WizardInfoRepository;
import com.accenture.wizardinfo.entity.WizardInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Service
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

    @PostMapping("/add")
    public ResponseEntity<?> addWizard(@RequestBody WizardInfo wizard){
        WizardInfo newWizard = new WizardInfo();
        newWizard.setName(wizard.getName());
        newWizard.setAge(wizard.getAge());
        newWizard.setJoinedDate(new Date(System.currentTimeMillis()));

        return ResponseEntity.ok(repo.save(newWizard));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWizard(@PathVariable(value = "id") Long id){

        repo.deleteById(id);

        return ("Wizard info successfully deleted");
    }

    @PutMapping("/update")
    public WizardInfo updateWizard(@RequestBody WizardInfo wizardInfo) {
        repo.findById(wizardInfo.getId()).orElseThrow(() ->
                new WizardException(WizardException.INVALID_ID));

        return repo.save(wizardInfo);
    }

    /*@PutMapping("/update/{id}")
    public void updateWizard(@RequestBody WizardInfo wizard){
        *//*wizard.setName(wizard.getName());
        wizard.setAge(wizard.getAge());*//*
        repo.save(wizard);
    }*/
}
