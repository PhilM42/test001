package webutilities;

/**
 * Utility class for parsing text
 */
public class TextParsingUtilities {

    /**
     * This method extracts an integer from the given text.
     *
     * @param stringToParse The input text containing numeric characters.
     * @return The extracted integer value. If the text does not contain a valid integer, 0 is returned.
     */
    public int getIntegerFromText(String stringToParse) {
        int integer = 0;
        // Remove non-numeric characters from the input text
        String numericPart = stringToParse.replaceAll("[^0-9]", "");
        try {
            // Parse the numeric part as an integer
            integer = Integer.parseInt(numericPart);
        } catch (NumberFormatException e) {
            // Handle any parsing errors (e.g., if the text doesn't contain valid numbers)
            e.printStackTrace();
        }
        return integer;
    }
}
