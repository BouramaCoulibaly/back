package com.orange.orange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.orange.orange.repository.KpiDataRepository;
import com.orange.orange.model.KpiData;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:50486")

@RestController
@RequestMapping("/api")
public class KpiDataController {
    @Autowired  KpiDataRepository kpiDataRepository;
    @GetMapping("/file_info")
    public ResponseEntity<List<KpiData>> getAllListe(){
        List<KpiData> file_info = kpiDataRepository.findAll();
        return new ResponseEntity<>(file_info, HttpStatus.OK);
    }
    @GetMapping("/uniqueDbNames")
    public ResponseEntity<Set<String>> getUniqueDbNames() {
        Set<String> uniqueDbNames = kpiDataRepository.findUniqueDbNames(); // Implement this repository method
        return new ResponseEntity<>(uniqueDbNames, HttpStatus.OK);
    }
    @GetMapping("/relatedTableNames/{dbName}")
    public ResponseEntity<Set<String>> getRelatedTableNames(@PathVariable String dbName) {
        Set<String> relatedTableNames = kpiDataRepository.findRelatedTableNamesByDbName(dbName); // Implement this repository method
        return new ResponseEntity<>(relatedTableNames, HttpStatus.OK);
    }

    @GetMapping("/uniqueExportDays")
    public ResponseEntity<List<LocalDate>> getUniqueExportDays() {
        List<LocalDate> uniqueExportDays = kpiDataRepository.findUniqueExportDays(); // Implement this repository method
        return new ResponseEntity<>(uniqueExportDays, HttpStatus.OK);
    }

    @GetMapping("/helloi")
    public String hello(){
        return "helloi kpi";
    }
    @PostMapping("/file_info")
    public ResponseEntity<KpiData> createKpiData(@RequestBody KpiData kpiData) {
        KpiData createdKpiData = kpiDataRepository.save(kpiData);
        return new ResponseEntity<>(createdKpiData, HttpStatus.CREATED);
    }

    @GetMapping("/uniqueTableNames")
    public ResponseEntity<Set<String>> getUniqueTableNames() {
        Set<String> uniqueTableNames = kpiDataRepository.findUniqueTableNames();
        return new ResponseEntity<>(uniqueTableNames, HttpStatus.OK);
    }

    @GetMapping("/uniqueYears")
    public ResponseEntity<Set<Integer>> getUniqueYears() {
        Set<Integer> uniqueYears = kpiDataRepository.findUniqueYears(); // Implement this repository method
        return new ResponseEntity<>(uniqueYears, HttpStatus.OK);
    }


    @GetMapping("/uniqueMonths")
    public ResponseEntity<Set<String>> getUniqueMonths() {
        Set<String> uniqueMonths = kpiDataRepository.findUniqueMonths(); // Implement this repository method
        return new ResponseEntity<>(uniqueMonths, HttpStatus.OK);
    }

