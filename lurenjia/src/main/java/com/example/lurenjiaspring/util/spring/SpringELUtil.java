package com.example.lurenjiaspring.util.spring;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author 82590
 */
public class SpringELUtil {


        public static void main(String[] args) {
            // 创建一个ExpressionParser对象
            ExpressionParser parser = new SpelExpressionParser();

            // 创建一个StandardEvaluationContext对象
            StandardEvaluationContext context = new StandardEvaluationContext();

            // 在EvaluationContext中设置变量
            context.setVariable("name", "John Doe");
            context.setVariable("age", 30);
            context.setVariable("hobbies", "reading,music,sports");

            // 使用SpEL表达式进行属性访问和方法调用
            String nameExpression = "#name.toUpperCase()";
            //#{person.age >= 18 ? true : false}
            //#{'John Doe'.toUpperCase()}
            //#{{'reading', 'music', 'sports'}}
            String ageExpression = "#age * 2";
            String hobbiesExpression = "#hobbies";

            // 解析和评估表达式
            String nameResult = parser.parseExpression(nameExpression).getValue(context, String.class);
            Integer ageResult = parser.parseExpression(ageExpression).getValue(context, Integer.class);
            String[] hobbiesResult = parser.parseExpression(hobbiesExpression).getValue(context, String[].class);

            // 打印结果
            System.out.println("Name: " + nameResult);
            System.out.println("Age: " + ageResult);
            System.out.println("hobbiesResult[0] = " + hobbiesResult[0]);
        }

}
