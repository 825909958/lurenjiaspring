//package com.example.lurenjiaspring.util.xmlutil;
//
//import jdk.internal.org.xml.sax.InputSource;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.lang.model.element.Element;
//import javax.swing.text.Document;
//import java.io.ByteArrayInputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Demo {
//        /**  * 解析xml数据  * */  public static Map<String,Object> parseXml(byte[] xmlBytes, String charset) {
//            SAXReader reader = new SAXReader(false);
//            InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
//            source.setEncoding(charset);
//            Map<String,Object> map = new HashMap<String, Object>();
//            try {
//                Document doc = reader.read(source);
//                Iterator<Element> iter = doc.getRootElement().elementIterator();
//                while (iter.hasNext()) {
//                    Element e = iter.next();
//                    if (!e.elementIterator().hasNext()) {
//                        map.put(e.getName(),e.getTextTrim());
//                        continue;
//                    }
//                    Iterator<Element> iterator = e.elementIterator();
//                    Map<String,String> param = new HashMap<String, String>();
//                    while (iterator.hasNext()) {
//                        Element el = iterator.next();
//                        param.put(el.getName(),el.getTextTrim());
//                    }
//                    map.put(e.getName(),param);
//                }
//            }catch (Exception e) {
//                LogUtils.error("XmlParseError",e);
//            }
//            return map;
//        }
//
//        public static Map<String,String> parseXml(String xml) {
//            if (StringUtils.isBlank(xml)) {
//                return null;
//            }
//            Map<String,String> result = new HashMap<String, String>();
//            try {
//                Map<String,Object> map = parseXml(xml.getBytes("UTF-8"), "UTF-8");
//                for (String key:map.keySet()) {
//                    Object value = map.get(key);
//                    result.put(key, String.valueOf(value));
//                }
//                return result;
//            }catch (Exception e) {
//                //LogUtils.error("parse_xml_error", e);
//            }
//            return null;
//        }
//
//}
