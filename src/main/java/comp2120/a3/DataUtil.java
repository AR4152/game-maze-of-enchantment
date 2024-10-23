package comp2120.a3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Data loading
 */
public class DataUtil {
    //load the maze and data from json package
    public final static int TYPE_PATH = 0;
    public final static int TYPE_WALL = 2;
    public final static int TYPE_NPC = 3;
    public final static int TYPE_MONSTER = 4;

    public final static int IMAGE_WIDTH = 34;
    public final static int IMAGE_HEIGHT = 34;
    public final static int MAX_ROW = 22;
    public final static int MAX_COL = 22;

    public static ArrayList<MazeMap> levels = new ArrayList<>();

    /**
     * load and analysis data from the mazemap.json package
     * @return true or false
     * @author WangLin Song
     */
    public static boolean loadData() {
        //load and analysis data from the mazemap.json package
        try {
            String filePath = "/mazemap.json";
            String dataStr = readFile(filePath);
            if (isStringEmpty(dataStr)) {
                return false;
            }
            JSONArray jsonArray = new JSONArray(dataStr);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject != null) {
                        String name = jsonObject.getString("name");
                        int row = jsonObject.getInt("row");
                        int col = jsonObject.getInt("col");
                        int[][] map = convertJSONToArray(jsonObject.getJSONArray("map"), row, col);
                        if (name != null && map != null) {
                            if (name.equals("mainMap")) {
                                MazeMap gameMap = new MazeMap();
                                gameMap.setName(jsonObject.getString("name"));
                                gameMap.setIndex(jsonObject.getInt("index"));
                                gameMap.setObserveTime(jsonObject.getInt("observeTime"));
                                gameMap.setLimitedTime(jsonObject.getInt("limitedTime"));
                                gameMap.setDark(jsonObject.getBoolean("isDark"));
                                gameMap.setMap(map);
                                levels.add(gameMap);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Read data from the file
     *
     * @param fileName Path of file relative to resources folder
     * @return Content of file or null if file not found
     *
     * @author WangLin Song
     */
    public static String readFile(String fileName) {
        //read data from the file
        try {
            InputStream fileStream = DataUtil.class.getResourceAsStream(fileName);
            Reader reader = new InputStreamReader(fileStream,"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * convert the data from json to array
     * @param jsonArray
     * @param row
     * @param col
     * @return array or null
     * @author WangLin Song
     */
    public static int[][] convertJSONToArray(JSONArray jsonArray, int row, int col) {
        // convert the data from json to array
        if (jsonArray == null || jsonArray.length() <= 0) {
            return null;
        }
        try {
            int[][] array = new int[row][col];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray rowArray = jsonArray.getJSONArray(i);
                if (rowArray != null) {
                    for (int j = 0; j < rowArray.length(); j++) {
                        array[i][j] = rowArray.getInt(j);
                    }
                }
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get the map of current level
     * @param index
     * @return MazeMap or null
     * @author WangLin Song
     */
    public static MazeMap getLevel(int index) { //TODO
        //get the map of current level
        if (index < 0 || index >= levels.size()) {
            return null;
        }
        return levels.get(index);
    }

    /**
     * check if the string is empty
     * @param str
     * @return true of false
     */
    public static boolean isStringEmpty(String str) {
        return str == null || str.length() == 0;
    }

}
