package MiscFunctions;

import java.sql.*;

public class DBConfig {

    public static ResultSet DBConfigure() throws SQLException {
        String UserName="akhan";
        String Password="Ancer@123!";
        String DataBaseName = "QA-IE-DB";
        String DB_URL ="jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName="+DataBaseName+";user="+UserName+";Password="+Password;
        Connection connection = DriverManager.getConnection(DB_URL,UserName,Password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select userFirstName,userLastName from 'user' where userEmail = 'junaid.alam@tenx.ai'");
        return resultSet;

    }
}
