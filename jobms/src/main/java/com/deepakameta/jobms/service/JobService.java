package com.deepakameta.jobms.service;

import com.deepakameta.jobms.data.Job;
import com.deepakameta.jobms.utils.JobException;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();

    String createJob(Job job);

    String updateJob(long jobId, Job job) throws JobException;

    String deleteJobById(long jobId)  throws JobException;
}
