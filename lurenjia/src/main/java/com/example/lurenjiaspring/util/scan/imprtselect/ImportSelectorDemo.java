package com.example.lurenjiaspring.util.scan.imprtselect;

import com.example.lurenjiaspring.util.scan.MyFilter;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportSelectorDemo implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] strings = {MyFilter.class.getName()};

        return strings;
    }
}
