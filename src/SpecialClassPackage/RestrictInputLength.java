package SpecialClassPackage;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author shivn
 */
public class RestrictInputLength extends PlainDocument
{
    private final int limit;

    public RestrictInputLength(int limit)
    {
        this.limit = limit;
    }
  
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    if (str == null)
    {
        return;
    }
    if ((getLength() + str.length()) <= limit)
    {
      super.insertString(offs, str, a);
    }
  }
}
