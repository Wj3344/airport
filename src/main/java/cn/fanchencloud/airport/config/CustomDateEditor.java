package cn.fanchencloud.airport.config;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-2-3
 * Time: 下午3:50
 * Description: 扩展类型转换
 *
 * @author chen
 */
public class CustomDateEditor extends PropertyEditorSupport {

    /**
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Date(Long.decode(text)));
    }

    /**
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? String.valueOf(TimeUnit.MILLISECONDS.toSeconds(value.getTime())) : "");
    }

}