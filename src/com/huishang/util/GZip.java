package com.huishang.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class GZip
{
    // 日志对象
    private static Log logger = LogFactory.getLog(GZip.class);
    
    /**
     * <解压tar包>
     * <该方法只适用于解压tar包>
     * @param rarFileName 需要解压的文件路径(具体到文件)
     * @param destDir 解压目标路径
     * @return 执行结果
     * @see [类、类#方法、类#成员]
     */
    public static boolean unTargzFile(String rarFileName, String destDir, List<String> fileNames)
    {
        try
        {
            if (!StringUtils.isEmpty(rarFileName) && !StringUtils.isEmpty(destDir))
            {
                mkdirs(destDir);
                return unGzipFile(rarFileName, destDir, fileNames);
            }
            return false;
        }
        catch (Exception e)
        {
            logger.error(e);
            return false;
        }
    }
    
    /**
     * <解压zip包>
     * <该方法只适用于解压zip包>
     * @param rarFileName 需要解压的文件路径(具体到文件)
     * @param destDir 解压目标路径
     * @return 执行结果
     * @see [类、类#方法、类#成员]
     */
    public static void unZipFile(String rarFileName, String destDir, List<String> fileNames)
    {
        try
        {
            if (!StringUtils.isEmpty(rarFileName) && !StringUtils.isEmpty(destDir))
            {
                mkdirs(destDir);
                ReadZip(rarFileName, destDir, fileNames);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
    
    /**
     * <解压缩文件>
     * <解压标准gzip格式的压缩包>
     * @param zipfileName 需要解压的文件路径(具体到文件)
     * @param outputDirectory 解压目标路径
     * @see [类、类#方法、类#成员]
     */
    private static boolean unGzipFile(String zipfileName, String outputDirectory, List<String> fileNames)
    {
        FileInputStream fis = null;
        ArchiveInputStream in = null;
        BufferedInputStream bis = null;
        try
        {
            fis = new FileInputStream(zipfileName);
            GZIPInputStream gis = new GZIPInputStream(new BufferedInputStream(fis));
            in = new ArchiveStreamFactory().createArchiveInputStream("tar", gis);
            bis = new BufferedInputStream(in);
            TarArchiveEntry entry = (TarArchiveEntry)in.getNextEntry();
            while (entry != null)
            {
                String name = entry.getName();
                String[] names = name.split("/");
                String fileName = outputDirectory;
                for (int i = 0; i < names.length; i++)
                {
                    String str = names[i];
                    fileName = fileName + File.separator + str;
                }
                if (name.endsWith("/"))
                {
                    mkdirs(fileName);
                }
                else
                {
                    File file = getRealFileName(outputDirectory, name);
                    if (null != fileNames)
                    {
                        fileNames.add(file.getName());
                    }
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    int b = -1;
                    while ((b = bis.read()) != -1)
                    {
                        bos.write(b);
                    }
                    bos.flush();
                    bos.close();
                }
                entry = (TarArchiveEntry)in.getNextEntry();
            }
            return true;
        }
        catch (Exception e)
        {
            logger.error(e);
            return false;
        }
        finally
        {
            try
            {
                if (null != bis)
                {
                    bis.close();
                }
            }
            catch (IOException e)
            {
                logger.error(e);
            }
        }
    }
    
    
    /**
     * <解压缩文件>
     * <解压标准gzip格式的压缩包>
     * @param zipfileName 需要解压的文件路径(具体到文件)
     * @param outputDirectory 解压目标路径
     * @see [类、类#方法、类#成员]
     */
    public static boolean unGzipFiles(BufferedInputStream fis, String outputDirectory, List<String> fileNames)
    {
    	mkdirs(outputDirectory);
        ArchiveInputStream in = null;
        BufferedInputStream bis = null;
        try
        {
            GZIPInputStream gis = new GZIPInputStream(fis);
            in = new ArchiveStreamFactory().createArchiveInputStream("tar", gis);
            bis = new BufferedInputStream(in);
            TarArchiveEntry entry = (TarArchiveEntry)in.getNextEntry();
            while (entry != null)
            {
                String name = entry.getName();
                String[] names = name.split("/");
                String fileName = outputDirectory;
                for (int i = 0; i < names.length; i++)
                {
                    String str = names[i];
                    fileName = fileName + File.separator + str;
                }
                if (name.endsWith("/"))
                {
                    mkdirs(fileName);
                }
                else
                {
                    File file = getRealFileName(outputDirectory, name);
                    if (null != fileNames)
                    {
                        fileNames.add(file.getName());
                    }
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    int b = -1;
                    while ((b = bis.read()) != -1)
                    {
                        bos.write(b);
                    }
                    bos.flush();
                    bos.close();
                }
                entry = (TarArchiveEntry)in.getNextEntry();
            }
            return true;
        }
        catch (Exception e)
        {
            logger.error(e);
            return false;
        }
        finally
        {
            try
            {
                if (null != bis)
                {
                    bis.close();
                }
            }
            catch (IOException e)
            {
                logger.error(e);
            }
        }
    }
    
    /**
     * <解压缩zip包>
     * <功能详细描述>
     * @param unzipPath
     * @param zippath
     * @see [类、类#方法、类#成员]
     */
    public static void ReadZip(String unzipPath, String zippath, List<String> fileNames)
    {
        try
        {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(unzipPath);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null)
            {
                String name = entry.getName();
                String[] names = name.split("/");
                String fileName = zippath;
                for (int i = 0; i < names.length; i++)
                {
                    String str = names[i];
                    fileName = fileName + File.separator + str;
                }
                if (name.endsWith("/"))
                {
                    mkdirs(fileName);
                }
                else
                {
                    int count;
                    byte data[] = new byte[2048];
                    File file = getRealFileName(zippath, name);
                    if (null != fileNames)
                    {
                        fileNames.add(file.getName());
                    }
                    dest = new BufferedOutputStream(new FileOutputStream(file));
                    
                    while ((count = zis.read(data, 0, 2048)) != -1)
                    {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                }
            }
            zis.close();
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
    
    /** 
    * 给定根目录，返回一个相对路径所对应的实际文件对象
    * @param zippath 指定根目录 
    * @param absFileName 相对路径名，来自于ZipEntry中的name 
    * @return File 实际的文件 
    */
    private static File getRealFileName(String zippath, String absFileName)
    {
        String[] dirs = absFileName.split("/", absFileName.length());
        
        File ret = new File(zippath);// 创建文件对象 
        
        if (dirs.length > 1)
        {
            for (int i = 0; i < dirs.length - 1; i++)
            {
                ret = new File(ret, dirs[i]);
            }
        }
        
        if (!ret.exists())
        {
            // 检测文件是否存在 
            ret.mkdirs();// 创建此抽象路径名指定的目录 
        }
        
        // 根据 ret 抽象路径名和 child 路径名字符串创建一个新 File 实例
        ret = new File(ret, dirs[dirs.length - 1]);
        
        return ret;
    }

    private static void mkdirs(String fileName){
    	File file = new File(fileName);
    	if(!file.exists()){
    		file.mkdirs();
    	}
    }
    
    public static void main(String[] args)
    {
        List<String> unzipOne = new ArrayList<String>();
        List<String> unzipTwo = new ArrayList<String>();
        GZip.unTargzFile("F:/fap.8.tar.gz", "F:/111", unzipOne);
        int size = unzipOne.size();
        for (int i = 0; i < size; i++)
        {
            GZip.unTargzFile("F:/111/" + unzipOne.get(i), "F:/222", unzipTwo);
        }
    }
}