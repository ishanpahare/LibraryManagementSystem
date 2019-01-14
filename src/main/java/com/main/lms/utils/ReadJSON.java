package com.main.lms.utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.main.lms.dao.BookDaoImpl;
import com.main.lms.entities.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSON {

    public static void getJson() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("books.json")) {
            Object obj = parser.parse(reader);
            JSONArray booklist = (JSONArray) obj;
            booklist.forEach(book -> parseBookObject((JSONObject) book));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void parseBookObject(JSONObject book) {

        JSONObject bookObject = (JSONObject) book.get("book");


        String name = (String) bookObject.get("name");
        System.out.println("Name: " + name);


        Integer isbn = (int) ((long) bookObject.get("isbn"));
       // System.out.println("Isbn: " + isbn);

        //Integer uid = (int)(long) bookObject.get("id");

        String author = (String) bookObject.get("author");
        // System.out.println("author: " + author);

        Integer price = (int) ((long) bookObject.get("price"));

        //Integer quantity=(int)((long) bookObject.get("quantity"));
        String publisher = (String) bookObject.get("publisher");
        //System.out.println(publisher);

        //Book bookToBeAdded=new Book(isbn,name,price,author,publisher);
        //BookDaoImpl bookDao=new BookDaoImpl();
        //bookDao.save(bookToBeAdded);

    }
}

