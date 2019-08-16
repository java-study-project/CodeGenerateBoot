package com.code.generate.utils;

/**
 * 描述：字符串处理工具类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/6      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class StringUtils {

    /**
     * 描述:字符串替换分隔符，并把单词首字母大写
     * @author 2018年12月06日  fengjiajia
     * @param oldStr 旧字符串
     * @param oldChar 旧分隔符
     * @param newChar 新分隔符
     */
    public static String replaceSeparatorAndWordFirstCharUpper(String oldStr,String oldChar,String newChar)
    {
        StringBuilder newString = new StringBuilder();
        int index = 0;
        String tempStr = oldStr;
        while(tempStr.contains(oldChar))
        {
            // 查询旧分隔符位置
            index = tempStr.indexOf(oldChar);
            // 替换分隔符
            newString.append(tempStr, 0, index).append(newChar);
            if(index != tempStr.length())
            {
                // 分隔符不是末尾，重新设置临时字符串为分隔符后的字符串
                tempStr = tempStr.substring(index + oldChar.length(), tempStr.length());
                // 首字母大写
                tempStr = firstCharUpperCas(tempStr);
            }
        }
        newString.append(tempStr);
        return firstCharUpperCas(newString.toString());
    }

    /**
     * 描述: 字符串转换为java字段规范格式（驼峰），以'_'分隔单词
     * 例：
     *      sc_info_scf  -》  scInfoScf   // 第1个单词小写，往后单词首字母大写
     *      s_info_scf  -》  sinfoScf     // 第1个单词如果只有1个字符，第二个单词也全小写，往后单词首字母大写
     *      scInfoScf    -》  scInfoScf   // 只有一个单词, 首字母小写
     *      SCInfoScf    -》  scinfoscf   // 只有一个单词， 第一个和第二个字符都是大写，全小写
     */
    public static String toJavaFieldName(String oldName){
        StringBuilder newName = new StringBuilder();
        String[] words = oldName.split("_");
        if(words.length == 1){
            if(oldName.length() > 2) {
                char firstChar = oldName.charAt(0);
                char secondChar = oldName.charAt(1);
                if(isUpperChar(firstChar) && isUpperChar(secondChar)){
                    // 如果第一个字母和第二个字母都是大写，则属性名全部改成小写
                    newName = new StringBuilder(oldName.toLowerCase());
                }else{
                    // 否则改成首字母小写
                    newName = new StringBuilder(firstCharLowerCase(oldName));
                }
            }else{
                //如果只有字符串长度小于3则全部设置为小写
                newName = new StringBuilder(oldName.toLowerCase());
            }
        }else{
            // 存在多个以_分隔的单词时，先把字符串全部转小写
            String _oldName = oldName.toLowerCase();
            words = oldName.split("_"); // 重新分隔单词
            for (int i = 0; i < words.length; i++) {
                if(i == 0){
                    // 第一个单词全小写
                    newName.append(words[i]);
                }else{
                    if(newName.length() == 1 ){
                        // 第1个单词如果只有1个字符，第二个单词也全小写
                        newName.append(words[i]);
                    }else{
                        // 否则第一个单词后的单词，首字母大写
                        newName.append(firstCharUpperCas(words[i]));
                    }
                }
            }
        }
        return newName.toString();
    }

    /**
     * 描述: 将首字母转换为小写
     */
    public static String firstCharLowerCase(String str) {
        if (str != null && str.length() >= 1) {
            String begin = str.substring(0, 1).toLowerCase();
            String end = str.substring(1, str.length());
            return begin + end;
        } else {
            return str;
        }
    }

    /**
     * 字符串首字符大写
     */
    public static String firstCharUpperCas(String str){
        if (str != null && str.length() >= 1) {
            String begin = str.substring(0, 1).toUpperCase();
            String end = str.substring(1, str.length());
            return begin + end;
        } else {
            return str;
        }
    }

    /**
     * 描述:字符是否为大写
     */
    private static boolean isUpperChar(char c){
        return c <= 90;
    }

    public static boolean isNotEmpty(String str) {
        return org.springframework.util.StringUtils.isEmpty(str);
    }
}
