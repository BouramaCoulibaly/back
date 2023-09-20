package com.orange.orange;

import com.orange.orange.model.KpiData;
import com.orange.orange.repository.KpiDataRepository;
import jakarta.annotation.PostConstruct;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class test implements CommandLineRunner {

    @Autowired
    KpiDataRepository kpiDataRepository;
    //@PostConstruct

/*    public List<KpiData> getFileByTab(String tabName,List<KpiData> files){
        List<KpiData> fileOfTab = files.stream().filter(value->tabName.equals(value.getDbname().trim())).toList();
        return fileOfTab;
    }*/

    @Override
    public void run(String... args) throws Exception {
        System.out.println("here is the entry................");
        List<KpiData> file_info = kpiDataRepository.findAll();
        Date d = new Date();
        File file = new File("/Users/sow080987/Desktop/bourama_kpi/back/orange/src/main/resources/export.xlsx");
        FileInputStream fis = new FileInputStream(file);
        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workBook = new XSSFWorkbook (fis);
        XSSFSheet sheet = workBook.createSheet("db1");
        System.out.println("......................."+sheet.getSheetName());
        Iterator<Row> rowIt = sheet.iterator();
        //List<KpiData> fileOfDb1 = file_info.stream().filter(value->"db1".equals(value.getDbname().trim())).toList();
        List<String> db1Data = kpiDataRepository.findRelatedTableNamesByDbName2("db1");
        System.out.println("here is my list ::::::::::::: "+db1Data.size());

        int j =0;
       while(db1Data.size()>j){

            String tabName=db1Data.get(j);
            System.out.println("here is the name :::::::::::::::::: "+tabName);
            List<KpiData> dataList = kpiDataRepository.findRelatedKpiByTabName(tabName);
            System.out.println("here is my dataList ::::::::: "+dataList.size());
          int i =0;

            while(dataList.size()>i){
                Row row = sheet.createRow(i);
                int cellCompteur = 0;
                Cell cell = row.createCell(cellCompteur);
                cell.setCellValue(dataList.get(i).getFileSize());
                cellCompteur++;
                cell = row.createCell(cellCompteur);
                cell.setCellValue(dataList.get(i).getNbFiles());
                i++;
            }
/*           Cell cell = row.createCell(0);
           CellStyle cs = workBook.createCellStyle();
           cs.setWrapText(true);
           cell.setCellStyle(cs);*/
            j++;
       }

        FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
        workBook.write(fileOut);
    }
}
