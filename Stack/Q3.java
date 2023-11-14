/**
 * Class Name: Q3 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class contains a single method that will check a given string for balanced brackets
 */

package Stack;

public class Q3 {
    public static void main(String[] args) {
        System.out.println(balancedBrackets("[G*H(J*$)]+{i + j(h+21)(23[asdf+sjfd])}"));
    }

    /**
     * Method to check if a given string has balanced brackets
     * @param str - string to be checked for balanced brackets
     * @return - true if the brackets within the string are balanced 
     */
    private static boolean balancedBrackets(String str) {
        Stack stack = new Stack();
        String[] brackets = {"(", "[", "{"};
        String[] closing = {")", "]", "}"};

        for (int i=0; i<str.length()-1; i++) {
            String substring = str.substring(i, i+1);
            // Check for opening brackets 
            if (substring.equals(brackets[0]) || 
                substring.equals(brackets[1]) || 
                substring.equals(brackets[2])) 
                {
                stack.push(new Node(substring));
            } 
            // Check for closing brackets
            if (substring.equals(closing[0]) && stack.peek().getData().equals(brackets[0])) {               
                stack.pop();
            } else if (substring.equals(closing[1]) && stack.peek().getData().equals(brackets[1])) {               
                stack.pop();
            } else if (substring.equals(closing[2]) && stack.peek().getData().equals(brackets[2])) {               
                stack.pop();
            } 
            // Ignore any other characters in the string that aren't closing brackets
            else if (!substring.equals(closing[0]) && !substring.equals(closing[1]) && !substring.equals(closing[2])) {
                ; 
            } else {
                return false; 
            }
        }
        return true;
    }
}
