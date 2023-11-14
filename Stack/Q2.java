/**
 * Class Name: Q2 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class has a single method that will return true
 * if a given string is a Palindrome (Case sensitive) 
 */

package Stack;

public class Q2 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("Helloll"));
    }

    /**
     * Method that will check to see if a given string is a palindrome 
     * @param str - string to be checked 
     * @return - true if the string input is a palindrome 
     */
    private static boolean isPalindrome(String str) {
        Stack stack = new Stack();
        String str1 = "";
        // Push substrings into stack
        for (int i=0; i < str.length(); i++) {
            stack.push(new Node(str.substring(i, i+1)));
        }
        // Add stack items to an empty string
        for (int i=0; i < str.length(); i++) {
            str1 += stack.pop().getData();
        }
        return (str1.equals(str));
    }
}
