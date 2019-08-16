package com.code.generate.entity;

import java.util.List;

/**
 * 描述：模板对象
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class TemplateData {
    private String templateName; // 模板文件名
    private String path; // 生成路径
    private String name; // 生成文件名
    private boolean append = false; // 附加：true, 覆盖：false
    private List<String> useAble; // 什么情况下使用（为空时：默认都使用， 不为空时：useAble的参数其中一个为true时使用）

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public List<String> getUseAble() {
        return useAble;
    }

    public void setUseAble(List<String> useAble) {
        this.useAble = useAble;
    }

    @Override
    public String toString() {
        return "TemplateData{" + "templateName='" + templateName + '\'' + ", path='" + path + '\'' + ", name='" + name + '\'' + ", append=" + append + '}';
    }
}
