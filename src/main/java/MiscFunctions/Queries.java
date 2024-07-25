package MiscFunctions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Queries extends DB_Config_DW {



    public Queries() throws SQLException {
    }


    public static String selectQuery() throws SQLException {
        String farmName = "select * from site";
        return farmName;
    }

    public static List<String> selectAll() throws SQLException, ParseException {
        ResultSet getFarmNames = DB_Config_DB.getStmt().executeQuery(selectQuery());
        List<String> farmNames = new ArrayList<>();
        while (getFarmNames.next()) {
            String farmName = getFarmNames.getString("*");
            farmNames.add(farmName);
        }
        return farmNames;
    }
}


