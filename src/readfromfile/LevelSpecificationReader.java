package readfromfile;

import geometry.Block;
import levels.DefaultLevel;
import levels.LevelInformation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    /**
     * The Utils.
     */
    private Utils utils = new Utils();

    /**
     * Division to levels list.
     *
     * @param lines the lines
     * @return the list
     */
    public List<List<String>> divisionToLevels(List<String> lines) {
        List<List<String>> levels = new ArrayList<>();
        // if the file does not start with start string.
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).equals("START_LEVEL")) {
                throw new RuntimeException("File doesn't start with\"START_LEVEL\".\n" + lines.get(i));
            } else {
                // skip the "START_LEVEL" row.
                i++;
                List<String> levelData = new ArrayList<>();
                // copy the datas in the level.
                while (i < lines.size() && !lines.get(i).equals("END_LEVEL")) {
                    levelData.add(lines.get(i));
                    i++;
                }
                if (!lines.get(i).equals("END_LEVEL")) {
                    throw new RuntimeException("no \"END_LEVEL\" found.\n");
                }
                // skip "end LEVEL"
                i++;
                // add the level data to the list list.
                levels.add(levelData);
            }
            // check the start level.
            i--;
        }
        return levels;
    }

    /**
     * Division to blocks list.
     *
     * @param lines the lines
     * @return the list
     */
    public List<String> divisionToBlocksLayout(List<String> lines) {
        List<String> blockLines = new ArrayList<>();
        int i = 0;
        for (; i < lines.size() & !lines.get(i).equals("START_BLOCKS"); i++) {
            continue;
        }
        //didn't see "START_BLOCKS".
        if (i == lines.size()) {
            throw new RuntimeException("invalid game");
        }
        // skip "START_BLOCKS"
        i++;
        for (; i < lines.size() & !lines.get(i).equals("END_BLOCKS"); i++) {
            blockLines.add(lines.get(i));
        }
        //didn't see END_BLOCKS.
        if (i == lines.size()) {
            throw new RuntimeException("invalid game");
        }
        return blockLines;
    }

    /**
     * Create level from file level information.
     *
     * @param levelData the level data
     * @return the level information
     */
    public LevelInformation createLevelFromFile(List<String> levelData) {
        LevelDataMap levelDataMap = new LevelDataMap();
        levelDataMap.initializeMaps();
        List<String> levelDataWitoutBlocks = takeOutBlocksText(levelData);

        // initilize all the level data to the map.
        for (String data : levelDataWitoutBlocks) {
            levelDataMap.getUtils().setValue(data, levelDataMap.getLevelDataMap(), levelDataMap.getIsValueUpdated());
        }
        //check if all the data exist.
        levelDataMap.hasAllDataUpdated();

        //initilize arguments.
        //create level from factory.
        LevelInformationFactory levelInformationFactory = new LevelInformationFactory(levelDataMap);
        //take onlye the block data and put it in to a list.
        List<String> stringBlockLayoutData = divisionToBlocksLayout(levelData);
        //inilize block reader.
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        //path of blockDefinitionFilePath
        String blockDefinitionFilePath = levelInformationFactory.getBlockPathString();

        InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitionFilePath);
        InputStreamReader reader = new InputStreamReader(ireader);

        // read the file and convert the file to block symbol factory.
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = blocksDefinitionReader.fromReader(reader);
        // create blocks from block file and factory.
        List<Block> newBlockList = levelInformationFactory.setGameBlocks(
                stringBlockLayoutData,
                blocksFromSymbolsFactory);
        // create new level and initilize its properties.
        DefaultLevel defaultLevel = levelInformationFactory.setThisLevelInformation(new DefaultLevel(), newBlockList);
        return defaultLevel;
    }

    /**
     * Take out blocks text list.
     *
     * @param levelLines the level lines
     * @return the list
     */
    public List<String> takeOutBlocksText(List<String> levelLines) {
        int indexOfStartBlocks = levelLines.indexOf("START_BLOCKS");
        if (indexOfStartBlocks == -1) {
            throw new RuntimeException("cant find START_BLOCKS");
        }
        List levelProperties = levelLines.subList(0, indexOfStartBlocks);
        return levelProperties;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        // initialize the level list.
        List<LevelInformation> levelsInformation = new ArrayList<>();
        //split the text file to list of rows.
        List<String> fileSplitedToLines = utils.divideIntoLines(reader);
        // split all the lines to lists.
        List<List<String>> linesSplitedByLevelInfo = divisionToLevels(fileSplitedToLines);
        for (List<String> specificLevelInformation : linesSplitedByLevelInfo) {
            levelsInformation.add(createLevelFromFile(specificLevelInformation));
        }
        return levelsInformation;
    }
}