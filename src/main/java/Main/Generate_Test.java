package Main;

import Module.Generate_VerificationCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Generate_Test {

    public static void main(String[] args) {
        Generate_VerificationCode Code = new Generate_VerificationCode();
        for (Integer i : Code.Generate_Color()) {
            System.out.println(i);
        }

        System.out.println();

        Code.Generate_Code(10, 10).forEach(i -> {
            for (Object j : i) {
                System.out.print(" " + j);
            }
            System.out.println();
        });

        System.out.println();

        for (String i : Code.Generate_Code(5)) {
            System.out.print(" " + i);
        }
        System.out.println();

        StringBuilder CodeBuilder = new StringBuilder();
        for (String i : Code.Generate_Code(5)) {
            CodeBuilder.append(i);
        }
        System.out.println();

        String AlphaCode = CodeBuilder.toString();
        System.out.println(AlphaCode);
        BufferedImage BFImage = Code.Generate_Image(75, 200, 5, AlphaCode, 40, "CodeImage.png", true);

        System.out.println();
        System.out.println(BFImage == null);

        System.out.println();
        String Base64String = Code.Image_To_Base64String(BFImage, "png");
        System.out.println(Base64String);

        System.out.println();
        BufferedImage Base64Image = Code.Base64String_To_Image(Base64String);
        try {
            ImageIO.write(Base64Image, "png", new File("Base64 TO Image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
