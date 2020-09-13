package com.je_chen.Module;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generate_VerificationCode {

    public List<Integer> Generate_Color(){
        List<Integer> RGB = new ArrayList<>();
        int R = ThreadLocalRandom.current().nextInt(0,255 + 1);
        int G = ThreadLocalRandom.current().nextInt(0,255 + 1);
        int B = ThreadLocalRandom.current().nextInt(0,255 + 1);
        RGB.add(R);
        RGB.add(G);
        RGB.add(B);
        return RGB;
    }

    public List<List> Generate_Code(int Max_Num,int Max_Alpha){
        assert Max_Num > 0;
        assert Max_Alpha > 0;
        List<Integer> Num = new ArrayList<>();
        List<String> LowAlpha = new ArrayList<>();
        List<List> Random_List = new ArrayList<>();
        for(int num_list=0;num_list<Max_Num;num_list++) {
            int Random_Num = ThreadLocalRandom.current().nextInt(0,9 + 1);
            Num.add(Random_Num);
        }
        for(int alpha_list=0;alpha_list<Max_Alpha;alpha_list++) {
            String Random_Alpha = Character.toString((char)ThreadLocalRandom.current().nextInt(97,122 + 1));
            LowAlpha.add(Random_Alpha);
        }
        Random_List.add(Num);
        Random_List.add(LowAlpha);
        return Random_List;
    }

    public List<String> Generate_Code(int Max_Alpha){
        assert Max_Alpha > 0;
        List<String> LowAlpha = new ArrayList<>();
        for(int alpha_list=0;alpha_list<Max_Alpha;alpha_list++) {
            String Random_Alpha = Character.toString((char)ThreadLocalRandom.current().nextInt(97,122 + 1));
            LowAlpha.add(Random_Alpha);
        }
        return LowAlpha;
    }

    public BufferedImage Generate_Image(int Height,int Width,int LineCount,String DrawString,int FontSize,String File_Name,boolean Save){
        assert Height > 0;
        assert Width > 0;
        assert LineCount > 0;

        List<Integer> FontRGB = this.Generate_Color();
        List<Integer> LineRGB = this.Generate_Color();
        List<Integer> BackgroundRGB = this.Generate_Color();

        Color FontColor = new Color(FontRGB.get(0), FontRGB.get(1), FontRGB.get(2));
        Color LineColor = new Color(LineRGB.get(0), LineRGB.get(1), LineRGB.get(2));

        Color BackgroundColor = new Color(BackgroundRGB.get(0), BackgroundRGB.get(1), BackgroundRGB.get(2));
        BufferedImage CodeImage = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);

        Graphics Painter = CodeImage.getGraphics();
        Painter.setColor(BackgroundColor);
        Painter.fillRect(0,0,Width,Height);
        Painter.setColor(LineColor);

        for(int pre_draw_count=0;pre_draw_count<LineCount;pre_draw_count++)
            Painter.drawLine(ThreadLocalRandom.current().nextInt(Width),0,ThreadLocalRandom.current().nextInt(Width),Height);

        Painter.setColor(FontColor);
        Painter.setFont(new Font("TimesRoman",Font.BOLD,FontSize));
        Painter.drawString(DrawString,FontSize,(Height+(FontSize/2))/2);

        Painter.setColor(LineColor);
        for(int after_draw_count=0;after_draw_count<LineCount;after_draw_count++)
            Painter.drawLine(0,ThreadLocalRandom.current().nextInt(Height),Width,ThreadLocalRandom.current().nextInt(Height));

        Painter.dispose();

        if(Save) {
            try {
                ImageIO.write(CodeImage, "png", new File(File_Name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CodeImage;
    }

    public String Image_To_Base64String(BufferedImage Image,String Type){
        String Imagebase64 = null;
        ByteArrayOutputStream ImageByteStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(Image,Type,ImageByteStream);
            byte[] ImageByte = ImageByteStream.toByteArray();
            Imagebase64 = Base64.getEncoder().encodeToString(ImageByte);
            ImageByteStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return Imagebase64;
    }

    public BufferedImage Base64String_To_Image(String Base64String){
        BufferedImage ImageBase64 = null;
        byte[] ImageByte;
        try {
            ImageByte = Base64.getDecoder().decode(Base64String);
            ByteArrayInputStream ImageByteStream = new ByteArrayInputStream(ImageByte);
            ImageBase64 = ImageIO.read(ImageByteStream);
            ImageByteStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ImageBase64;
    }

}
