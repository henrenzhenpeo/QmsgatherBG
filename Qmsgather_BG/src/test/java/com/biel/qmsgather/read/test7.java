// package com.biel.qmsgather.read;
//
// import lombok.extern.slf4j.Slf4j;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
//
// /**
//  * @autor 96901
//  * @date 2025/2/25
//  */
//
// @SpringBootTest
// @Slf4j
// public class test7 {
//
//     @Test
//     public void readExcelFile() throws IOException {
//         String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版\\等待\\ss.xlsx";
//         FileInputStream file = new FileInputStream(new File(filePath));
//
//         // 创建工作簿对象
//         Workbook workbook = new XSSFWorkbook(file);
//
//         // 遍历所有工作表
//         for (Sheet sheet : workbook) {
//             System.out.println("Sheet Name: " + sheet.getSheetName());
//
//             // 遍历所有行
//             for (Row row : sheet) {
//                 // 遍历所有单元格
//                 for (Cell cell : row) {
//                     // 根据单元格类型读取数据
//                     switch (cell.getCellType()) {
//                         case STRING:
//                             System.out.print(cell.getStringCellValue() + "\t");
//                             break;
//                         case NUMERIC:
//                             if (DateUtil.isCellDateFormatted(cell)) {
//                                 System.out.print(cell.getDateCellValue() + "\t");
//                             } else {
//                                 System.out.print(cell.getNumericCellValue() + "\t");
//                             }
//                             break;
//                         case BOOLEAN:
//                             System.out.print(cell.getBooleanCellValue() + "\t");
//                             break;
//                         case FORMULA:
//                             System.out.print(cell.getCellFormula() + "\t");
//                             break;
//                         default:
//                             System.out.print("UNKNOWN\t");
//                     }
//                 }
//                 System.out.println();
//             }
//         }
//
//         // 关闭工作簿和文件流
//         workbook.close();
//         file.close();
//     }
//
// }
