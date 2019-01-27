package cn.fanchencloud.airport.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-26
 * Time: 下午11:22
 * Description:
 *
 * @author chen
 */
public class BeanUtils {
    /**
     * 通过反射，将父类属性赋值给子类
     *
     * @param father 父类对象
     * @param child  子类对象
     * @param <T>    泛型
     * @throws Exception 转换失败异常
     */
    public static <T> void fatherToChild(T father, T child) throws Exception {
        if (child.getClass().getSuperclass() != father.getClass()) {
            throw new Exception("child 不是 father 的子类");
        }
        Class<?> fatherClass = father.getClass();
        Field[] declaredFields = fatherClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (isStatic) {
                System.out.println("静态！");
                continue;
            }
            Method method = fatherClass.getDeclaredMethod("get" + upperHeadChar(field.getName()));
            Object obj = method.invoke(father);
            field.setAccessible(true);
            field.set(child, obj);
        }

    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }
}
