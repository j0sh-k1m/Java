/**
 * Class Name: Q7 
 * Assignment Name: Recursion 
 * Author's Name: Josh Kim
 * Date: April 4th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * Recursive method to see if an input string is a valid palindrome. 
 * (case sensitive) 
 * Cases: 
 * "" -> true,
 * L or l -> true,
 * LOL -> true,
 * MOm -> false,  
 */

package Recursion;

public class Q7 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("hell"));
    }

    public static boolean isPalindrome(String str) {
        // When the string is of length 0 or 1
        // It is a valid palindrome. 
        if (str.length() == 0 || str.length() == 1) {
            return true; 
        }
        // compare the first and last character of the string
        if (str.charAt(0) == str.charAt(str.length()-1)) {
            // return a call to this same method with the substring 
            // of the input without the first and last letter 
            // littil -> itti
            return isPalindrome(str.substring(1, str.length()-1));
        }
        return false;
    }
}
