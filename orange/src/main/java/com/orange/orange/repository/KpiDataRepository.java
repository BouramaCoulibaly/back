package com.orange.orange.repository;

import com.orange.orange.model.KpiData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface KpiDataRepository extends JpaRepository<KpiData, Long> {
    @Query("SELECT DISTINCT k.dbname FROM KpiData k")
    Set<String> findUniqueDbNames();
    @Query("SELECT DISTINCT k.tablename FROM KpiData k WHERE k.dbname = :dbName")
    Set<String> findRelatedTableNamesByDbName(String dbName);
   /* @Query("SELECT DISTINCT k.exportday FROM KpiData k")
    Set<String> findUniqueExportDays();*/
    @Query("SELECT DISTINCT k.exportday FROM KpiData k")
    List<LocalDate> findUniqueExportDays();

    @Modifying
    @Transactional
    @Query("UPDATE KpiData k SET k.seuil = :newSeuil WHERE k.exportday = :exportDay AND k.dbname = :dbname AND k.tablename = :tablename")
    void updateSeuil(LocalDate exportDay, String dbname, String tablename, double newSeuil);

    @Query("SELECT DISTINCT kd.tablename FROM KpiData kd")
    Set<String> findUniqueTableNames();

    @Query("SELECT DISTINCT YEAR(k.exportday) FROM KpiData k")
    Set<Integer> findUniqueYears();

    @Query("SELECT DISTINCT MONTHNAME(k.exportday) FROM KpiData k")
    Set<String> findUniqueMonths();

    /*@Transactional
    @Modifying
    @Query("UPDATE KpiData k " +
            "SET k.seuil = :seuil " +
            "WHERE k.dbName = :dbName " +
            "AND k.tableName = :tableName " +
            "AND k.year = :year " +
            "AND k.month = :month")
    int updateSeuil(
            @Param("seuil") double seuil,
            @Param("dbName") String dbName,
            @Param("tableName") String tableName,
            @Param("year") int year,
            @Param("month") String month
    );*/

    @Query("SELECT k FROM KpiData k WHERE k.dbname = :dbname AND k.tablename = :tablename " +
            "AND YEAR(k.exportday) = :year AND MONTH(k.exportday) = :month")
    KpiData findByDbNameAndTableNameAndYearAndMonth(
            @Param("dbname") String dbname,
            @Param("tablename") String tablename,
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("SELECT DISTINCT YEAR(k.exportday) FROM KpiData k WHERE MONTH(k.exportday) = :month")
    Set<Integer> findRelativeYearsByMonth(@Param("month") int month);
    @Query("SELECT DISTINCT k.tablename FROM KpiData k WHERE MONTH(k.exportday) = :month")
    Set<String> findRelativeTableNamesByMonth(@Param("month") int month);
    @Query("SELECT DISTINCT k.dbname FROM KpiData k WHERE MONTH(k.exportday) = :month")
    Set<String> findRelativeDbNamesByMonth(@Param("month") int month);

    @Query("SELECT DISTINCT MONTHNAME(k.exportday) FROM KpiData k WHERE YEAR(k.exportday) = :year")
    Set<String> findRelativeMonthsByYear(@Param("year") int year);

    @Query("SELECT DISTINCT k.tablename FROM KpiData k WHERE YEAR(k.exportday) = :year")
    Set<String> findRelativeTableNamesByYear(@Param("year") int year);

    @Query("SELECT DISTINCT k.dbname FROM KpiData k WHERE YEAR(k.exportday) = :year")
    Set<String> findRelativeDbNamesByYear(@Param("year") int year);

 @Query("SELECT DISTINCT k.dbname FROM KpiData k WHERE k.tablename = :tablename")
 Set<String> findUniqueDbNamesByTableName(@Param("tablename") String tablename);
 //
 @Query("SELECT DISTINCT MONTHNAME(k.exportday) FROM KpiData k WHERE k.tablename = :tablename")
 Set<String> findRelativeMonthsByTablename(@Param("tablename") String tablename);
 //
 @Query("SELECT DISTINCT YEAR(k.exportday) FROM KpiData k WHERE k.tablename = :tablename")
 Set<Integer> findRelativeYearsByTablename(@Param("tablename") String tablename);

 @Query("SELECT DISTINCT YEAR(k.exportday) FROM KpiData k WHERE k.dbname = :dbname")
 Set<Integer> findRelativeYearsByDbname(@Param("dbname") String dbname);

 @Query("SELECT DISTINCT MONTHNAME(k.exportday) FROM KpiData k WHERE k.dbname = :dbname")
 Set<String> findRelativeMonthsByDbname(@Param("dbname") String dbname);


}