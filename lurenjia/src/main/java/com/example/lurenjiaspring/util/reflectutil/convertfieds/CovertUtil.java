package com.example.lurenjiaspring.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 不利用反射实现转换某个对象的一些字段
 * 
 * @param <T>
 */
public class CovertUtil<T extends CommonVO> {
    public T t;

    List<GetterSetter> getterSetters;

    public void setGetterSetters(List<GetterSetter> getterSetters) {
        this.getterSetters = getterSetters;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    /**
     *
     * @return
     */
    public T covert(){
        t.setCreator(t.getCreator()+"covert");
        t.setModify(t.getModify()+"covert");
        return t;
    }

    /**
     * 推测是因为可以控制要修改的函数个数，代码写死则不灵活（比如第一种）
     *
     * @param t
     * @param setters
     * @return
     */
    public T covert2(T t, List<GetterSetter> setters) {
        setters.forEach((item)->{
            item.setter.accept(t, item.getter.apply(t) + "covert");
        });
        return t;
    }

   public static class GetterSetter{
        Function<CommonVO, String> getter = CommonVO::getCreator;
        BiConsumer<CommonVO, String> setter = CommonVO::setCreator;

        public GetterSetter(Function<CommonVO, String> getter, BiConsumer<CommonVO, String> setter) {
            this.getter = getter;
            this.setter = setter;
        }
    }

    public static void main(String[] args) {
        // 1.
//        CovertUtil<CommonVO> commonVODemo = new CovertUtil<>();
//        CommonVO commonVO = new CommonVO("tht","tht");
//        commonVODemo.setT(commonVO);
//        commonVODemo.covert();
//        System.out.println(commonVODemo.getT());

        //2.推测是因为可以控制要修改的函数个数，代码写死则不灵活（比如第一种）
        CovertUtil<CommonVO> commonVODemo = new CovertUtil<>();
        CommonVO commonVO = new CommonVO("tht","tht");
        CommonVO commonVO1 = commonVODemo.covert2(commonVO,
                new ArrayList<>(Arrays.asList(new GetterSetter(CommonVO::getCreator, CommonVO::setCreator),
                        new GetterSetter(CommonVO::getModify, CommonVO::setModify))));
        System.out.println(commonVO1);


    }


}
