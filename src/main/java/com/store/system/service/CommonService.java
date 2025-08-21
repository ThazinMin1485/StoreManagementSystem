package com.store.system.service;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class CommonService {
    public <T> ResponseEntity<byte[]> addPdfData(List<T> dataList, String[] headers, Function<T, List<String>> rowMappers, int index, float[] numbers) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument document = new PdfDocument(pdfWriter);
        Document document1 = new Document(document);
        Color headerColor = ColorConstants.BLACK;
        Color fontColor = ColorConstants.GRAY;
        Table table = new Table(UnitValue.createPercentArray(numbers)).useAllAvailableWidth();
        table.setBorder(Border.NO_BORDER);
        table.setMargin(0);
        table.setPadding(0);
        for (String col : headers) {
            Cell cell = new Cell().add(new Paragraph(col)
                    .setFontColor(fontColor)
                    .setBold());
            cell.setBackgroundColor(headerColor).setBorder(Border.NO_BORDER);
            table.addHeaderCell(cell);
        }
        int rowIndex = 0;
        for (T item : dataList) {
            Color bg = (rowIndex % 2 == 0) ? new DeviceRgb(230, 230, 230) : ColorConstants.WHITE;
            List<String> values = rowMappers.apply(item);
            values.add(0, String.valueOf(index));
            for (String val : values) {
                Cell c = new Cell().add(new Paragraph(val == null ? "" : val)).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);
                table.addCell(c);
            }
            index++;
            rowIndex++;
        }
        document1.add(table);
        document1.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(baos.toByteArray(), httpHeaders, HttpStatus.OK);
    }

    public <T>void writeExcelData(List<T> dataList, String[] headers, Function<T, List<String>> rowMappers, int index, int[] numbers, int page, HttpServletResponse response, String sheetName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName + page + 1);
            for (int i=0; i<numbers.length; i++) {
                sheet.setColumnWidth(i, numbers[i] * 256);
            }
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setWrapText(true);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderLeft(BorderStyle.NONE);
            headerStyle.setBorderRight(BorderStyle.NONE);
            headerStyle.setBorderTop(BorderStyle.NONE);
            headerStyle.setBorderBottom(BorderStyle.NONE);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);

            Row row = sheet.createRow(0);
            row.setHeightInPoints(25);
            for (int i = 0; i < headers.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            int rowIndex = 1;
            int stripIndex = 0;
            for (T item : dataList) {
                CellStyle rowStyle = workbook.createCellStyle();
                rowStyle.setWrapText(true);
                rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                rowStyle.setAlignment(HorizontalAlignment.CENTER);
                rowStyle.setBorderLeft(BorderStyle.NONE);
                rowStyle.setBorderRight(BorderStyle.NONE);
                rowStyle.setBorderTop(BorderStyle.NONE);
                rowStyle.setBorderBottom(BorderStyle.NONE);
                if (stripIndex % 2 == 0) {
                    rowStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                }
                List<String> values = new ArrayList<>(rowMappers.apply(item));
                values.add(0, String.valueOf(index++));
                Row row1 = sheet.createRow(rowIndex++);
                row1.setHeightInPoints(25);
                for(int i=0; i < values.size(); i++) {
                    row1.createCell(i).setCellValue(values.get(i));
                    row1.getCell(i).setCellStyle(rowStyle);
                }
                stripIndex++;
            }
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
