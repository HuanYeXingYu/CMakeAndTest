package com.nzsc.cmakeandtest.utils;

/**
 * Created by 夜墨 on 2017/7/18.
 */

public class CharUtils {
    /**
     *
     * @param hex hex
     * @return     String
     */
    public static  String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }
    /**
     * 十六进制转换字符串
     *   str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static int hexStr2Str(byte[] hexStr)
    {
        byte[] byteList={hexStr[3],hexStr[2],hexStr[1],hexStr[0]};
        String byteString=new String(byteList);

        return  Integer.parseInt(byteString+"",10);
    }
}
