package com.thinkgem.hys.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
 



import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.google.zxing.oned.Code93Reader;

 
/**
 * 条形码工具类
 *
 * @author tangzz
 * @createDate 2015年9月17日
 *
 */
public class BarcodeUtils {
 
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file), "code39");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @param codedFormat
     * @return
     */
    public static File generateFile(String msg, String path, String codedFormat) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file), "128Mulit");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
 
    /**
     * 生成字节
     *
     * @param msg
     * @param codedFormat
     * @return
     */
    public static byte[] generate(String msg, String codedFormat) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous, codedFormat);
        return ous.toByteArray();
    }
 
    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     * @param codedFormat
     */
    public static void generate(String msg, OutputStream ous, String codedFormat) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
 
        	Code128Bean bean = new Code128Bean();
            
        	 
            // 精细度
            final int dpi = 150;
            // module宽度
            final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
     
            // 配置对象
            bean.setModuleWidth(moduleWidth);
   //         bean.setWideFactor(3);
            bean.doQuietZone(true);
            bean.setQuietZone(2);
            bean.setFontName("Helvetica");   
            bean.setFontSize(2);   
            String format = "image/png";
            try {
     
                // 输出到流
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                        BufferedImage.TYPE_BYTE_BINARY, false, 0);
     
                // 生成条形码
                bean.generateBarcode(canvas, msg);
     
                // 结束绘制
                canvas.finish();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        
        
    }
 
    /**
     * Test
     * */
    public static void main(String[] args) {
        //String msg = CommonUtils.generateNumber("93");
        //String path = "C:/Users/hys/Desktop/barcode.jpg";
        //File generateFile = generateFile(msg, path);
        //System.out.println(generateFile.getPath());
        
    }
}