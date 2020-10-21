import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @description TODO
 * author: liquan
 * date: 2020/10/20 09:45
 * version: 1.0
 */
public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) {
        String filePath = "src/Hello.xlass";

        try {
            // 读取xlass文件内容
            byte[] arrByte = getContent(filePath);

            for (int i = 0; i < arrByte.length; i++) {
                arrByte[i] = intToByte(255 - byteToInt(arrByte[i]));
            }

            Class<?> clas = new HelloClassloader().findClass("Hello", arrByte);

            Method[] methods = clas.getMethods();

            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i].getName() + "_" + methods[i].getReturnType());
            }

            Object obj = clas.newInstance();
            Method method = clas.getMethod("hello");
            method.invoke(obj);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public Class<?> findClass(String name, byte[] ret) throws ClassNotFoundException {
        return defineClass(name, ret, 0, ret.length);
    }

    /**
     * 读取文件内容，返回字节数组
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * byte转int类型
     * 如果byte是负数，则转出的int型是正数
     *
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
//        System.out.println("byte 是:" + b);
        int x = b & 0xff;
//        System.out.println("int 是:" + x);
        return x;
    }

    /**
     * int 类型转换为byte 类型
     * 截取int类型的最后8位,与 0xff
     *
     * @param x
     * @return
     */
    public static byte intToByte(int x) {
//        System.out.println("int 是:" + x);
//        System.out.println("int的二进制数据为:" + Integer.toBinaryString(x));
        byte b = (byte) (x & 0xff);
//        System.out.println("截取后8位的二进制数据为:" + Integer.toBinaryString(x & 0xff));
//        System.out.println("byte 是:" + b);
        return b;
    }

}
