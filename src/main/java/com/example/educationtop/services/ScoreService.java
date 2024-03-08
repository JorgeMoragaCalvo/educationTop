package com.example.educationtop.services;

import com.example.educationtop.entities.ScoreEntity;
import com.example.educationtop.repositories.ScoreRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class ScoreService {

    public final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }

    public List<ScoreEntity> allScores(){
        return scoreRepository.findAll();
    }

    public void processingExcelFile() throws IOException {
        try (InputStream inputStream = new ClassPathResource("/excel-files/scores-data.xlsx").getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                processingSheet(sheet);
            }
        }
    }

    private void processingSheet(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        // Ignorar la primera fila
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            processingRow(row);
        }
    }

    private void processingRow(Row row) {
        Cell rutCell = row.getCell(1);
        Cell puntajeCell = row.getCell(3);

        //Verificar si las celdas no son nulas
        if (rutCell != null && puntajeCell != null) {
            String rut = rutCell.getStringCellValue();
            int puntaje = (int) puntajeCell.getNumericCellValue();

            //Persistir en la base de datos
            persistOnDataBase(rut, puntaje);
        } else {
            System.err.println("Al menos una de las celdas es nula en la fila " + row.getRowNum());
        }
    }

    private void persistOnDataBase(String rut, int puntaje) {
        ScoreEntity score = scoreRepository.findByRut(rut);

        if (score == null) {
            score = new ScoreEntity();
            score.setRut(rut);
            score.setTotalExams(1);
            score.setAverageExams(puntaje);
        } else {
            int tests = score.getTotalExams();
            double promedioActual = score.getAverageExams();
            double nuevoPromedio = (promedioActual * tests + puntaje) / (tests + 1);

            score.setTotalExams(tests + 1);
            score.setAverageExams(nuevoPromedio);
        }
        scoreRepository.save(score);
    } 
}