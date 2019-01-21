package com.otc.api.util;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.LuminanceSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QRCode {

    /**
     * 解析指定路径下的二维码图片
     *
     * @param filePath 二维码图片路径
     * @return
     */
    private static String parseQRCode(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
