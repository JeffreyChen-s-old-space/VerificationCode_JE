package Module;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Generate_VerificationCodeTest {

    Generate_VerificationCode Code = new Generate_VerificationCode();

    @Test
    void generate_Color() {
        for (Integer i : Code.Generate_Color()) {
            System.out.println(i);
        }
        System.out.println();
    }

    @Test
    void generate_Code() {
        for (String i : Code.Generate_Code(5)) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    @Test
    void generate_Image() {
        StringBuilder CodeBuilder = new StringBuilder();
        for (String i : Code.Generate_Code(5)) {
            CodeBuilder.append(i);
        }
        System.out.println();

        String AlphaCode = CodeBuilder.toString();
        System.out.println(AlphaCode);
        BufferedImage BFImage = Code.Generate_Image(75, 200, 5, AlphaCode, 40, "CodeImage.png", true);
        System.out.println();
    }

    @Test
    void image_To_Base64String() {
        StringBuilder CodeBuilder = new StringBuilder();
        for (String i : Code.Generate_Code(5)) {
            CodeBuilder.append(i);
        }
        System.out.println();

        String AlphaCode = CodeBuilder.toString();
        System.out.println(AlphaCode);
        BufferedImage BFImage = Code.Generate_Image(75, 200, 5, AlphaCode, 40, "CodeImage.png", true);

        System.out.println();
        String Base64String = Code.Image_To_Base64String(BFImage, "png");
        System.out.println(Base64String);

    }

    @Test
    void base64String_To_Image() {
        StringBuilder CodeBuilder = new StringBuilder();
        for (String i : Code.Generate_Code(5)) {
            CodeBuilder.append(i);
        }
        System.out.println();

        String AlphaCode = CodeBuilder.toString();
        System.out.println(AlphaCode);
        BufferedImage BFImage = Code.Generate_Image(75, 200, 5, AlphaCode, 40, "CodeImage.png", true);

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