package com.orange.orange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="dienfa")

public class KpiData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "file_size")
    private double fileSize;
    @Column(name="nb_files")
    private int nbFiles;
    @Column(name = "nb_lines")
    private int nbLines;
    @Column(name = "moy")
    private double moy;
    @Column(name="seuil")
    private double seuil;
    @Column(name="exportday")
    private LocalDate  exportday;

    public void setId(int id) {
        this.id = id;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public void setNbFiles(int nbFiles) {
        this.nbFiles = nbFiles;
    }

    public void setNbLines(int nbLines) {
        this.nbLines = nbLines;
    }

    public void setMoy(double moy) {
        this.moy = moy;
    }

    public void setExportday(LocalDate exportday) {
        this.exportday = exportday;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setD_d(double d_d) {
        this.d_d = d_d;
    }

    public int getId() {
        return id;
    }

    public double getFileSize() {
        return fileSize;
    }

    public int getNbFiles() {
        return nbFiles;
    }

    public int getNbLines() {
        return nbLines;
    }

    public double getMoy() {
        return moy;
    }

    public double getSeuil() {
        return seuil;
    }

    public LocalDate getExportday() {
        return exportday;
    }

    public String getDbname() {
        return dbname;
    }

    public String getTablename() {
        return tablename;
    }

    public double getD_d() {
        return d_d;
    }

    @Column(name = "dbname")
    private String dbname;
    @Column(name = "tablename")
    private String tablename;

    @Column(name="d_d")
    private double d_d;
    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }



}
