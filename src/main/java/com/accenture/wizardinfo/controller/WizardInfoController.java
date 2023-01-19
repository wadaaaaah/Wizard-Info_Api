package com.accenture.wizardinfo.controller;

import brave.Tracer;
import com.accenture.wizardinfo.repository.WizardInfoRepository;
import com.accenture.wizardinfo.entity.WizardInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/add")
    public void addWizard(@RequestBody WizardInfo wizard){
        WizardInfo newWizard = new WizardInfo();
        newWizard.setName(wizard.getName());
        newWizard.setAge(wizard.getAge());
        newWizard.setJoinedDate(new Date(System.currentTimeMillis()));

        repo.save(newWizard);

        //return
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWizard(@PathVariable(value = "id") Long id){

        repo.deleteById(id);
    }

    /*@PutMapping("/update/{id}")
        public ResponseEntity<WizardInfo> updateWizard (@PathVariable(value = "id") Long wizardId,
                @Valid @RequestBody WizardInfo wizardDetails) throws ResourseNotFoundException {
            WizardInfo wizard = repo.findById(wizardId).orElseThrow(() ->
                    new ResourseNotFoundException("Wizard not found"));
        }*/

    @PutMapping("/update/{id}")
    public void updateWizard(@RequestBody WizardInfo wizard){
        /*wizard.setName(wizard.getName());
        wizard.setAge(wizard.getAge());*/
        repo.save(wizard);
    }
}
