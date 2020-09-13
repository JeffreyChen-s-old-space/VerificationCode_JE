package com.je_chen.Main;

import com.je_chen.Module.Generate_VerificationCode;

import java.util.List;

public class Generate_Test {

    public static void main(String[] args) {
        Generate_VerificationCode Code = new Generate_VerificationCode();
        for(Integer i : Code.Generate_Color()){
            System.out.println(i);
        }

        System.out.println();

        for(List i : Code.Generate_Code(10,10)){
            for(Object j : i){
                System.out.print(" " + j);
            }
            System.out.println();
        }

        System.out.println("");

        for(String i : Code.Generate_Code(5)){
            System.out.print(" " + i);
        }
        System.out.println();

        StringBuilder CodeBuilder = new StringBuilder();
        for(String i : Code.Generate_Code(5)){
            CodeBuilder.append(i);
        }
        System.out.println();

        String AlphaCode = CodeBuilder.toString();
        System.out.println(AlphaCode);
        Code.Generate_Image(75,200,5,AlphaCode,40);
    }
}
