package com.deepakameta.jobms.controller;


import com.deepakameta.jobms.data.Job;
import com.deepakameta.jobms.service.JobService;
import com.deepakameta.jobms.utils.JobException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @PostMapping
    public ResponseEntity<String> saveJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJobById(@PathVariable("jobId") Long jobId, @RequestBody Job job) {
        try {
            String result = jobService.updateJob(jobId, job);
            return ResponseEntity.ok(result);
        } catch (JobException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable("jobId") Long jobId) {
        try {
            String result = jobService.deleteJobById(jobId);
            return ResponseEntity.ok(result);
        } catch (JobException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
