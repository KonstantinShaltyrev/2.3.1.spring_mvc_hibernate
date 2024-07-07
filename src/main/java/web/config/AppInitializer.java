package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /** Метод, указывающий на класс конфигурации */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /** Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp. */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /** Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /** Для решения проблемы кодировки русских символов добавим фильтр, который будет заниматься предварительной обработкой запросов. */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{ characterEncodingFilter };
    }
}
