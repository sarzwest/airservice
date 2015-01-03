package airservice.resources.tests;

import airservice.entity.bean.ParamConverBean;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by papa on 3.1.15.
 */
@Provider
public class MyParamConverter implements ParamConverterProvider{
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.getName().equals(ParamConverBean.class)){
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    return (T)new ParamConverBean(value);
                }

                @Override
                public String toString(T value) {
                    return value.toString();
                }
            };
        }
        return null;
    }
}
