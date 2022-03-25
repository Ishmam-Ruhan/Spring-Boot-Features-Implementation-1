package com.ishmamruhan.TestPackage;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Abc {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int _givenValue1 = sc.nextInt();
        int _givenValue2 = sc.nextInt();

        int checkValue1 = sc.nextInt();
        int checkValue2 = sc.nextInt();

        if(checkValue1>7 || checkValue2>7){
            System.out.println("Please enter correct position.");
        }else{

            if(checkValue1 < _givenValue1){
                int diff = Math.abs(_givenValue1-checkValue1);
                if(_givenValue2 + diff == checkValue2){
                    System.out.println("Yes. This is the correct position of TOP - Right!!!");
                }
                else if(_givenValue2 - diff == checkValue2){
                    System.out.println("Yes. This is the correct position of TOP - LEFT!!!");
                }
                else{
                    System.out.println("This is not correct position!");
                }
            }

            else if(checkValue1 > _givenValue1){
                int diff = Math.abs(_givenValue1-checkValue1);
                if(_givenValue2 + diff == checkValue2){
                    System.out.println("Yes. This is the correct position of DOWN - RIGHT!!!");
                }
                else if(_givenValue2 - diff == checkValue2){
                    System.out.println("Yes. This is the correct position of DOWN - LEFT!!!");
                }
                else{
                    System.out.println("This is not correct position!");
                }
            }
            else{
                System.out.println("This is not correct position!");
            }

        }

    }
}
