package com.gucheng.ssm.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * NAME   :  EmailSystem/com.biolims.emailsystem.util
 * Author :  biolims
 * Date   :  2015.12.04 22:58
 * TODO   :
 */
public class FileUtil {

    /**
     * ���շָ�������ַ�ַ����и,Ȼ��ƴװ��FIle����
     * @param files �ַ���
     * @param split �ָ���,����Ϊ����
     * @return
     */
    public File[] getFileArrayByString(String files, String split){
        String[] aa = files.split(split);
        File[] attachments = new File[aa.length];
        for(int i=0;i<aa.length;i++){
            attachments[i] = new File(aa[i]);
        }
        return attachments;
    }

    public String getFilePrefix(MultipartFile file){
        String fileName=file.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
        return prefix;
    }

}
