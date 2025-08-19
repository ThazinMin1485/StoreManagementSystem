package com.store.system.controller;

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
import com.store.system.entity.Good;
import com.store.system.service.GoodService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class FileDownloadController {

    @Autowired
    private GoodService goodService;

    Date date = new Date();
    Long milliseconds = date.getTime();

    @GetMapping("/good/download/pdf")
    public ResponseEntity<byte[]> downloadPdf( @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Good> goodPage = goodService.getGoodList(pageable);
        List<Good> goodList = goodPage.getContent();
        int index = page * size + 1;
        return addGoodPdfData(goodList, index);
    }

    @GetMapping("/good/download/excel")
    public void downloadExcel(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size, HttpServletResponse response) throws IOException {
        Pageable pageable = PageRequest.of(page, size);
        Page<Good> goodPage = goodService.getGoodList(pageable);
        List<Good> goodList = goodPage.getContent();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=goods.xlsx");
        int index = page * size + 1;
        writeExcelGoodData(goodList, index, page, response);
    }

    public ResponseEntity<byte[]> addGoodPdfData(List<Good> goodList, int index) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument document = new PdfDocument(pdfWriter);
        Document document1 = new Document(document);
        Color headerColor = ColorConstants.BLACK;
        Color fontColor = ColorConstants.GRAY;
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2, 2,1,2})).useAllAvailableWidth();
        table.setBorder(Border.NO_BORDER);
        table.setMargin(0);
        table.setPadding(0);
        String[] headers = {"ID","Name", "Quantity", "Kg", "Category"};
        for (String col : headers) {
            Cell cell = new Cell().add(new Paragraph(col)
                    .setFontColor(fontColor)
                    .setBold());
            cell.setBackgroundColor(headerColor).setBorder(Border.NO_BORDER);
            table.addHeaderCell(cell);
        }
        int rowIndex = 0;
        for(Good g: goodList) {
            Color bg = (rowIndex % 2 == 0) ? new DeviceRgb(230, 230, 230) : ColorConstants.WHITE;
            Cell c1 = new Cell().add(new Paragraph(String.valueOf(index))).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);
            Cell c2 = new Cell().add(new Paragraph(g.getGood_name())).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);
            Cell c3 = new Cell().add(new Paragraph(g.getQuantity() == null ? "" : String.valueOf(g.getQuantity()))).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);
            Cell c4 = new Cell().add(new Paragraph(g.getKg() == null ? "" : String.valueOf(g.getKg()))).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);
            Cell c5 = new Cell().add(new Paragraph(g.getCategory().getCategory_name())).setBackgroundColor(bg).setBorder(Border.NO_BORDER).setPadding(5);

            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);
            table.addCell(c4);
            table.addCell(c5);
            index ++;
            rowIndex++;
        }
        document1.add(table);
        document1.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(baos.toByteArray(), httpHeaders, HttpStatus.OK);
    }

    public void writeExcelGoodData(List<Good> goodList, int index, int page, HttpServletResponse response) {
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Goods List" + page + 1);
            sheet.setColumnWidth(0, 10 * 256); // ID column
            sheet.setColumnWidth(1, 20 * 256); // Name column
            sheet.setColumnWidth(2, 20 * 256); // Quantity column
            sheet.setColumnWidth(3, 10 * 256); // Kg column
            sheet.setColumnWidth(4, 20 * 256);
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

            String[] headers = {"ID","Name", "Quantity", "Kg", "Category"};
            Row row = sheet.createRow(0);
            row.setHeightInPoints(25);
            for (int i=0; i< headers.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            int rowIndex = 1;
            int stripIndex = 0;
            for(Good good: goodList) {
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
                Row row1 = sheet.createRow(rowIndex++);
                row1.setHeightInPoints(25);
                row1.createCell(0).setCellValue(index++);
                row1.getCell(0).setCellStyle(rowStyle);
                row1.createCell(1).setCellValue(good.getGood_name());
                row1.getCell(1).setCellStyle(rowStyle);
                row1.createCell(2).setCellValue(good.getQuantity() == null ? "" : String.valueOf(good.getQuantity()));
                row1.getCell(2).setCellStyle(rowStyle);
                row1.createCell(3).setCellValue(good.getKg() == null ? "" : String.valueOf(good.getKg()));
                row1.getCell(3).setCellStyle(rowStyle);
                row1.createCell(4).setCellValue(good.getCategory().getCategory_name());
                row1.getCell(4).setCellStyle(rowStyle);
                stripIndex++;
            }
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
