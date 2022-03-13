package com.aem.hospice.arean;
import java.util.*;
public class Encryption {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> Shuffledlist;
    private char character;
    private String line;
    private char[] letters;
    private char[] Secretletters;

    Encryption(){
        scanner = new Scanner(System.in);
        random= new Random();
        list= new ArrayList();
        Shuffledlist= new ArrayList();
        character= ' ';
        newkey();
        askquestion();
    }
    private void askquestion(){
        while(true){
            System.out.println("****************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewkey, (G)etkey (E)ncrypt, (D)ecrypt, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (response){
                case 'N' :
                    newkey();
                    break;
                case 'G' :
                    getkey();
                    break;
                case 'E' :
                    encrypt();
                    break;
                case 'D' :
                    decrypt();
                    break;
                case 'Q' :
                    quit();
                    break;
                default:
                    System.out.println("Not a valid input");
                    break;
            }

        }


    }
    private void newkey(){
        character = ' ';
        list.clear();
        Shuffledlist.clear();
        for(int i=32; i<127; i++){
            list.add(Character.valueOf(character));
            character++;
        }
        Shuffledlist = new ArrayList(list);
        Collections.shuffle(Shuffledlist);
        System.out.println("A new key has been generated");


    }
    private void getkey(){
        System.out.println("Key: ");
        for(Character x:list){
            System.out.print(x);
        }
        System.out.println();
        for(Character x : Shuffledlist){
            System.out.print(x);
        }
        System.out.println();

    }
    private void encrypt(){
        System.out.println("Enter a message to be encrypted: ");
        String message = scanner.nextLine();
        letters = message.toCharArray();

    }
    private void decrypt(){

    }
    private void quit(){

    }

    public static void main(String[] args) {
        Encryption e = new Encryption();
    }


}