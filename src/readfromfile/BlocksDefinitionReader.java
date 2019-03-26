package readfromfile;

import blockdecorator.BlockCreator;
import blockdecorator.BlockDrawerCreator;
import blockdecorator.DefultBlock;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        Map<String, String> blockDataMap = new HashMap<>();
        Map<String, String> defultDataMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();

        BlocksFromSymbolsFactory blockSymbol = new BlocksFromSymbolsFactory();
        BlockCreatorFactory blockCreatorFactory = new BlockCreatorFactory();
        BlocksDefinitionReader bdr = new BlocksDefinitionReader();
        List<String> lines = bdr.divideIntoLines(reader);

        Utils utils = new Utils();
        for (String currentLine : lines) {
            currentLine = currentLine.trim();

            //check if it start with defalut.
            if (currentLine.startsWith("default")) {
                currentLine = currentLine.substring("default".length()).trim();
                defultDataMap = utils.parseLine(currentLine);

                // consider the blocks properties.
            } else if (currentLine.startsWith("bdef")) {
                currentLine = currentLine.substring("bdef".length()).trim();
                tempMap.clear();
                tempMap = utils.parseLine(currentLine);
                blockDataMap.clear();
                blockDataMap.putAll(defultDataMap);
                blockDataMap.putAll(tempMap);

                // check if all the argument inserted
                if (blockDataMap.containsKey("symbol")) {
                    String symbol = blockDataMap.get("symbol");
                    BlockCreator currentBlock = new DefultBlock();

                    // decorate the block with all his decoration.
                    for (Map.Entry<String, String> couple : blockDataMap.entrySet()) {
                        if (couple.getKey().equals("symbol")) {
                            continue;
                        }
                        currentBlock = blockCreatorFactory.decorateBlock(currentBlock, couple.getKey(),
                                couple.getValue());
                    }
                    // if there is a frame.
                    if (blockDataMap.containsKey("stroke")) {
                        Color color = utils.setColor(blockDataMap.get("stroke"));
                        currentBlock = new BlockDrawerCreator(currentBlock, color, false, 2);
                    }
                    // add the block kind to the blockSymbol.
                    blockSymbol.addBlockCreators(symbol, currentBlock);
                }
                // add a space to map.
            } else if (currentLine.startsWith("sdef")) {
                currentLine = currentLine.substring("sdef".length()).trim();
                Map<String, String> spacerMap = utils.parseLine(currentLine);
                if (spacerMap.containsKey("symbol")
                        && spacerMap.containsKey("width")) {
                    String symbol = spacerMap.get("symbol");
                    Integer width =
                            Integer.valueOf(spacerMap.get("width"));
                    blockSymbol.addSpacerWidth(symbol, width);
                } else {
                    throw new RuntimeException("no symbol to save with");
                }
            } else {
                throw new RuntimeException("failed read line");
            }
        }
        //return the block factory after
        return blockSymbol;
    }

    /**
     * From file blocks from symbols factory.
     *
     * @param filename the filename
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromFile(String filename) {
        Reader fileReader = null;
        try {
            fileReader = new FileReader(new File(filename));
            return fromReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("cant open Block File");
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    System.out.println("IO Exception thrown while closing stream: " + ex.toString());
                }
            }
        }
    }

    /**
     * Divide into rows list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<String> divideIntoLines(java.io.Reader reader) {
        List<String> stringLines = new ArrayList<>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line;
            while ((line = is.readLine()) != "") {
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
}
