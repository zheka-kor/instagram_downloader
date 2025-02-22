package com.company;
import java.io.*;
import java.net.URL;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.company.VideoDownloader.*;


public class Main {




    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner choice = new Scanner(System.in);
        System.out.println("Write into console 1 for download picture and 2 for download video. Press enter ");
        Integer userChoise = choice.nextInt();

        if (userChoise == 1) {
            String userData = getUserData();
            String path = createDowloadPath();
            String element = getElementUrl(userData);
            saveToFile(element, path);
        } else if (userChoise == 2) {
            String userVideo = getUserDataForVideo();
            String videoPath = createVideoDowloadPath();
            String videoElement = getVideoElementUrl(userVideo);
            saveVideoFile(videoElement, videoPath);
        }
    }

    public static String getUserData () throws IOException, InterruptedException{
        //Метод получения URL от пользователя из консоли
        InputStream inputUserLink = System.in;
        Reader inputStreamReader = new InputStreamReader(inputUserLink);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("Put URL page with pucture into concole" + "\n");
        String userLink = bufferedReader.readLine();
        System.out.println("Your link is " +"\n"+userLink);
        return userLink;
    }
    public static String createDowloadPath () throws IOException, InterruptedException{
        InputStream inputUserPath = System.in;
        Reader inputStreamReader = new InputStreamReader(inputUserPath);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("Write dowload path into concole. Example (folder should exist!): C:\\downloads\\pucture.jpg");
        String userPath = bufferedReader.readLine();
        System.out.println("The file saved to " +"\n"+userPath);
        return userPath;
    }

    public static String getElementUrl(String sourceUrl) throws IOException, InterruptedException{
        //Метод ищет HTML элемент на странице и выдёргивает URL прямой
        Document doc = Jsoup.connect(sourceUrl).get();
        Elements content = doc.getElementsByAttributeValueContaining("property","og:image");
        String url = content.attr("content");
        System.out.println(url);
        return url;
    }

    public static void saveToFile (String url, String FILE_TO ) throws IOException, InterruptedException{
        //Метод открывает поток по прямому url и записывает в файл на диске
            BufferedInputStream picture = new BufferedInputStream(new URL(url).openStream());
            Files.copy(picture, Paths.get(FILE_TO));
            picture.close();
    }
}

