package readfromfile;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * Divide into rows list.
     *
     * @param reader the reader
     * @return the list
     */
    public java.util.List<String> divideIntoLines(java.io.Reader reader) {
        List<String> stringLines = new ArrayList<>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line;
            while ((line = is.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    stringLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
            return stringLines;
        }
    }

    /**
     * Sets image.
     *
     * @param stringBackGround the string back ground
     * @return the image
     */
    public Image setImage(String stringBackGround) {
        stringBackGround = stringBackGround.substring("image(".length());
        stringBackGround = stringBackGround.replace(")", "");
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(stringBackGround);
            Image image = ImageIO.read(inputStream);
            return image;
        } catch (IOException e) {
            throw new RuntimeException("failed of load" + stringBackGround + "image path");
        }
    }

    /**
     * Choose background color.
     *
     * @param s the s
     * @return the color
     */
    public Color setColor(String s) {
        if (s.startsWith("color(")) {
            s = s.substring("color(".length());
            if (s.startsWith("RGB(")) {
                s = s.substring("RGB(".length());
                s = s.replace("))", "");
                String[] parts = s.split(",");
                if (!(parts.length == 3)) {
                    throw new RuntimeException("Error in reading file");
                }
                int r = Integer.valueOf(parts[0]);
                int g = Integer.valueOf(parts[1]);
                int b = Integer.valueOf(parts[2]);
                return new Color(r, g, b);
            } else {
                s = s.replace(")", "");
                if (s.equals("black")) {
                    return Color.BLACK;
                } else if (s.equals("blue")) {
                    return Color.BLUE;
                } else if (s.equals("cyan")) {
                    return Color.CYAN;
                } else if (s.equals("gray")) {
                    return Color.GRAY;
                } else if (s.equals("lightGray")) {
                    return Color.LIGHT_GRAY;
                } else if (s.equals("green")) {
                    return Color.GREEN;
                } else if (s.equals("orange")) {
                    return Color.ORANGE;
                } else if (s.equals("pink")) {
                    return Color.PINK;
                } else if (s.equals("red")) {
                    return Color.RED;
                } else if (s.equals("white")) {
                    return Color.WHITE;
                } else if (s.equals("yellow")) {
                    return Color.YELLOW;
                } else {
                    throw new RuntimeException("Error in reading file");
                }
            }
        } else {
            throw new RuntimeException(
                    "error in reading file");
        }
    }

    /**
     * Conver to double double.
     *
     * @param string the string
     * @return the double
     */
    public double convertToDouble(String string) {
        try {
            double num = Double.parseDouble(string);
            if (num < 0) {
                throw new RuntimeException("invalid number");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new RuntimeException("invalid number");
        }
    }

    /**
     * Conver to int int.
     *
     * @param string the string
     * @return the int
     */
    public int converToInt(String string) {
        try {
            int num = Integer.parseInt(string);
            if (num < 0) {
                throw new RuntimeException("invalid number");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new RuntimeException("invalid number");
        }
    }

    /**
     * sets value to keys if valid, throws Exception otherwise.
     *
     * @param data           the data
     * @param levelDataMap   the level data map
     * @param isValueUpdated the is value updated
     */
    public void setValue(String data, Map<String, String> levelDataMap, Map<String, Boolean> isValueUpdated) {
        String key;
        String values;
        if (data.contains(":")) {
            String[] parts = data.split(":");
            key = parts[0];
            values = parts[1];
        } else {
            throw new IllegalArgumentException("String " + data + " does not contain :");
        }
        if (levelDataMap.containsKey(key)) {
            if (isValueUpdated.get(key)) {
                // Exception if value already updated.
                throw new IllegalArgumentException("String " + key + " already updated");
            }
            // update map value booliean and mark key as updated.
            levelDataMap.put(key, values);
            isValueUpdated.put(key, true);
        } else {
            throw new RuntimeException("Incorrect input");
        }
    }

    /**
     * return tree value.
     *
     * @param line the line
     * @return new parse map.
     */
    public Map<String, String> parseLine(String line) {

        Map<String, String> valueMap = new HashMap<>();

        String[] pairs = line.split(" ");
        for (String pair : pairs) {
            String[] parts = pair.split(":");
            if (parts.length != 2) {
                throw new RuntimeException("Error in file");
            }
            valueMap.put(parts[0], parts[1]);
        }
        return valueMap;
    }
}
