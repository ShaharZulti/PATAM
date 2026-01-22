package test;

import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;
    
    /**
     * Primary constructor that receives a String and performs all conversions.
     */
    public Message(String text) {
        this.date = new Date();
        this.asText = text;
        this.data = text.getBytes();
        this.asDouble = convertToDouble(text);
    }
    
    /**
     * Constructor that receives a byte array.
     */
    public Message(byte[] data) {
        this(new String(data));
    }
    
    /**
     * Constructor that receives a double value.
     */
    public Message(double value) {
        this(String.valueOf(value));
    }
    
    /**
     * Attempts to convert the text representation to a Double.
     * Returns Double.NaN if conversion fails.
     */
    private double convertToDouble(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}
