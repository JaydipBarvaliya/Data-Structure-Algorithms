package com.competitive.coding.JDA;

import java.util.*;

/**
 * Created by jaine03 on 02/09/17.
 * This class will schedule the job as per customer request and the resource availability
 */
public class JobScheduler {

    static Map<Integer, Boolean> cpuCores, RAM, HDD;
    static Queue<JobRequest> pendingJobs = new LinkedList<>();
    static Integer TOTAL_THREADS = 5;

    // Initializing all resource available with the Company's Server to server customer JobsRequest
    static {
        cpuCores = new HashMap<>();
        cpuCores.put(2, true);
        cpuCores.put(4, true);
        cpuCores.put(8, true);

        RAM = new HashMap<>();
        RAM.put(2, true);
        RAM.put(4, true);
        RAM.put(8, true);

        HDD = new HashMap<>();
        HDD.put(1, true);
        HDD.put(2, true);
        HDD.put(4, true);
    }

    static class JobRequest {
        Integer requestId, customerId, requiredCore, requiredRAM, requiredHDD, duration;

        JobRequest(Integer requestId, Integer customerId, Integer requiredCore, Integer requiredRAM, Integer requiredHDD, Integer duration) {
            this.requestId = requestId;
            this.customerId = customerId;
            this.requiredCore = requiredCore;
            this.requiredRAM = requiredRAM;
            this.requiredHDD = requiredHDD;
            this.duration = duration;
        }
    }

    private static void scheduleJobs(List<JobRequest> jobRequests) {
        Queue<JobRequest> queue = new LinkedList<>(jobRequests);

        ScheduleJob scheduleJob = new ScheduleJob(queue);

        Thread[] allConsumerThread = new Thread[TOTAL_THREADS];

        for (int i = 0; i < TOTAL_THREADS; i++) {
            allConsumerThread[i] = new Thread(scheduleJob);
            allConsumerThread[i].start();
        }

        // Joining Threads so that Main thread will wait till all child threads are completed
        for (int i = 0; i < TOTAL_THREADS; i++) {
            try {
                allConsumerThread[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<JobRequest> jobRequests = Arrays.asList(
                new JobRequest(1, 1, 2, 8, 2, 20),
                new JobRequest(2, 2, 2, 4, 1, 7),
                new JobRequest(3, 3, 2, 4, 4, 4),
                new JobRequest(4, 4, 8, 2, 1, 15),
                new JobRequest(5, 5, 8, 8, 2, 8)
        );
        scheduleJobs(jobRequests);
    }
}

class ScheduleJob implements Runnable {

    private Queue<JobScheduler.JobRequest> queue;

    ScheduleJob(Queue<JobScheduler.JobRequest> queue) {
        this.queue = queue;
    }

    /**
     * 1) Check if Resource for Selected Job is available
     * 2) If Available :
     * 2.a) BlockResources for the duration selectedJob will execute.
     * 2.b) To mimic Job Execution Putting current thread into sleep for the specified duration(in seconds)
     * 2.c) Once Sleep time is over, free blocked resource.
     * 2.d) Check the pendingJobs queue waiting due to the resource unavailability for the Jobs, fetch out the oldestJob which was waiting.
     * 2.e) For these pendingJobs spawn a newThread which will do the scheduling as per resource availability
     * <p>
     * 3) if Resource NotAvailable :
     * 3.a) Put the Job into pendingJob List.
     */
    @Override
    public void run() {

        while (!queue.isEmpty()) {
            try {

                JobScheduler.JobRequest jobRequest = queue.poll();
                if (jobRequest != null) {
                    if (isResourceAvailable(jobRequest)) {

                        reserveResource(jobRequest);
                        System.out.println(Thread.currentThread().getName() + " Executing Job " + jobRequest.requestId + " consuming  " + getResourceInfoConsumedByJob(jobRequest)
                                + " for " + jobRequest.duration + " seconds....." + " :: " + new Date());
                        Thread.sleep(jobRequest.duration * 1000);
                        System.out.println(Thread.currentThread().getName() + " Job completed for JobId  " + jobRequest.requestId);
                        freeResource(jobRequest);

                        if (!JobScheduler.pendingJobs.isEmpty()) {
                            Thread pendingJobsThread = new Thread(new ScheduleJob(JobScheduler.pendingJobs));
                            pendingJobsThread.start();
                            pendingJobsThread.join();
                        }
                    } else {
                        //System.out.println(Thread.currentThread().getName() + " Waiting Job " + jobRequest.requestId + "::CORE:" + jobRequest.requiredCore + "::RAM:" + jobRequest.requiredRAM + "::HDD:" + jobRequest.requiredHDD);
                        JobScheduler.pendingJobs.add(jobRequest);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getResourceInfoConsumedByJob(JobScheduler.JobRequest jobRequest) {
        return "Resource : [CPU :: " + jobRequest.requiredCore + "core, RAM :: " + jobRequest.requiredRAM + "GB, HDD - " + jobRequest.requiredHDD + "TB]";
    }

    /**
     * Method to check for a particular JobRequest of a customer is resourceAvailable for executing the JOB
     *
     * @param jobRequest for which resource availability needs to be determined
     * @return true if resource is available else false
     */
    private synchronized Boolean isResourceAvailable(JobScheduler.JobRequest jobRequest) {
        //System.out.println("Checking Resource Availability "+Thread.currentThread().getName()+":::::"+jobRequest.requestId);
        return JobScheduler.cpuCores.get(jobRequest.requiredCore) &&
                JobScheduler.RAM.get(jobRequest.requiredRAM) &&
                JobScheduler.HDD.get(jobRequest.requiredHDD);
    }

    /**
     * Method to block the availableResource which is being used by the Job during the period of execution
     *
     * @param jobRequest for which we have to reserveResource
     */
    private synchronized void reserveResource(JobScheduler.JobRequest jobRequest) {
        //System.out.println(Thread.currentThread().getName() + " Booking Resource for JOB " + jobRequest.requestId + " CORE:: " + jobRequest.requiredCore + " RAM:: " + jobRequest.requiredRAM + " HDD::" + jobRequest.requiredHDD);
        JobScheduler.cpuCores.put(jobRequest.requiredCore, false);
        JobScheduler.RAM.put(jobRequest.requiredRAM, false);
        JobScheduler.HDD.put(jobRequest.requiredHDD, false);
    }

    /**
     * Method to un-block the blockedResource which were being used by the specified jobRequest
     *
     * @param jobRequest for which we have to un-block the resource
     */
    private synchronized void freeResource(JobScheduler.JobRequest jobRequest) {
        //System.out.println(Thread.currentThread().getName() + " Releasing Resource for JOB " + jobRequest.requestId + " CORE:: " + jobRequest.requiredCore + " RAM:: " + jobRequest.requiredRAM + " HDD::" + jobRequest.requiredHDD);
        JobScheduler.cpuCores.put(jobRequest.requiredCore, true);
        JobScheduler.RAM.put(jobRequest.requiredRAM, true);
        JobScheduler.HDD.put(jobRequest.requiredHDD, true);


        // If the request came from PendingJobs the removing it from the list
        JobScheduler.pendingJobs.remove(jobRequest);
    }
}
