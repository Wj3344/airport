package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.service.ExcelService;
import cn.fanchencloud.airport.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 2019/2/24
 * Time: 15:40
 * Description:
 *
 * @author chen
 */
@Service
public class ExcelServiceImpl  implements ExcelService {
    @Override
    public HSSFWorkbook getHSSFWorkbook() {
        HSSFWorkbook hssfWorkbook = ExcelUtils.getHeadFile();
        // 获取最近的
        return null;
    }
}
