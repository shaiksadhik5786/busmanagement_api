package com.web.sms.controller;

import com.web.sms.entity.Incharge;
import com.web.sms.service.InchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/incharge")
public class InchargeController {

    @Autowired
    private InchargeService inchargeService;

    @PostMapping("/")
    public ResponseEntity<Incharge> addIncharge(@RequestBody Incharge incharge) {
        Incharge saved = inchargeService.addIncharge(incharge);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incharge> updateIncharge(@PathVariable("id") long id, @RequestBody Incharge incharge) {
        incharge.setId(id);
    	Incharge updated = inchargeService.updateIncharge(incharge);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incharge> getInchargeById(@PathVariable("id") long id) {
        Incharge incharge = inchargeService.getInchargeById(id);
        return new ResponseEntity<>(incharge, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInchargeById(@PathVariable("id") long id) {
        inchargeService.deleteInchargeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<Incharge>> getAllIncharges() {
        List<Incharge> incharges = inchargeService.getAllIncharges();
        return new ResponseEntity<>(incharges, HttpStatus.OK);
    }
}
