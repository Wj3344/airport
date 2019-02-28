package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.*;
import cn.fanchencloud.airport.mapper.*;
import cn.fanchencloud.airport.model.AdminRecord;
import cn.fanchencloud.airport.model.ExcelSheet1;
import cn.fanchencloud.airport.model.ExcelSheet2;
import cn.fanchencloud.airport.service.AdminService;
import cn.fanchencloud.airport.utils.BeanUtils;
import cn.fanchencloud.airport.utils.ExcelUtils;
import cn.fanchencloud.airport.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午4:22
 * Description:
 *
 * @author chen
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    /**
     * 注入账号服务持久层
     */
    private AdminMapper adminMapper;

    /**
     * 注入账号等级服务持久层
     */
    private IdentityMapper identityMapper;

    /**
     * 航班信息服务持久层
     */
    private FlightInformationMapper flightInformationMapper;

    /**
     * 值机信息服务持久层
     */
    private CheckInMapper checkInMapper;

    /**
     * 清洁信息持久层
     */
    private CleanMapper cleanMapper;

    /**
     * 站坪车辆持久层
     */
    private StandCarMapper standCarMapper;

    /**
     * 行查信息持久层
     */
    private BaggageMapper baggageMapper;

    /**
     * 综服信息持久层
     */
    private IntegratedServiceMapper integratedServiceMapper;

    /**
     * 货运信息持久层
     */
    private FreightMapper freightMapper;

    /**
     * 重点旅客标记
     */
    private FlightInformationPassengerTagMapper flightInformationPassengerTagMapper;

    /**
     * 特殊航班标记
     */
    private FlightInformationSpecialFlightMapper flightInformationSpecialFlightMapper;

    @Override
    public Admin login(Admin admin) {
        boolean flag = this.isExists(admin);
        if (flag) {
            int identity = this.queryIdentity(admin.getUsername());
            admin.setIdentity(identity);
            return admin;
        }
        return null;
    }

    @Override
    public int add(Admin admin) {
        return adminMapper.add(admin);
    }

    @Override
    public boolean update(Admin admin) {
        String password = MD5Utils.encrypt(admin.getUsername(), admin.getPassword());
        admin.setPassword(password);
        return adminMapper.update(admin) != 0;
    }

    @Override
    public boolean deleteAdmin(String username) {
        return adminMapper.deleteAdmin(username) != 0;
    }

    @Override
    public boolean isExists(Admin admin) {
        return adminMapper.isExists(admin) != 0;
    }

    @Override
    public int queryIdentity(String username) {
        return adminMapper.queryIdentity(username);
    }

    @Override
    public List<AdminRecord> getAllList() {
        List<Admin> adminList = adminMapper.queryAllAdmin();
        Map<Integer, Identity> identityMap = identityMapper.getAllIdentityMap();
        List<AdminRecord> adminRecordList = new ArrayList<>(adminList.size());
        for (Admin admin : adminList) {
            AdminRecord adminRecord = new AdminRecord();
            try {
                BeanUtils.fatherToChild(admin, adminRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }
            adminRecord.setDescribe(identityMap.get(admin.getIdentity()).getDescribe());
            adminRecordList.add(adminRecord);
        }
        return adminRecordList;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.queryAdmin(username);
    }

    @Override
    public HSSFWorkbook downloadData(Date startTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        List<FlightInformation> flightInformationList = flightInformationMapper.queryDataBetweenTime(startTime, endTime);
        if (flightInformationList == null || flightInformationList.size() == 0) {
            return null;
        }
        ListSort(flightInformationList);
        List<Integer> ids = flightInformationList.stream().map(FlightInformation::getId).collect(Collectors.toList());
        // 查询值机信息
        Map<Integer, CheckIn> checkInMap = checkInMapper.getRecordByIdList(ids);
        // 查询清洁信息
        Map<Integer, Clean> cleanMap = cleanMapper.getRecordByIdList(ids);
        // 查询站坪车辆
        Map<Integer, StandCar> standCarMap = standCarMapper.getRecordByIdList(ids);
        // 查询行查信息
        Map<Integer, Baggage> baggageMap = baggageMapper.getRecordByIdList(ids);
        // 查询综服信息
        Map<Integer, IntegratedService> integratedServiceMap = integratedServiceMapper.getRecordByIdList(ids);
        // 查询货运信息
        Map<Integer, Freight> freightMap = freightMapper.getRecordByIdList(ids);
        List<ExcelSheet1> sheet1List = new ArrayList<>(flightInformationList.size());
        List<ExcelSheet2> sheet2List = new ArrayList<>(flightInformationList.size());
        for (FlightInformation flightInformation : flightInformationList) {
            ExcelSheet1 sheet1 = new ExcelSheet1();
            sheet1.setFlightInformation(flightInformation);
            sheet1.setCheckIn(checkInMap.get(flightInformation.getId()));
            sheet1.setClean(cleanMap.get(flightInformation.getId()));
            sheet1.setStandCar(standCarMap.get(flightInformation.getId()));
            sheet1.setBaggage(baggageMap.get(flightInformation.getId()));
            sheet1.setIntegratedService(integratedServiceMap.get(flightInformation.getId()));
            sheet1.setFreight(freightMap.get(flightInformation.getId()));
            sheet1List.add(sheet1);
            ExcelSheet2 sheet2 = new ExcelSheet2();
            sheet2.setFlightInformation(flightInformation);
            // 重点旅客标记
            List<FlightInformationPassengerTag> byFlightId = flightInformationPassengerTagMapper.findByFlightId(flightInformation.getId());
            sheet2.setKeyPassenger(StringUtils.join(byFlightId.stream().map(FlightInformationPassengerTag::getTagId).toArray(), ","));
            // 特殊航班标记
            List<FlightInformationSpecialFlight> flightId = flightInformationSpecialFlightMapper.findByFlightId(flightInformation.getId());
            sheet2.setSpecialFlight(StringUtils.join(flightId.stream().map(FlightInformationSpecialFlight::getSpecialFlight).toArray(), ","));
            sheet2List.add(sheet2);
        }

        HSSFWorkbook headFile = ExcelUtils.getHeadFile();
        // 获取第一个工作页面
        HSSFSheet sheet1 = headFile.getSheetAt(0);
        // 设置水平居中的样式
        HSSFCellStyle style = headFile.createCellStyle();
        // 设置水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 开始写入数据
        int rowNumber = 2;
        for (ExcelSheet1 excelSheet1 : sheet1List) {
            // 获取行
            HSSFRow row = sheet1.createRow(rowNumber);
            // 新建列
            int line = 0;
            HSSFCell cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getDate(), sdf);
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getFlightNumber());

            // 值机start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getNumberOfPeople());
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getNumberOfLuggage());
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getCheckInSpecialCase());

            // 清洁 start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getCleaningTeamInPlace(), simpleDateFormat);
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getCleaningTime());
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getCleaningSpecialCaseDescription());

            // 站坪车辆 start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getVipVehicleInPlace(), simpleDateFormat);
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getCartInPlace(), simpleDateFormat);
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getStationFloorSpecialSituationDescription());

            // 行查 start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getBaggageCarTime(), simpleDateFormat);
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getConveyorBeltInPlace(), simpleDateFormat);
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getCheckSpecialCaseDescription());

            // 综服 start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getBoardingTime(), simpleDateFormat);
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getGuestTime(), simpleDateFormat);
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getOffCabinTime(), simpleDateFormat);
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getComprehensiveServiceDescription());

            // 货运 start
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet1.getCloseCargoTime(), simpleDateFormat);
            cell = row.createCell(line++);
            setCellValue(cell, excelSheet1.getSpecialCaseDescriptionOfFreight());

            rowNumber++;
        }

        // // 获取第二个工作页面
        HSSFSheet sheet2 = headFile.getSheetAt(1);
        // 开始写入数据
        rowNumber = 1;
        for (ExcelSheet2 excelSheet2 : sheet2List) {
            // 获取行
            HSSFRow row = sheet2.createRow(rowNumber++);
            // 新建列
            int line = 0;
            // 日期
            HSSFCell cell = row.createCell(line++);
            setCellValue(cell, excelSheet2.getDate(), sdf);
            // 航班号
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getPlaneNumber());
            // 机号
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getPlaneNumber());
            // 停机位
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getGatePosition());
            // 始发站
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getDepartureStation());
            // 目的地
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getDestination());
            // 登机时间
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getBoardingTime(), simpleDateFormat);
            // 重点旅客
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getKeyPassenger());
            // 特殊航班
            cell = row.createCell(line++);
            cell.setCellStyle(style);
            setCellValue(cell, excelSheet2.getSpecialFlight());
        }
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(new File("E:\\testExcel.xls"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            headFile.write(fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return headFile;
    }

    private static void ListSort(List<FlightInformation> list) {
        list.sort(Comparator.comparingLong(o -> o.getTime().getTime()));
    }

    private static void setCellValue(HSSFCell cell, Integer value) {
        if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(value);
        }
    }

    private static void setCellValue(HSSFCell cell, Date value, SimpleDateFormat simpleDateFormat) {
        if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(simpleDateFormat.format(value));
        }
    }

    private static void setCellValue(HSSFCell cell, String value) {
        if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(value);
        }
    }

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Autowired
    public void setIdentityMapper(IdentityMapper identityMapper) {
        this.identityMapper = identityMapper;
    }

    @Autowired
    public void setFlightInformationMapper(FlightInformationMapper flightInformationMapper) {
        this.flightInformationMapper = flightInformationMapper;
    }

    @Autowired
    public void setCheckInMapper(CheckInMapper checkInMapper) {
        this.checkInMapper = checkInMapper;
    }

    @Autowired
    public void setCleanMapper(CleanMapper cleanMapper) {
        this.cleanMapper = cleanMapper;
    }

    @Autowired
    public void setStandCarMapper(StandCarMapper standCarMapper) {
        this.standCarMapper = standCarMapper;
    }

    @Autowired
    public void setFlightInformationPassengerTagMapper(FlightInformationPassengerTagMapper flightInformationPassengerTagMapper) {
        this.flightInformationPassengerTagMapper = flightInformationPassengerTagMapper;
    }

    @Autowired
    public void setFlightInformationSpecialFlightMapper(FlightInformationSpecialFlightMapper flightInformationSpecialFlightMapper) {
        this.flightInformationSpecialFlightMapper = flightInformationSpecialFlightMapper;
    }

    @Autowired
    public void setBaggageMapper(BaggageMapper baggageMapper) {
        this.baggageMapper = baggageMapper;
    }

    @Autowired
    public void setIntegratedServiceMapper(IntegratedServiceMapper integratedServiceMapper) {
        this.integratedServiceMapper = integratedServiceMapper;
    }

    @Autowired
    public void setFreightMapper(FreightMapper freightMapper) {
        this.freightMapper = freightMapper;
    }
}
