package proven.msg;

import java.util.*;

/**
 * <strong>PropertiesLanguage.java</strong>
 * Read labels from Properties Files of languages
 * @author ProvenSoft
 */
public class PropertiesLanguage {
 
   static String FILE_PROPERTIES = "proven.msg.labelsbundle";
    
   public static String returnValue(Locale currentLocale, String key) {
      ResourceBundle labels = ResourceBundle.getBundle(FILE_PROPERTIES,currentLocale);
      return labels.getString(key);
   } 
   
   public static int returnValueInt(Locale currentLocale, String key) {
      ResourceBundle labels = ResourceBundle.getBundle(FILE_PROPERTIES,currentLocale);
      return Integer.parseInt(labels.getString(key));
   } 

   public static void displayValue(Locale currentLocale, String key) {
      ResourceBundle labels = ResourceBundle.getBundle(FILE_PROPERTIES,currentLocale);
      String value  = labels.getString(key);
      System.out.println(
           "Locale = " + currentLocale.toString() + ", " +
           "key = " + key + ", " +
           "value = " + value); 
   } 
   
   private static void iterateKeys(Locale currentLocale) {
      ResourceBundle labels = 
         ResourceBundle.getBundle(FILE_PROPERTIES,currentLocale);
       Enumeration bundleKeys = labels.getKeys();
      while (bundleKeys.hasMoreElements()) {
         String key = (String)bundleKeys.nextElement();
         String value  = labels.getString(key);
         System.out.println("key = " + key + ", " +
           "value = " + value);
      }
 
   } 
 
}