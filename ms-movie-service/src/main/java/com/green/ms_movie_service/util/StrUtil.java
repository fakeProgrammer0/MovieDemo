package com.green.ms_movie_service.util;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/10
 * @description:
 */
public class StrUtil
{
    public static String trimQuotes(String str)
    {
//        char[] tokens = {'\"', '\''};
        String[] tokens = {"\"", "\'"};
        for (int i = 0; i < tokens.length; i++)
            if (str.startsWith(tokens[i]) && str.endsWith(tokens[i]))
            {
                str = str.substring(1, str.length() - 1);
                break;
            }
        
        return str;
    }
}
