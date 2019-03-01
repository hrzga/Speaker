package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayServlet extends HttpServlet {

    public static List<String> audiosToPlay = new CopyOnWriteArrayList<>(); //threadsafe

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {
                out.println(audiosToPlay.remove(0));
            } catch (Exception e) {
                out.println("none");
            }
        } finally {
            out.close();  // Always close the output writer
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String audio = request.getParameter("audio");
        System.out.println("audio: " + audio);


        try {
            audiosToPlay.add(audio);

            out.println("successfully added audio to queue");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("error: " + e.getLocalizedMessage());
        } finally {
            out.close();
        }
    }


}