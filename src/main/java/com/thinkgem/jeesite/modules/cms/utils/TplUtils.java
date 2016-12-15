package com.thinkgem.jeesite.modules.cms.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * User: songlai
 */
public class TplUtils {

	   /**
     * 去除模板前缀
     *
     * @param list     模板列表
     * @param prefix   模板前缀  例如“frontViewArticle”
     * @param include  需包含的模板  例如“/WEB-INF/views/modules/cms/front/themes/jeesite/articleSelectList.jsp”
     * @param excludes 需去除的模板  例如“frontViewArticle”
     * @return
     */
    public static List<String> tplTrim(List<String> list, String prefix, String include, String... excludes) {
        List<String> result = new ArrayList<String>();
        if (!StringUtils.isBlank(include) && !list.contains(include)) {
            if (!tplContain(excludes, include)) {
                int start = include.lastIndexOf("/");
                int end = include.lastIndexOf(".");
                if (start == -1 || end == -1) {
                    throw new RuntimeException("include not contain '/' or '.':" + include);
                }
                result.add(include.substring(start + 1, end));
            }
        }
        for (String t : list) {
            if (!tplContain(excludes, t)) {
                int start = t.lastIndexOf("/");
                int end = t.lastIndexOf(".");
                if (start == -1 || end == -1) {
                    throw new RuntimeException("name not contain '/' or '.':" + t);
                }
                t = t.substring(start + 1, end);
                //1、截取含有名为viewList的文件
                if(t.contains(prefix)){
                    result.add(t);
                }
            }
        }
        return result;
    }

    /**
     * 检查tpl是否存在于excludes里面。
     *
     * @param excludes
     * @param tpl
     * @return
     */
    private static boolean tplContain(String[] excludes, String tpl) {
        int start = tpl.lastIndexOf("/");
        int end = tpl.lastIndexOf(".");
        if (start == -1 || end == -1) {
            throw new RuntimeException("tpl not contain '/' or '.':" + tpl);
        }
        //截取文件的名字
        String name = tpl.substring(start + 1, end);
        for (String e : excludes) {
            if (e.equals(name)) {
                return true;
            }
        }
        return false;
    }

}
