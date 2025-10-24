package practice.tutorial.edureka;

import java.util.Scanner;

public class PrintUniqueChars {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please provide the input String : ");
        String input = scanner.nextLine();
        int ch[]=new int[128];
        for(int i=0;i<input.length();i++)
        {
            ch[input.charAt(i)]=ch[input.charAt(i)]+1;
        }

        System.out.print("Unique Chars in given String - ");
        for(int i=0;i<ch.length;i++)
        {
            if(ch[i]==1)
            {
                System.out.print((char)i);
            }
        }
    }
}
