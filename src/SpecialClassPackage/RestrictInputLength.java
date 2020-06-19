package SpecialClassPackage;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * PDC Assignment 2
 * This is the RestrictInputLength Class
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class RestrictInputLength extends PlainDocument
{
    /**
     * Variables
     */
    private final int limit;

    /**
     * Constructor
     * @param limit   The amount of characters the player can enter in the login screen for each the username and password 
     */
    public RestrictInputLength(int limit)
    {
        this.limit = limit;
    }
  
    /**
     * This method will limit the character the player can enter
     * @param offset  The offset for the string
     * @param str   The string
     * @param attributes    The attributes limiting the string
     * @throws BadLocationException 
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attributes) throws BadLocationException
    {
        if (str == null)
        {
            return;
        }
        if ((getLength() + str.length()) <= limit)
        {
            super.insertString(offset, str, attributes);
        }
    }
}
