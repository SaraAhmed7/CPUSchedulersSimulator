package org.example;

import org.example.process.SJFTSchedule;
import org.example.schedules.*;
import org.example.process.MProcess;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<MProcess> processes1 = new ArrayList<>();
        System.out.println("Program starts...");
        System.out.println();
        System.out.print("Enter number of process: ");
        int numberOfProcess = scanner.nextInt();
        System.out.print("Enter Round Robin Time Quantum: ");
        int roundRobinTimeQuantum = scanner.nextInt();
        System.out.print("Enter Context Switching Time: ");
        int contextSwitching = scanner.nextInt();
        scanner.nextLine();
        //
        System.out.println("\n...............\n");
        for(int i = 1; i <= numberOfProcess; i++){
            System.out.print("Enter process #"+i+" Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter process #"+i+" Arrival Time: ");
            double arrivalTime = scanner.nextDouble();
            System.out.print("Enter process #"+i+" Burst Time: ");
            double burstTime = scanner.nextDouble();
            System.out.print("Enter process #"+i+" Process Priority: ");
            double priorityTime = scanner.nextDouble();
            System.out.print("Enter Time Quantum: ");
            double quantum = scanner.nextDouble();
            scanner.nextLine();
            MProcess tmpProcess = new MProcess(name,arrivalTime,burstTime,priorityTime, quantum);
            processes1.add(tmpProcess);
            System.out.println("..........");
        }
        while(true){
            ScheduleTechnique technique = null;
            System.out.println("Enter your choice: ");
            System.out.println("[1]Shortest Job First(SJF)");
            System.out.println("[2]Round Robin(RR)");
            System.out.println("[3]Preemptive Priority");
            System.out.println("[4]AG Scheduling");
            System.out.println("[0]Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            if(!(0<=choice&&choice<=4)){
                System.out.println("..............");
                System.out.println("Wrong input, try again!!!");
                System.out.println("..............");
                continue;
            }
            List<MProcess> processes = new ArrayList<>();
            for (MProcess process : processes1) {
                processes.add(new MProcess(process.getProcessName(),
                        process.getArrivalTime(),
                        process.getBurstTime(),
                        process.getProcessPriority(),
                        process.getRealTimeQuantum()));
            }
            switch (choice){
                case 1 -> technique = new SJFTSchedule(processes, contextSwitching);
                case 2 -> technique = new RoundRobinSchedule(processes,contextSwitching, roundRobinTimeQuantum);
                case 3 -> technique = new PreemptiveSchedule(processes,contextSwitching);
                case 4 -> technique = new AGSchedule(processes,contextSwitching);
                case 0 -> {
                    System.out.println("Program terminates...");
                    scanner.close();
                    System.exit(0);
                }
            }
            technique.run();
        }
    }
}