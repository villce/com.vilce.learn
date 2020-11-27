package com.vilce.common.utils;

import com.vilce.common.model.enums.ResultStatus;
import com.vilce.common.model.exception.BasicException;
import com.vilce.common.utils.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

/**
 * @Description: 文件读写工具类, 共103个工具方法
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.FileUtils
 * @Author: 雷才哲
 * @Date: 2020/11/26 13:39
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class FileUtils {
    /**
     * @param url 服务器文件地址，如http://xxx.xx.xx.xx/a.txt
     * @return 文件内容
     */
    public static String readRemoteFile(String url) {
        return readRemoteFile(url, null);
    }

    /**
     * 读取远程服务器上的文件
     *
     * @param url      服务器文件地址，如http://xxx.xx.xx.xx/a.txt
     * @param encoding 编码方式，默认UTF8
     * @return 文件内容
     */
    public static String readRemoteFile(String url, String encoding) {
        try {
            return IOUtils.toString(new URL(url).openStream(), encoding);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.READ_RESOURSE_EXCEPTION.getStatus(), StringUtils.join("读取文件：", url, "发生异常", e));
        }
    }

    /**
     * 等待NFS传播文件创建，设置超时
     * 此方法重复测试{@link File#exists()}，直到返回true,直到达到以秒为单位的指定最大时间为止
     *
     * @param file    要检测的文件，不可以为null
     * @param seconds 最大等待时间，单位：秒
     * @return true 如果文件存在
     */
    public static boolean waitFor(final File file, final int seconds) {
        return org.apache.commons.io.FileUtils.waitFor(file, seconds);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param data     数据
     */
    public static void write(final String filePath, final CharSequence data) {
        write(filePath, data, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param data     数据
     * @param encoding 编码
     */
    public static void write(final String filePath, final CharSequence data, final String encoding) {
        write(filePath, data, false, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param data     数据
     * @param append   是否累加
     * @param encoding 编码
     */
    public static void write(final String filePath, final CharSequence data, final boolean append, final String encoding) {
        write(new File(filePath), data, append, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file 文件对象
     * @param data 数据
     */
    public static void write(final File file, final CharSequence data) {
        write(file, data, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file     文件对象
     * @param data     数据
     * @param encoding 编码
     */
    public static void write(final File file, final CharSequence data, final String encoding) {
        write(file, data, false, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file     文件对象
     * @param data     数据
     * @param append   是否累加
     * @param encoding 编码
     */
    public static void write(final File file, final CharSequence data, final boolean append, final String encoding) {
        try {
            org.apache.commons.io.FileUtils.write(file, data, encoding, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "写数据到指定文件异常，" + e);
        }
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param lines    需要写入的数据集合
     */
    public static void writeLines(final String filePath, final Collection<?> lines) {
        writeLines(filePath, lines, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param lines    需要写入的数据集合
     */
    public static void writeLines(final String filePath, final Collection<?> lines, final String encoding) {
        writeLines(filePath, lines, false, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param filePath 文件路径
     * @param lines    需要写入的数据集合
     * @param append   是否累加
     * @param encoding 编码格式
     */
    public static void writeLines(final String filePath, final Collection<?> lines, final boolean append, final String encoding) {
        writeLines(new File(filePath), lines, append, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file  文件对象
     * @param lines 需要写入的数据集合
     */
    public static void writeLines(final File file, final Collection<?> lines) {
        writeLines(file, lines, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file  文件对象
     * @param lines 需要写入的数据集合
     */
    public static void writeLines(final File file, final Collection<?> lines, final String encoding) {
        writeLines(file, lines, false, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file     文件对象
     * @param lines    需要写入的数据集合
     * @param append   是否累加
     * @param encoding 编码格式
     */
    public static void writeLines(final File file, final Collection<?> lines, final boolean append, final String encoding) {
        try {
            org.apache.commons.io.FileUtils.writeLines(file, encoding, lines, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "写数据异常，" + e);
        }
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file       文件对象
     * @param lines      需要写入的数据集合
     * @param lineEnding 行结尾字符串,默认null时会自动换行，否则需要加上'\n'
     */
    public static void writeLinesEnding(final String filePath, final Collection<?> lines, final String lineEnding) {
        writeLinesEnding(filePath, lines, lineEnding, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file       文件对象
     * @param lines      需要写入的数据集合
     * @param append     是否累加
     * @param lineEnding 行结尾字符串,默认null时会自动换行，否则需要加上'\n'
     * @param encoding   编码格式
     */
    public static void writeLinesEnding(final String filePath, final Collection<?> lines, final String lineEnding, final boolean append, final String encoding) {
        writeLinesEnding(new File(filePath), lines, lineEnding, append, encoding);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file       文件对象
     * @param lines      需要写入的数据集合
     * @param lineEnding 行结尾字符串,默认null时会自动换行，否则需要加上'\n'
     */
    public static void writeLinesEnding(final File file, final Collection<?> lines, final String lineEnding) {
        writeLinesEnding(file, lines, lineEnding, false, SpecialCharUtils.UTF8);
    }

    /**
     * 写数据到指定的文件中
     *
     * @param file       文件对象
     * @param lines      需要写入的数据集合
     * @param append     是否累加
     * @param lineEnding 行结尾字符串,默认null时会自动换行，否则需要加上'\n'
     * @param encoding   编码格式
     */
    public static void writeLinesEnding(final File file, final Collection<?> lines, final String lineEnding, final boolean append, final String encoding) {
        try {
            org.apache.commons.io.FileUtils.writeLines(file, encoding, lines, lineEnding, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "写数据异常，" + e);
        }
    }

    /**
     * 将字节数组写入到指定的文件中;
     *
     * @param file 文件对象
     * @param data 字节数组
     */
    public static void writeLinesEnding(final File file, final byte[] data) {
        writeByteArrayToFile(file, data, false);
    }

    /**
     * 将字节数组写入到指定的文件中
     *
     * @param file   文件对象
     * @param data   字节数组
     * @param append true添加到文件末尾，false覆盖文件
     */
    public static void writeByteArrayToFile(final File file, final byte[] data, final boolean append) {
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "写数据异常，" + e);
        }
    }

    /**
     * 将字节数组写入到指定的文件中;
     *
     * @param file 文件对象
     * @param data 字节数组
     * @param off  字节数组其实位置
     * @param len  写入的字节长度
     */
    public static void writeByteArrayToFile(final File file, final byte[] data, final int off, final int len) {
        writeByteArrayToFile(file, data, off, len, false);
    }

    /**
     * 将字节数组写入到指定的文件中
     *
     * @param file   文件对象
     * @param data   字节数组
     * @param off    字节数组其实位置
     * @param len    写入的字节长度
     * @param append true添加到文件末尾，false覆盖文件
     */
    public static void writeByteArrayToFile(final File file, final byte[] data, final int off, final int len, final boolean append) {
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data, off, len, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "写数据异常，" + e);
        }
    }

    /**
     * 读取文件，整个文件读出一个字符串；编码方式默认是UTF8
     *
     * @param filePath 文件路径
     * @return String
     */
    public static String readFileToString(final String filePath) {
        return readFileToString(filePath, SpecialCharUtils.UTF8);
    }

    /**
     * 读取文件，整个文件读出一个字符串
     *
     * @param filePath 文件路径
     * @param encoding 编码方式
     * @return String
     */
    public static String readFileToString(final String filePath, final String encoding) {
        return readFileToString(new File(filePath), encoding);
    }

    /**
     * 读取文件，整个文件读出一个字符串；编码方式默认是UTF8
     *
     * @param file 文件对象
     * @return String
     */
    public static String readFileToString(final File file) {
        return readFileToString(file, SpecialCharUtils.UTF8);
    }

    /**
     * 读取文件，整个文件读出一个字符串
     *
     * @param file     文件对象
     * @param encoding 编码方式
     * @return String
     */
    public static String readFileToString(final File file, final String encoding) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "读取资源异常，" + e);
        }
    }

    /**
     * 将文件内容读取到字节数组之中
     *
     * @param filePath 文件路径
     * @return byte[]
     */
    public static byte[] readFileToByteArray(final String filePath) {
        return readFileToByteArray(new File(filePath));
    }

    /**
     * 将文件内容读取到字节数组之中
     *
     * @param file 文件对象
     * @return byte[]
     */
    public static byte[] readFileToByteArray(final File file) {
        try {
            return org.apache.commons.io.FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "读取资源异常，" + e);
        }
    }

    /**
     * 读取文件，按照行读取, 编码方式默认是UTF-8
     *
     * @param filePath 文件路径
     * @return List
     */
    public static List<String> readLines(final String filePath) {
        return readLines(filePath, SpecialCharUtils.UTF8);
    }

    /**
     * 读取文件，按照行读取
     *
     * @param filePath 文件路径
     * @param encoding 编码方式
     * @return List
     */
    public static List<String> readLines(final String filePath, final String encoding) {
        return readLines(new File(filePath), encoding);
    }

    /**
     * 读取文件，按照行读取, 编码方式默认是UTF-8
     *
     * @param file 文件对象
     * @return List
     */
    public static List<String> readLines(final File file) {
        return readLines(file, SpecialCharUtils.UTF8);
    }

    /**
     * 读取文件，按照行读取
     *
     * @param file     文件对象
     * @param encoding 编码方式
     * @return List
     */
    public static List<String> readLines(final File file, final String encoding) {
        try {
            return org.apache.commons.io.FileUtils.readLines(file, encoding);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "读取资源异常，" + e);
        }
    }

    /**
     * 计算文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static long sizeOf(final String filePath) {
        return sizeOf(new File(filePath));
    }

    /**
     * 计算文件大小
     *
     * @param file 文件对象
     * @return 文件大小
     */
    public static long sizeOf(final File file) {
        try {
            return org.apache.commons.io.FileUtils.sizeOf(file);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "计算文件大小异常，" + e);
        }
    }

    /**
     * 计算文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static BigInteger sizeOfAsBigInteger(final String filePath) {
        return sizeOfAsBigInteger(new File(filePath));
    }

    /**
     * 计算文件大小
     *
     * @param file 文件对象
     * @return 文件大小
     */
    public static BigInteger sizeOfAsBigInteger(final File file) {
        try {
            return org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "计算文件大小异常，" + e);
        }
    }

    /**
     * 计算目录下文件大小
     *
     * @param directory 文件目录对象
     * @return 文件大小
     */
    public static long sizeOfDirectory(final String directory) {
        return sizeOfDirectory(new File(directory));
    }

    /**
     * 计算目录下文件大小
     *
     * @param directory 文件目录对象
     * @return 文件大小
     */
    public static long sizeOfDirectory(final File directory) {
        try {
            return org.apache.commons.io.FileUtils.sizeOfDirectory(directory);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "计算文件大小异常，" + e);
        }
    }

    /**
     * 计算目录下文件大小
     *
     * @param directory 文件目录对象
     * @return 文件大小
     */
    public static BigInteger sizeOfDirectoryAsBigInteger(final String directory) {
        return sizeOfDirectoryAsBigInteger(new File(directory));
    }

    /**
     * 计算目录下文件大小
     *
     * @param directory 文件目录对象
     * @return 文件大小
     */
    public static BigInteger sizeOfDirectoryAsBigInteger(final File directory) {
        try {
            return org.apache.commons.io.FileUtils.sizeOfDirectoryAsBigInteger(directory);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "计算文件大小异常，" + e);
        }
    }

    /**
     * @return 返回系统临时目录路径
     */
    public static String getTempDirectoryPath() {
        return org.apache.commons.io.FileUtils.getTempDirectoryPath();
    }

    /**
     * @return 返回系统临时目录路径
     */
    public static File getTempDirectory() {
        return org.apache.commons.io.FileUtils.getTempDirectory();
    }

    /**
     * @return 返回用户主目录路径
     */
    public static String getUserDirectoryPath() {
        return org.apache.commons.io.FileUtils.getUserDirectoryPath();
    }

    /**
     * @return 返回用户主目录路径
     */
    public static File getUserDirectory() {
        return org.apache.commons.io.FileUtils.getUserDirectory();
    }

    /**
     * @param names 根据路径名称集构造File对象，如 ["D:", "work", "workplacec", "Emm"]
     * @return File文件对象
     */
    public static File getFile(final String... names) {
        return org.apache.commons.io.FileUtils.getFile(names);
    }

    /**
     * @param file  父目录对象， 如：new File("D:")
     * @param names 根据路径名称集构造File对象，如 ["work", "workplacec", "Emm"]
     * @return File文件对象
     */
    public static File getFile(final File file, final String... names) {
        return org.apache.commons.io.FileUtils.getFile(file, names);
    }

    /**
     * @param url 参数为null或者new URL("file://D:\\d.txt")
     * @return File文件对象
     */
    public static File toFile(final URL url) {
        try {
            return org.apache.commons.io.FileUtils.toFile(url);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "资源转换异常，" + e);
        }
    }

    /**
     * @param url 参数为null或者new URL("file://D:\\d.txt")
     * @return File文件对象
     */
    public static File[] toFiles(final URL[] url) {
        try {
            return org.apache.commons.io.FileUtils.toFiles(url);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "资源转换异常，" + e);
        }
    }

    /**
     * @param files 文件数组
     * @return java.net.URL数组
     */
    public static URL[] toUrls(final File[] files) {
        try {
            return org.apache.commons.io.FileUtils.toURLs(files);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "资源转换异常，" + e);
        }
    }

    /**
     * @param file 创建文件，如果文件存在则更新时间；如果不存在，创建一个空文件
     * @return true 创建成功，false 创建失败
     */
    public static void touch(final File file) {
        try {
            org.apache.commons.io.FileUtils.touch(file);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "资源转换异常，" + e);
        }
    }

    /**
     * 删除指定的file文件，不会抛出任何异常，如果file是一个目录则会删除当前目录及其子目录
     *
     * @param file 文件对象
     * @return true 删除成功，false 删除失败
     */
    public static boolean deleteQuietly(final File file) {
        return org.apache.commons.io.FileUtils.deleteQuietly(file);
    }

    /**
     * 删除指定的目录，当目录不存在或者不是目录的时候会删除失败
     *
     * @param directory
     * @return true 删除成功，false 删除失败
     */
    public static void deleteDirectory(final File directory) {
        try {
            org.apache.commons.io.FileUtils.deleteDirectory(directory);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "删除资源异常，" + e);
        }
    }

    /**
     * 强制删除指定的文件或者目录
     *
     * @param file 文件对象
     * @return true 删除成功，false 删除失败
     */
    public static void forceDelete(final File file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "删除资源异常，" + e);
        }
    }

    /**
     * 在调度JVM退出时删除文件，通常用在临时文件的删除
     *
     * @param file 文件或目录
     * @return true 删除成功，false 删除失败
     */
    public static void forceDeleteOnExit(final File file) {
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
        } catch (Exception e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "删除资源异常，" + e);
        }
    }

    /**
     * 创建目录，包括任何必需但不存在的父目录。如果已存在具有指定名称但不是目录的文件，则会引发IOException。如果无法创建目录（或目录不存在），
     * 则会引发IOException。
     *
     * @param directory 要创建的目录，不可以为null
     * @return true 创建成功，false创建失败
     */
    public static void forceMkdir(final File directory) {
        try {
            org.apache.commons.io.FileUtils.forceMkdir(directory);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "创建目录异常，" + e);
        }
    }

    /**
     * 为给定文件生成任何必需但不存在的父目录。如果无法创建父目录，则会引发ioexception。
     *
     * @param file 要创建的父文件，不可以为null
     * @return true 创建成功， false 创建失败
     */
    public static void forceMkdirParent(final File file) {
        try {
            org.apache.commons.io.FileUtils.forceMkdirParent(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "创建目录异常，" + e);
        }
    }

    /**
     * 将给定的字节（byte）数据转换为最接近的单位；如：1023->1023 bytes、1024->1KB、 1025->1KB、2047->1KB、2048->2KB、 2049->2kb
     * 单位包括：EB, PB, TB, GB, MB, KB or bytes
     *
     * @param size 字节大小
     * @return string 如：1 bytes
     */
    public static String byteCountToDisplaySize(final long size) {
        return byteCountToDisplaySize(BigInteger.valueOf(size));
    }

    /**
     * 将给定的字节（byte）数据转换为最接近的单位；如：1023->1023 bytes、1024->1KB、 1025->1KB、2047->1KB、2048->2KB、 2049->2kb
     * 单位包括：EB, PB, TB, GB, MB, KB or bytes
     *
     * @param size 字节大小
     * @return string 如：1 bytes
     */
    public static String byteCountToDisplaySize(final BigInteger size) {
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }

    /**
     * 打开指定文件的FileInputStream输入流
     *
     * @param file 文件对象
     * @return FileInputStream，异常时返回null
     */
    public static FileInputStream openInputStream(final File file) {
        try {
            return org.apache.commons.io.FileUtils.openInputStream(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "打开指定文件的FileInputStream输入流异常，" + e);
        }
    }

    /**
     * 打开指定文件的FileOutputStream输入流
     *
     * @param file 文件对象
     * @return FileOutputStream， 异常时返回null
     */
    public static FileOutputStream openOutputStream(final File file) {
        try {
            return org.apache.commons.io.FileUtils.openOutputStream(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "打开指定文件的FileInputStream输出流异常，" + e);
        }
    }

    /**
     * 打开指定文件的FileOutputStream输入流
     *
     * @param file   文件对象
     * @param append true 添加到文件末尾，false 覆盖文件
     * @return FileOutputStream， 异常时返回null
     */
    public static FileOutputStream openOutputStream(final File file, final boolean append) {
        try {
            return org.apache.commons.io.FileUtils.openOutputStream(file, append);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "打开指定文件的FileInputStream输出流异常，" + e);
        }
    }

    /**
     * 指定的父File对象是否包含子File对象
     *
     * @param directory 父目录
     * @param child     子对象可以是目录也可以是文件
     * @return true 如果包含，false 不包含；child 为null 返回false
     */
    public static boolean directoryContains(final File directory, final File child) {
        try {
            return org.apache.commons.io.FileUtils.directoryContains(directory, child);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "判定指定的父File对象是否包含子File对象异常，" + e);
        }
    }

    /**
     * 比较两个文件的内容是否相同
     *
     * @param file1 第一个文件
     * @param file2 第二个文件
     * @return true 文件内容相同，false 文件内容不同
     */
    public static boolean contentEquals(final File file1, final File file2) {
        try {
            return org.apache.commons.io.FileUtils.contentEquals(file1, file2);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "比较文件内容异常，" + e);
        }
    }

    /**
     * 比较两个文件的内容是否相同，ignoring EOL characters（EOL END OF LINE）忽略换行符
     *
     * @param file1       第一个文件
     * @param file2       第二个文件
     * @param charsetName 字符编码，可以为null
     * @return true 文件内容相同，false 文件内容不同
     */
    public static boolean contentEqualsIgnoreEOL(final File file1, final File file2, final String charsetName) {
        try {
            return org.apache.commons.io.FileUtils.contentEqualsIgnoreEOL(file1, file2, charsetName);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "比较文件内容异常，" + e);
        }
    }

    /**
     * 将包含java.io.File对象的集合转换为File[]
     *
     * @param files java.io.File集合
     * @return File[]
     */
    public static File[] convertFileCollectionToFileArray(final Collection<File> files) {
        return org.apache.commons.io.FileUtils.convertFileCollectionToFileArray(files);
    }

    /**
     * 校验文件数据的正确性
     * org.apache.commons.io.FileUtils.checksum(file, new CRC32());
     *
     * @param file     文件对象
     * @param checksum 校验算法
     * @return Checksum
     */
    public static Checksum checksum(final File file, final Checksum checksum) {
        try {
            return org.apache.commons.io.FileUtils.checksum(file, checksum);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "校验文件数据异常，" + e);
        }
    }

    /**
     * 校验文件数据的正确性,使用CRC32 循环冗余算法校验数据的正确性
     *
     * @param file     文件对象
     * @param checksum 校验算法
     * @return Checksum
     */
    public static Long checksumCRC32(final File file) {
        try {
            return org.apache.commons.io.FileUtils.checksumCRC32(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "校验文件数据异常，" + e);
        }
    }

    /**
     * 清空目录但是不删除目录
     *
     * @param directory 目录
     * @return true 清空目录成功，false 清空目录失败
     */
    public static void cleanDirectory(final File directory) {
        try {
            org.apache.commons.io.FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "清空目录异常，" + e);
        }
    }

    /**
     * 将整个目录复制到保留文件日期的新位置
     *
     * @param srcDir  要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null；如果目标目录不存在则创建该目录，否则合并目标源
     * @return true 复制成功， false 复制失败
     */
    public static void copyDirectory(final File srcDir, final File destDir) {
        copyDirectory(srcDir, destDir, true);
    }

    /**
     * 将整个目录复制到新位置
     *
     * @param srcDir           要复制的现有目录，不可以为null
     * @param destDir          新目录，不可以为null；如果目标目录不存在则创建该目录，否则合并目标源
     * @param preserveFileDate true 如果副本的文件日期要保持与源文件相同
     * @return true 复制成功， false 复制失败
     */
    public static void copyDirectory(final File srcDir, final File destDir, final boolean preserveFileDate) {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    /**
     * 将筛选后的目录复制到保留文件日期的新位置
     * 示例：复制目录和txt文件
     * 为“.txt”文件创建过滤器
     * OFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".txt");
     * IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);
     * <p>
     * 为目录或“.txt”文件创建筛选器
     * FileFilter filter = FileFilterUtils.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
     * <p>
     * 使用筛选器复制
     * FileUtils.copyDirectory(srcDir, destDir, filter);
     *
     * @param srcDir  要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null；如果目标目录不存在则创建该目录，否则合并目标源
     * @param filter  要应用的筛选器，null意味着复制所有的目录和文件
     * @return true 复制成功， false 复制失败
     */
    public static void copyDirectory(final File srcDir, final File destDir, final FileFilter filter) {
        copyDirectory(srcDir, destDir, filter, true);
    }

    /**
     * 将经过过滤器筛选的目录复制到指定的目录
     * 示例：复制目录和txt文件
     * 为“.txt”文件创建过滤器
     * OFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".txt");
     * IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);
     * <p>
     * 为目录或“.txt”文件创建筛选器
     * FileFilter filter = FileFilterUtils.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
     * <p>
     * 使用筛选器复制
     * FileUtils.copyDirectory(srcDir, destDir, filter, false);
     *
     * @param srcDir           要复制的现有目录，不可以为null
     * @param destDir          新目录，不可以为null；如果目标目录不存在则创建该目录，否则合并目标源
     * @param filter           要应用的筛选器，null意味着复制所有的目录和文件
     * @param preserveFileDate true 如果副本的文件日期要保持与源文件相同
     * @return true 复制成功， false 复制失败
     */
    public static void copyDirectory(final File srcDir, final File destDir, final FileFilter filter, final boolean preserveFileDate) {
        try {
            org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, filter, true);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将目录复制到保存文件日期的另外一个目录中，此方法将源目录及其所有内容复制到指定目标目录中同名的目录
     *
     * @param srcDir  要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null；如果目标目录不存在则创建该目录，否则合并目标源
     * @return true 复制成功， false 复制失败
     */
    public static void copyDirectoryToDirectory(final File srcDir, final File destDir) {
        try {
            org.apache.commons.io.FileUtils.copyDirectoryToDirectory(srcDir, destDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将文件复制到目录（保留文件日期）
     * 此方法复制指定源文件内容到指定目标目录中同名文件；如果目标目录不存在，则创建该目录，否则将覆盖
     *
     * @param srcFile 要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null
     * @return true 复制成功， false 复制失败
     */
    public static void copyFileToDirectory(final File srcFile, final File destDir) {
        copyFileToDirectory(srcFile, destDir, true);
    }

    /**
     * 将文件复制到目录（可以选择保留文件日期）
     * 此方法复制指定源文件内容到指定目标目录中同名文件；如果目标目录不存在，则创建该目录，否则将覆盖
     *
     * @param srcFile          要复制的现有目录，不可以为null
     * @param destDir          新目录，不可以为null
     * @param preserveFileDate true 保留文件最后修改日期，false 不保留
     * @return true 复制成功， false 复制失败
     */
    public static void copyFileToDirectory(final File srcFile, final File destDir, final boolean preserveFileDate) {
        try {
            org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将文件或目录复制到保留文件日期的另外一个目录中；此方法将源文件或目录及其所有内容复制到指定目标目录中同名的目录，如果目标目录不存在，则创建该目录。
     * 如果目标目录确实存在，则此方法将源与目标合并，以源为准
     *
     * @param src     要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null
     * @return true 复制成功， false 复制失败
     */
    public static void copyToDirectory(final File src, final File destDir) {
        try {
            org.apache.commons.io.FileUtils.copyToDirectory(src, destDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将文件复制到保留每个文件日期的目录；此方法复制指定源文件的内容到指定目标目录同名文件夹下，如果目录不存在，则创建该目录，否则覆盖目录
     *
     * @param srcs    要复制的现有目录，不可以为null
     * @param destDir 新目录，不可以为null
     * @return true 复制成功， false 复制失败
     */
    public static void copyToDirectory(final Iterable<File> srcs, final File destDir) {
        try {
            org.apache.commons.io.FileUtils.copyToDirectory(srcs, destDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 复制文件到一个新的位置,这个方法复制指定源文件的内容到指定的目标文件；如果目标文件不存在，则创建保存该文件的目录。如果目标文件存在，则此方法将覆盖它。
     *
     * @param srcFile  要复制现有的文件，不可以为null
     * @param destFile 新的文件，不可以为null
     * @return true 复制成功， false 复制失败
     */
    public static void copyFile(final File srcFile, final File destFile) {
        copyFile(srcFile, destFile, true);
    }

    /**
     * 复制文件到一个新的位置,这个方法复制指定源文件的内容到指定的目标文件；如果目标文件不存在，则创建保存该文件的目录。如果目标文件存在，则此方法将覆盖它。
     *
     * @param srcFile          要复制现有的文件，不可以为null
     * @param destFile         新的文件，不可以为null
     * @param preserveFileDate true 副本文件的日期保持一样，false 不保持
     * @return true 复制成功， false 复制失败
     */
    public static void copyFile(final File srcFile, final File destFile, final boolean preserveFileDate) {
        try {
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile, preserveFileDate);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将字节从文件中复制到OutputStream输出流中，
     *
     * @param input  读取字节流的文件
     * @param output 将要写入的OutputStream字节流
     * @return 复制的字节数
     */
    public static long copyFile(final File input, final OutputStream output) {
        try {
            return org.apache.commons.io.FileUtils.copyFile(input, output);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 从InputStream输入流中读取字节到目标文件，如果目标文件不存在，则将会被闯将；
     *
     * @param source      读取字节的InputStream流
     * @param destination 写入字节的目标文件
     * @return true 复制读取成功，false 复制读取失败
     */
    public static void copyInputStreamToFile(final InputStream source, final File destination) {
        try {
            org.apache.commons.io.FileUtils.copyInputStreamToFile(source, destination);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }

    }

    /**
     * 从InputStream输入流中读取字节到目标文件，如果目标文件不存在，则将会被闯将；
     *
     * @param source      读取字节的InputStream流
     * @param destination 写入字节的目标文件
     * @return true 复制读取成功，false 复制读取失败
     */
    public static void copyToFile(final InputStream source, final File destination) {
        try {
            org.apache.commons.io.FileUtils.copyToFile(source, destination);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将字节从URL中复制到目标文件中，如果目标文件不存在，则将会被创建，否则将会被覆盖
     *
     * @param source      要从中复制字节的URL,不可以为空
     * @param destination 非目录File对象，写入文件，不可以为null
     * @return
     */
    public static void copyURLToFile(final URL source, final File destination) {
        try {
            copyInputStreamToFile(source.openStream(), destination);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 将字节从URL中复制到目标文件中，如果目标文件不存在，则将会被创建，否则将会被覆盖
     *
     * @param source            要从中复制字节的URL,不可以为空
     * @param destination       非目录File对象，写入文件，不可以为null
     * @param connectionTimeout 连接url的超时时间，单位毫秒
     * @param readTimeout       如果没有数据可以读取的超时时间，单位毫秒
     * @return true 复制成功，false 复制失败
     */
    public static void copyURLToFile(final URL source, final File destination, final int connectionTimeout, final int readTimeout) {
        try {
            org.apache.commons.io.FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "复制资源异常，" + e);
        }
    }

    /**
     * 测试指定的文件flie是否比引用的文件reference新
     *
     * @param file      文件的修改日期是作为比较用，不可以为null
     * @param reference 文件的修改日期做为比较用，不可以为null
     * @return true file的修改日期比reference的日期新，否则：false
     */
    public static boolean isFileNewer(final File file, final File reference) {
        return org.apache.commons.io.FileUtils.isFileNewer(file, reference);
    }

    /**
     * 测试指定文件file的修改日期比指定日期date新
     *
     * @param file 指定文件的修改日期做比较用，不可以为null
     * @param date 引用的日期，不可以为null
     * @return true 指定文件修改日期比指定引用日期新，否则：false
     */
    public static boolean isFileNewer(final File file, final Date date) {
        return org.apache.commons.io.FileUtils.isFileNewer(file, date);
    }

    /**
     * 测试指定文件file的修改日期比指定日期timeMillis新
     *
     * @param file       指定文件的修改日期做比较用，不可以为null
     * @param timeMillis 引用的日期，不可以为null
     * @return true 指定文件修改日期比指定引用日期新，否则：false
     */
    public static boolean isFileNewer(final File file, final long timeMillis) {
        return org.apache.commons.io.FileUtils.isFileNewer(file, timeMillis);
    }

    /**
     * 测试指定的文件flie是否比引用的文件reference旧
     *
     * @param file      文件的修改日期是作为比较用，不可以为null
     * @param reference 文件的修改日期做为比较用，不可以为null
     * @return true file的修改日期比reference的日期旧，否则：false
     */
    public static boolean isFileOlder(final File file, final File reference) {
        return org.apache.commons.io.FileUtils.isFileOlder(file, reference);
    }

    /**
     * 测试指定文件file的修改日期比指定日期date旧
     *
     * @param file 文件的修改日期是作为比较用，不可以为null
     * @param date 文件的修改日期做为比较用，不可以为null
     * @return true file的修改日期比reference的日期旧，否则：false
     */
    public static boolean isFileOlder(final File file, final Date date) {
        return org.apache.commons.io.FileUtils.isFileOlder(file, date);
    }

    /**
     * 测试指定文件file的修改日期比指定日期timeMillis旧
     *
     * @param file       文件的修改日期是作为比较用，不可以为null
     * @param timeMillis 文件的修改日期做为比较用，不可以为null
     * @return true file的修改日期比reference的日期旧，否则：false
     */
    public static boolean isFileOlder(final File file, final long timeMillis) {
        return org.apache.commons.io.FileUtils.isFileOlder(file, timeMillis);
    }

    /**
     * 确定指定的文件是符号链接而不是实际文件
     * 如果路径中有符号链接，则不会返回true
     *
     * @param file 要校验的文件
     * @return true 如果文件时符号链接，否则：false
     */
    public static boolean isSymlink(final File file) {
        try {
            return org.apache.commons.io.FileUtils.isSymlink(file);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "判定确定指定的文件是符号链接而不是实际文件异常，" + e);
        }
    }

    /**
     * 返回指定文件行的Iterator迭代器
     * 此方法打开文件的InputStream读取流，当完成迭代器后，应该释放内部资源；这是可以通过调用close()方法或closeQuietly(LineIterator)方法做到
     * 建议使用的模式是：
     * LineIterator it = FileUtils.lineIterator(file, "UTF-8");
     * try {
     * while (it.hasNext()) {
     * String line = it.nextLine();
     * /// do something with line
     * }
     * } finally {
     * LineIterator.closeQuietly(iterator);
     * }
     * 如果在创建迭代器期间发生异常，则关闭底层流
     *
     * @param file     将要打开InputStream流的文件，不可以为空
     * @param encoding 将要使用的编码，null使用默认编码
     * @return 文件中行的迭代器
     */
    public static LineIterator lineIterator(final File file, final String encoding) {
        try {
            return org.apache.commons.io.FileUtils.lineIterator(file, encoding);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "获取指定文件行的Iterator迭代器异常，" + e);
        }
    }

    /**
     * 返回指定文件行的Iterator迭代器
     *
     * @param file 将要打开InputStream流的文件，不可以为空
     * @return 文件中行的迭代器
     */
    public static LineIterator lineIterator(final File file) {
        return lineIterator(file, null);
    }

    /**
     * 查找给定目录（及其子目录）中的文件并与扩展数组匹配
     *
     * @param directory  要搜索的目录
     * @param extensions 扩展数组，ex:{"java","xml"},如果这个参数为null,则返回所有的文件
     * @param recursive  true 递归搜索所有子目录
     * @return 匹配到的java.io.File集合
     */
    public static Collection<File> listFiles(final File directory, final String[] extensions, final boolean recursive) {
        return org.apache.commons.io.FileUtils.listFiles(directory, extensions, recursive);
    }

    /**
     * 查找给定目录（及其子目录）中的文件,找到的所有文件都由IOFileFilter过滤;
     * 如果你的搜索需要递归到子目录，你可以传入子目录IOFileFilter 过滤器，不需要将DirectoryFileFilter（通过逻辑AND）绑定到筛选器，这种方法对你很有用
     * 示例：
     * 如果你要搜索所有名为“temp”的目录，你可以传递FileFilterUtils.NameFileFilter("temp")
     * 此方法的另一个常见用法是在目录树中查找文件，但忽略生成的cvs目录；你可以简单的传递：FileFilterUtils.makeCVSAware(null)
     *
     * @param directory  要搜索的目录
     * @param fileFilter 查找文件时的过滤器，不可以为null，可以使用 TrueFileFilter#INSTANCE匹配所有的文件在搜索目录中，
     * @param dirFilter  查找子目录时要应用的可选筛选器；如果参数为null,则子目录不在搜索范围中，可以使用 TrueFileFilter#INSTANCE匹配所有的文件在搜索目录中
     * @return 匹配的java.io.File文件集合
     */
    public static Collection<File> listFiles(final File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        return org.apache.commons.io.FileUtils.listFiles(directory, fileFilter, dirFilter);
    }

    /**
     * 查找给定目录（及其子目录）中的文件。找到的所有文件都由IOFileFilter过滤。
     * 结果集合包括起始目录和与目录筛选器匹配的任何子目录
     *
     * @param directory  要搜索的目录
     * @param fileFilter 查找文件时应用的筛选器
     * @param dirFilter  查找子目录时要应用的可选筛选器；如果参数为null,则子目录不在搜索范围中，可以使用 TrueFileFilter#INSTANCE匹配所有的文件在搜索目录中
     * @return 匹配的java.io.File文件集合
     */
    public static Collection<File> listFilesAndDirs(final File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        return org.apache.commons.io.FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter);
    }

    /**
     * 允许给定目录（及其子目录）中的文件进行迭代
     * 所有文件都是通过IOFileFilter过滤，此方法是基于listFiles(File, IOFileFilter, IOFileFilter)，它支持iterable（'foreach'循环）
     *
     * @param directory  要查询的目录
     * @param fileFilter 查找文件时应用的筛选器
     * @param dirFilter  查找子目录时要应用的可选筛选器。如果此参数为{@code null}，则子目录将不包含在搜索中。使用TrueFileFilter.Instance匹配所有目录。
     * @return 匹配的java.io.File迭代器
     */
    public static Iterator<File> iterateFiles(final File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        return org.apache.commons.io.FileUtils.iterateFiles(directory, fileFilter, dirFilter);
    }

    /**
     * 允许对给定目录（及其可选子目录）中与扩展名数组匹配的文件进行迭代。
     * 此方法基于{@link listfiles（file，string[]，boolean）}，它支持iterable（'foreach'循环）。
     *
     * @param directory  要查询的目录
     * @param extensions 扩展名数组，ex. {"java","xml"}，如果此参数为null,则返回所有的文件
     * @param recursive  true 同时搜索所有的子目录
     * @return 匹配的java.io.File迭代器集合
     */
    public static Iterator<File> iterateFiles(final File directory, final String[] extensions, final boolean recursive) {
        return org.apache.commons.io.FileUtils.iterateFiles(directory, extensions, recursive);
    }

    /**
     * 允许对给定目录（及其子目录）中的文件进行迭代
     * 所有的文件通过IOFileFilter过滤器过滤，此方法是基于listFilesAndDirs(File, IOFileFilter, IOFileFilter)，支持Iterable ('foreach' 循环).
     * 生成的迭代器包含子目录本身
     *
     * @param directory  要查询的目录
     * @param fileFilter 查找文件时应用的筛选器
     * @param dirFilter  查找子目录时要应用的可选筛选器。如果此参数为{@code null}，则子目录将不包含在搜索中。使用TrueFileFilter.Instance匹配所有目录。
     * @return 匹配的java.io.File迭代器集合
     */
    public static Iterator<File> iterateFilesAndDirs(final File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        return org.apache.commons.io.FileUtils.iterateFilesAndDirs(directory, fileFilter, dirFilter);
    }

    /**
     * 移动目录
     * 当目标目录位于另一个文件系统上时，执行“复制并删除”
     *
     * @param srcDir  要移动的目录
     * @param destDir 目标目录
     * @return true 移动成功， false 移动失败
     */
    public static void moveDirectory(final File srcDir, final File destDir) {
        try {
            org.apache.commons.io.FileUtils.moveDirectory(srcDir, destDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "移动目录异常，" + e);
        }
    }

    /**
     * 移动目录到另外一个目录
     *
     * @param src           要移动的文件
     * @param destDir       目标目录
     * @param createDestDir true 创建目标目录，false 抛出IOException异常
     * @return true 移动成功，false 创建失败
     */
    public static void moveDirectoryToDirectory(final File src, final File destDir, final boolean createDestDir) {
        try {
            org.apache.commons.io.FileUtils.moveDirectoryToDirectory(src, destDir, createDestDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "移动目录异常，" + e);
        }
    }

    /**
     * 移动文件到另外一个文件
     * 当目标目录位于另一个文件系统上时，执行“复制并删除”
     *
     * @param srcFile  要移动的文件
     * @param destFile 目标目录
     * @return true 移动成功，false 移动失败
     */
    public static void moveFile(final File srcFile, final File destFile) {
        try {
            org.apache.commons.io.FileUtils.moveFile(srcFile, destFile);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "移动文件异常，" + e);
        }
    }

    /**
     * 移动一个文件到一个目录
     *
     * @param srcFile       要移动的文件
     * @param destDir       目标目录
     * @param createDestDir true 创建目标目录，false 抛出IOException异常
     * @return true 移动成功，false 移动失败
     */
    public static void moveFileToDirectory(final File srcFile, final File destDir, final boolean createDestDir) {
        try {
            org.apache.commons.io.FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "移动文件异常，" + e);
        }
    }

    /**
     * 移动一个文件或者目录到另外一个目录
     * 当目标目录位于另一个文件系统上时，执行“复制并删除”
     *
     * @param src           要移动的文件或目录
     * @param destDir       目标目录
     * @param createDestDir true 创建目录，false 抛出IOException异常
     * @return true 移动成功，false 移动失败
     */
    public static void moveToDirectory(final File src, final File destDir, final boolean createDestDir) {
        try {
            org.apache.commons.io.FileUtils.moveToDirectory(src, destDir, createDestDir);
        } catch (IOException e) {
            throw new BasicException(ResultStatus.IO_EXCEPTION.getStatus(), "移动文件异常，" + e);
        }
    }
}
