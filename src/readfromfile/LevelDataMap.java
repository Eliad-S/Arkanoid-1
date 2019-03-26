package readfromfile;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Level data map.
 */
public class LevelDataMap {
    private Map<String, String> levelDataMap;
    private Map<String, Boolean> isValueUpdated;
    /**
     * The Utils.
     */
    private Utils utils = new Utils();

    /**
     * Instantiates a new Level data map.
     */
    public LevelDataMap() {
        this.levelDataMap = new HashMap<>();
        this.isValueUpdated = new HashMap<>();
        initializeMaps();
    }

    /**
     * Initialize level data map.
     */
    public void initializeMaps() {
        levelDataMap.put("level_name", null);
        levelDataMap.put("ball_velocities", null);
        levelDataMap.put("background", null);
        levelDataMap.put("paddle_speed", null);
        levelDataMap.put("paddle_width", null);
        levelDataMap.put("block_definitions", null);
        levelDataMap.put("blocks_start_x", null);
        levelDataMap.put("blocks_start_y", null);
        levelDataMap.put("row_height", null);
        levelDataMap.put("num_blocks", null);
        //booliean map
        isValueUpdated.put("level_name", false);
        isValueUpdated.put("ball_velocities", false);
        isValueUpdated.put("background", false);
        isValueUpdated.put("paddle_speed", false);
        isValueUpdated.put("paddle_width", false);
        isValueUpdated.put("block_definitions", false);
        isValueUpdated.put("blocks_start_x", false);
        isValueUpdated.put("blocks_start_y", false);
        isValueUpdated.put("row_height", false);
        isValueUpdated.put("num_blocks", false);
    }

    /**
     * check if all data updated, throws Exception if not.
     */
    public void hasAllDataUpdated() {
        for (Boolean wasUpadted : isValueUpdated.values()) {
            if (!wasUpadted) {
                throw new IllegalArgumentException("One or more data was not inserted");
            }
        }
    }

    /**
     * Gets level data map.
     *
     * @return the level data map
     */
    public Map<String, String> getLevelDataMap() {
        return levelDataMap;
    }

    /**
     * Gets is value updated.
     *
     * @return the is value updated
     */
    public Map<String, Boolean> getIsValueUpdated() {
        return isValueUpdated;
    }

    /**
     * Gets value.
     *
     * @param key the key
     * @return the value
     */
    public String getValue(String key) {
        return levelDataMap.get(key);
    }

    /**
     * Gets utils.
     *
     * @return the utils
     */
    public Utils getUtils() {
        return utils;
    }
}
