package com.code.generate.utils;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述：bean转换
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class MyBeanUtils {
    public static final Logger logger = LoggerFactory.getLogger(MyBeanUtils.class);

    public static <T> List<T> convertList(List datas, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object data : datas) {
            if (clazz.equals(data.getClass())){
                return datas;
            }
            if (data instanceof Map) {
                try {
                    T obj = clazz.newInstance();
                    org.apache.commons.beanutils.BeanUtils.copyProperties(obj, data);
                    list.add(obj);
                } catch (Exception e) {
                    throw new RuntimeException("数据转换报错", e);
                }
            }
        }
        return list;
    }

    public static Object getBeanFieldValue(Object obj, String fileName) {
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor descriptor = propertyUtilsBean.getPropertyDescriptor(obj, fileName);
            if (descriptor!=null){
                Method readMethod = descriptor.getReadMethod();
                return readMethod.invoke(obj);
            }
        } catch (Exception e) {
            logger.warn("获得类["+obj.getClass()+"]的属性["+fileName+"]值报错。");
        }
        return null;
    }
}
