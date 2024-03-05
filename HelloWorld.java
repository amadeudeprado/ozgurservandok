public class HelloWorld {
    // This is the main method, the entry point of the Java program.
    public static void main(String[] args) {
        // Print the text "Hello, World!" to the console.
        System.out.println("Hello, World!");

        System.out.println(firstSecondName("Ozgur", "Dok"));
    }

    public static String firstSecondName(String firstName, String secondName){
        
        return firstName + " " + secondName;
    }
}
