package cn.fanchencloud.airport.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-24
 * Time: 下午9:10
 * Description: Excel文件工具类
 *
 * @author chen
 */
public class ExcelUtils {

    /**
     * 生成需要导出的Excel表格文件的头部（标题部分）
     *
     * @return 表格头部 生成的头部信息可以参考资源目录下的demo1548334855715.xls文件
     */
    public static HSSFWorkbook getHeadFile() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFPalette palette = workbook.getCustomPalette();
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFColor myColor = palette.findSimilarColor(255, 0, 0);
        // get the palette index of that color
        short palIndex = myColor.getIndex();
        // code to get the style for the cell goes here
        style.setFillBackgroundColor(palIndex);

        // 创建工作簿
        HSSFSheet sheet = workbook.createSheet("时间节点记录表");
        HSSFSheet sheet2 = workbook.createSheet("次日出港航班计划表");

        // 创建第一行
        HSSFRow first = sheet.createRow(0);
        HSSFCell cellFirst = first.createCell(0);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("日期");

        cellFirst = first.createCell(1);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("航班号");

        cellFirst = first.createCell(2);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("值机");

        cellFirst = first.createCell(5);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("清洁队");


        // 创建第二行
        HSSFRow secondLine = sheet.createRow(1);
        HSSFCell cellSecondLine = secondLine.createCell(2);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("人数");
        cellSecondLine = secondLine.createCell(3);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("行李件数");
        cellSecondLine = secondLine.createCell(4);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");
        cellSecondLine = secondLine.createCell(5);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("清洁队到位时间");
        cellSecondLine = secondLine.createCell(6);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("清洁用时");
        cellSecondLine = secondLine.createCell(7);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");


        // 站坪车辆
        cellFirst = first.createCell(8);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("站坪车辆");
        // 站坪车辆的记录 VIP车辆到位时间、推车到位时间
        cellSecondLine = secondLine.createCell(8);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("VIP车辆到位时间");
        cellSecondLine = secondLine.createCell(9);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("推车到位时间");
        cellSecondLine = secondLine.createCell(10);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");


        // 行查
        cellFirst = first.createCell(11);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("行查");
        // 行查的记录 行李车发时间、传送带到位时间
        cellSecondLine = secondLine.createCell(11);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("行李车发时间");
        cellSecondLine = secondLine.createCell(12);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("传送带到位时间");
        cellSecondLine = secondLine.createCell(13);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");

        // 综服
        cellFirst = first.createCell(14);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("综服");
        // 登机时间、客齐时间、关客舱时间
        cellSecondLine = secondLine.createCell(14);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("登机时间");
        cellSecondLine = secondLine.createCell(15);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("客齐时间");
        cellSecondLine = secondLine.createCell(16);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("关客舱时间");
        cellSecondLine = secondLine.createCell(17);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");

        // 货运
        cellFirst = first.createCell(18);
        cellFirst.setCellStyle(style);
        cellFirst.setCellValue("货运");
        // 登机时间、客齐时间、关客舱时间
        cellSecondLine = secondLine.createCell(18);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("关货舱时间");
        cellSecondLine = secondLine.createCell(19);
        cellSecondLine.setCellStyle(style);
        cellSecondLine.setCellValue("特殊情况说明");


        // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
        // 行和列都是从0开始计数，且起始结束都会合并
        // 这里是合并excel中日期的两行为一行
        // 合并日期
        CellRangeAddress region = new CellRangeAddress(0, 1, 0, 0);
        sheet.addMergedRegion(region);
        // 合并航班号单元格
        region = new CellRangeAddress(0, 1, 1, 1);
        sheet.addMergedRegion(region);
        // 合并值机单元格
        region = new CellRangeAddress(0, 0, 2, 4);
        sheet.addMergedRegion(region);
        // 合并清洁队单元格
        region = new CellRangeAddress(0, 0, 5, 7);
        sheet.addMergedRegion(region);

        region = new CellRangeAddress(0, 0, 8, 10);
        sheet.addMergedRegion(region);

        region = new CellRangeAddress(0, 0, 11, 13);
        sheet.addMergedRegion(region);

        region = new CellRangeAddress(0, 0, 14, 17);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 0, 18, 19);
        sheet.addMergedRegion(region);

        // 创建第二页的标题
        // 日期	航班号	机号	停机位	始发站	目的地	登机时间	重点旅客	重点航班
        String[] array = {"日期", "航班号", "机号", "停机位", "始发站", "目的地", "登机时间", "重点旅客", "重点航班"};
        HSSFRow second = sheet2.createRow(0);
        HSSFCell hssfCell = null;
        for (int i = 0; i < array.length; i++) {
            hssfCell = second.createCell(i);
            hssfCell.setCellStyle(style);
            hssfCell.setCellValue(array[i]);
        }
        return workbook;
    }
}
