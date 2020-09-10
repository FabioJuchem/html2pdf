package com.poc.converter.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.dom.Document;
import org.w3c.tidy.Configuration;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Service
public class Converter {

    private static ITemplateEngine templateEngine;

    public Converter(ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public Document convert() {
        try {
            OutputStream os = new FileOutputStream("C:\\Projetos\\POCS\\poc-html-pdf\\hello.pdf");

            var template = "manager-notification";

            var context = new Context();

            var user = new User();

            context.setVariable("user", user);

            return convert(templateEngine.process(template, context), os);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static String getHTML() {
        try {
            String text = "";
            FileReader in = new FileReader("C:/manager-notification.html");

            BufferedReader buffer = new BufferedReader(in);



            while(buffer.ready()) {
                text += buffer.readLine();
            }


            return text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    public static Document convert(String input, OutputStream out) throws Exception {
        return convert(new ByteArrayInputStream(input.getBytes()), out);
    }

    public static Document convert(InputStream input, OutputStream out) throws Exception {
        Tidy tidy = new Tidy();
        tidy.setCharEncoding(Configuration.UTF8);
        Document doc = tidy.parseDOM(input, null);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(out);
        return renderer.getDocument();
    }

    public static class User {
        String name = "TMaik";

        String lastName = "Cunha";

        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
