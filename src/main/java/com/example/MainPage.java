package com.example;

import javax.swing.*;
import java.awt.*;

public class MainPage {

    private static GitInfos gitInfos;

    public static void main(String[] args){
        System.out.println("\nHello, Maven Build");

        gitInfos = new GitInfos();

        StringBuilder sb = new StringBuilder();
        sb.append("Version\n\n");
        sb.append("build time : " + gitInfos.getBuildTime() + "\n");
        sb.append("version : " + gitInfos.getCommitIDDesc() + "\n");
        System.out.println(sb.toString());
    }

}
