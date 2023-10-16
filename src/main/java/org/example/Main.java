package org.example;

//import java.sql.*;
//import java.util.*;

import java.util.Scanner;

public class Main {
    private static boolean isLoop = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.Connection();
        SqlRequest sqlRequest = new SqlRequest(sqlConnection.getConnection());

        while (isLoop) {
            System.out.print("1) Показать таблицу\n2) Добавить строку\n3) Удалить\n4) Поиск\n5) Изменить\n6) Выйти\nВыберите действие: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {sqlRequest.Print();
                         break;}
                case 2: {sqlRequest.inputLine();
                         break;}
                case 3: {
                    System.out.print("Введите фамилию: ");
                    sqlRequest.Delete(scanner.next());
                    break;
                }
                case 4: {
                    System.out.print("Введите сумму: ");
                    sqlRequest.SearchPot(scanner.nextInt());
                    break;
                }
                case 5: {
                    System.out.print("Введите сумму и фамилию: ");
                    sqlRequest.UpdatePot(scanner.nextInt(), scanner.next());
                    break;
                }
                case 6: {isLoop = false;
                    sqlConnection.Close();
                    break;}
            }
        }
        sqlConnection.Close();
    }
}