    @PostMapping("/updateSeuil")
    public ResponseEntity<String> updateSeuil(@RequestBody Map<String, Object> request) {
        String dbName = (String) request.get("dbname");
        String tableName = (String) request.get("tablename");
        int year = (int) request.get("year");
        String monthStr = (String) request.get("month"); // Accept month as a string

        // Convert the month string to its corresponding integer value
        int month = monthStringToNumber(monthStr);

        double seuil = (Double) request.get("seuil");

        // Find the KpiData record based on the provided criteria
        KpiData kpiData = (KpiData) kpiDataRepository.findByDbNameAndTableNameAndYearAndMonth(dbName, tableName, year, month);

        if (kpiData != null) {
            // Update the 'seuil' value
            kpiData.setSeuil(seuil);
            kpiDataRepository.save(kpiData);
            return new ResponseEntity<>("Seuil updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("KpiData record not found", HttpStatus.NOT_FOUND);
        }
    }

    // Helper function to convert month string to number
    private int monthStringToNumber(String monthStr) {
        switch (monthStr.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month string: " + monthStr);
        }
    }
    @GetMapping("/relativeYears/{month}")
    public ResponseEntity<Set<Integer>> getRelativeYearsByMonth(@PathVariable String month) {
        int monthNumber = monthStringToNumber(month); // Convert month string to number

        Set<Integer> relativeYears = kpiDataRepository.findRelativeYearsByMonth(monthNumber);
        return new ResponseEntity<>(relativeYears, HttpStatus.OK);
    }

    @GetMapping("/relativeTableNames/{month}")
    public ResponseEntity<Set<String>> getRelativeTableNamesByMonth(@PathVariable String month) {
        int monthNumber = monthStringToNumber(month); // Convert month string to number

        Set<String> relativeTableNames = kpiDataRepository.findRelativeTableNamesByMonth(monthNumber);
        return new ResponseEntity<>(relativeTableNames, HttpStatus.OK);
    }

    @GetMapping("/relativeDbNames/{month}")
    public ResponseEntity<Set<String>> getRelativeDbNamesByMonth(@PathVariable String month) {
        int monthNumber = monthStringToNumber(month); // Convert month string to number

        Set<String> relativeDbNames = kpiDataRepository.findRelativeDbNamesByMonth(monthNumber);
        return new ResponseEntity<>(relativeDbNames, HttpStatus.OK);
    }

    @GetMapping("/relativeMonths/{year}")
    public ResponseEntity<Set<String>> getRelativeMonthsByYear(@PathVariable int year) {
        Set<String> relativeMonths = kpiDataRepository.findRelativeMonthsByYear(year);
        return new ResponseEntity<>(relativeMonths, HttpStatus.OK);
    }

    @GetMapping("/relativeTableNames/year/{year}")
    public ResponseEntity<Set<String>> getRelativeTableNamesByYear(@PathVariable int year) {
        Set<String> relativeTableNames = kpiDataRepository.findRelativeTableNamesByYear(year);
        return new ResponseEntity<>(relativeTableNames, HttpStatus.OK);
    }

    @GetMapping("/relativeDbNames/year/{year}")
    public ResponseEntity<Set<String>> getRelativeDbNamesByYear(@PathVariable int year) {
        Set<String> relativeDbNames = kpiDataRepository.findRelativeDbNamesByYear(year);
        return new ResponseEntity<>(relativeDbNames, HttpStatus.OK);
    }

    @GetMapping("/uniqueDbNamesByTableName/{tablename}")
    public ResponseEntity<Set<String>> getUniqueDbNamesByTableName(@PathVariable String tablename) {
        Set<String> uniqueDbNames = kpiDataRepository.findUniqueDbNamesByTableName(tablename); // Implement this repository method
        return new ResponseEntity<>(uniqueDbNames, HttpStatus.OK);
    }
    //
    @GetMapping("/relativeMonths/month/{tablename}")
    public ResponseEntity<Set<String>> getRelativeMonthsByTablename(@PathVariable String tablename) {
        Set<String> relativeMonths = kpiDataRepository.findRelativeMonthsByTablename(tablename);
        return new ResponseEntity<>(relativeMonths, HttpStatus.OK);
    }

    @GetMapping("/relativeYears/year/{tablename}")
    public ResponseEntity<Set<Integer>> getRelativeYearsByTablename(@PathVariable String tablename) {
        Set<Integer> relativeYears = kpiDataRepository.findRelativeYearsByTablename(tablename);
        return new ResponseEntity<>(relativeYears, HttpStatus.OK);
    }

    @GetMapping("/relativeYearsByDbname/{dbname}")
    public ResponseEntity<Set<Integer>> getRelativeYearsByDbname(@PathVariable String dbname) {
        Set<Integer> relativeYears = kpiDataRepository.findRelativeYearsByDbname(dbname);
        return new ResponseEntity<>(relativeYears, HttpStatus.OK);
    }

    //
    @GetMapping("/relativeMonthsByDbname/{dbname}")
    public ResponseEntity<Set<String>> getRelativeMonthsByDbname(@PathVariable String dbname) {
        Set<String> relativeMonths = kpiDataRepository.findRelativeMonthsByDbname(dbname);
        return new ResponseEntity<>(relativeMonths, HttpStatus.OK);
    }


}
