package com.company;



import java.io.*;
import java.net.URL;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class VideoDownloader {


    public static String getUserDataForVideo () throws IOException, InterruptedException{
        //Метод получения URL от пользователя из консоли
        InputStream inputUserLink = System.in;
        Reader inputStreamReader = new InputStreamReader(inputUserLink);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("Put URL page with video into concole" + "\n");
        String userLink = bufferedReader.readLine();
        System.out.println("Your link is " +"\n"+userLink);
        return userLink;
    }

    public static String getVideoElementUrl(String sourceUrl) throws IOException, InterruptedException{
        //Метод ищет HTML элемент на странице и выдёргивает URL прямой
        Document doc = Jsoup.connect(sourceUrl).get();
        Elements content = doc.getElementsByAttributeValueContaining("property","og:video");
        String url = content.attr("content");
        return url;
    }

    public static String createVideoDowloadPath () throws IOException, InterruptedException{
        InputStream inputUserPath = System.in;
        Reader inputStreamReader = new InputStreamReader(inputUserPath);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("Write dowload path into concole. Example (folder should exist!): C:\\downloads\\video.mp4");
        String userPath = bufferedReader.readLine();
        System.out.println("The file saved to " +"\n"+userPath);
        return userPath;
    }
    public static void saveVideoFile (String url, String FILE_TO ) throws IOException, InterruptedException{
        //Метод открывает поток по прямому url и записывает в файл на диске
        BufferedInputStream picture = new BufferedInputStream(new URL(url).openStream());
        Files.copy(picture, Paths.get(FILE_TO));
        picture.close();
    }

}
