package com.example.lurenjiaspring.util.scan.classes;

import com.example.lurenjiaspring.util.scan.classes.base.RestfulClassInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author THT
 */
public class AllUrlName {
    private static final Logger logger = LoggerFactory.getLogger(AllUrlName.class);
    private static final String RESOURCE_PATTERN = "**/*.class";

    public static void main(String[] args) throws Exception {

        //System.out.println(scanPackages("com.example".split(",")));
        List<RestfulClassInfo> x = scanPackages1("com.example".split(","));
        x.forEach(System.out::println);
    }

    /**
     *
     * @param basePackages
     * @return 所有类
     */
    static List<Class<?>> scanPackages(String[] basePackages) {
        List<Class<?>> candidates = new ArrayList<Class<?>>();
        for (String pkg : basePackages) {
            try {
                candidates.addAll(findCandidateClasses(pkg));
            } catch (IOException e) {
                logger.error("扫描指定注解@RestController的基础包{}时出现异常", pkg);
                continue;
            }
        }
        return candidates;
    }

    /**
     *
     * @param basePackages
     * @return 所有类的所有方法相关信息
     */
    static List<RestfulClassInfo> scanPackages1(String[] basePackages) {
        List<RestfulClassInfo> classInfoList = new ArrayList<>();
        for (String pkg : basePackages) {
            try {
                List<Class<?>> candidateClasses = findCandidateClasses(pkg);
                for (Class<?> candidateClassItem : candidateClasses) {
                    for (Method method : candidateClassItem.getMethods()) {
                        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                        if (ObjectUtils.isEmpty(annotation)) {
                           continue;
                        }
                        RestfulClassInfo restfulClassInfo = new RestfulClassInfo();
                        restfulClassInfo.setClassName(candidateClassItem.getName());
                        restfulClassInfo.setMethodName(method.getName());
                        List<String> parameterNames = Arrays.stream(method.getParameters()).map(Parameter::getName).collect(Collectors.toList());
                        restfulClassInfo.setParametersAssembly(StringUtils.join(parameterNames, ','));
                       restfulClassInfo.setUrlName(StringUtils.join(annotation.value(),","));
                        classInfoList.add(restfulClassInfo);
                    }

                }
            } catch (IOException e) {
                logger.error("扫描指定注解@RestController的基础包{}时出现异常", pkg);
            }
        }
        return classInfoList;
    }


    /**
     * 获取符合要求的Controller名称
     *
     * @param basePackage
     * @return
     * @throws IOException
     */
    private static List<Class<?>> findCandidateClasses(String basePackage) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("开始扫描指定包{}下的所有类" + basePackage);
        }
        List<Class<?>> candidates = new ArrayList<Class<?>>();
        String packageSearchPath = replaceDotByDelimiter(basePackage) + '/' + RESOURCE_PATTERN;
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory(resourceLoader);
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(packageSearchPath);
        for (Resource resource : resources) {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            Class<?> candidateClass = transform(reader.getClassMetadata().getClassName());
            if (candidateClass == null) {
                continue;
            }
            RestController alias = candidateClass.getAnnotation(RestController.class);
            if (alias == null) {
                continue;
            }
            candidates.add(candidateClass);
            logger.debug("扫描到@RestController注解基础类:{}" + candidateClass.getName());
        }
        return candidates;
    }

    /**
     * 用"/"替换包路径中"."
     *
     * @param path
     * @return
     */
    private static String replaceDotByDelimiter(String path) {
        return StringUtils.replace(path, ".", "/");
    }

    /**
     * @param className
     * @return
     */
    private static Class<?> transform(String className) {
        Class<?> clazz = null;
        try {
            clazz = ClassUtils.forName(className, AllUrlName.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("未找到指定类:{}", className);
        }
        return clazz;
    }

}
