/**
 * Class Name: Q1 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class has a single method that will reverse a given string
 * by using a Stack ADT. 
 */

package Stack;

public class Q1 {
    public static void main(String[] args) {
        System.out.println(reverse("String"));
    }

    /**
     * Method that will reverse a given string
     * @param str - string to be reversed
     * @return - the reversed string of the input 
     */
    private static String reverse(String str) {
        Stack stack = new Stack(); 
        String str1 = "";
        // Add substrings to stack
        for (int i=0; i < str.length(); i++) {
            stack.push(new Node(str.substring(i, i+1)));
        }
        // Reverse the string 
        for (int i=0; i < str.length(); i++) {
            str1 += stack.pop().getData();
        }
        return str1;
    }
}
