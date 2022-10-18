package com.rusile.web_lab2.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rusile.web_lab2.entity.User;
import com.rusile.web_lab2.model.UserDAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class UserJsonDAO implements UserDAO {

    private final File file;

    public UserJsonDAO() {
        file = new File("data1.json");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



    private void saveUserInFile(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<User> list = getUsers();
        list.add(user);
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<User> getUsers() {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            StringBuilder jsonHolder = new StringBuilder();
            char[] buffer = new char[1024];
            int length = reader.read(buffer);
            while (length != -1) {
                jsonHolder.append(buffer, 0, length);
                length = reader.read(buffer);
            }
            reader.close();
            if (jsonHolder.toString().equals(""))
                return new ArrayList<>();

            ObjectMapper objectMapper = new ObjectMapper();
            List<User> userList = objectMapper.readValue(jsonHolder.toString(), new TypeReference<List<User>>() {
            });
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> getUserByToken(String sign) {
        return getUsers().stream().filter(p -> p.getSign().equals(sign)).findFirst();
    }

    @Override
    public void saveUser(User user) {
        saveUserInFile(user);
    }

    @Override
    public void updateUser(User user) {
        List<User> userList = getUsers();
        userList.stream()
                .filter(p -> p.getLogin().equals(user.getLogin()) && p.getPassword().equals(user.getPassword()))
                .forEach(p -> {
                    p.setSign(user.getSign());
                    p.setValidTill(user.getValidTill());
                });
        updateData(userList);
    }

    private void updateData(List<User> userList) {
        try {
            new FileOutputStream(file.getAbsolutePath()).close();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                objectMapper.writeValue(file, userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
