/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author dell pc
 */
public class Admin
{

    public ArrayList<String> getStations()
    {/*
         String DB_Driver = getServletContext().getInitParameter("DB_Driver");
         String DB_Con = getServletContext().getInitParameter("DB_Con");
         String DB_Uname = getServletContext().getInitParameter("DB_Username");
         String DB_Password = getServletContext().getInitParameter("DB_Password");
         */

        Connection con = null;
        ResultSet resultSet;
        ArrayList<String> arrayListId = new ArrayList<String>();
        ArrayList<String> arrayListName = new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/trainreservation", "root", "");
            PreparedStatement preparedStatement;
            //The query can be update query or can be select query
            String query = "select id,Name from station";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                arrayListId.add(resultSet.getString(1));
                arrayListName.add(resultSet.getString(2));
            }

        }
        catch (Exception e)
        {

        }
        finally
        {
            try
            {
                if (con != null)
                {
                    con.close();
                }
            }
            catch (Exception ex)
            {
            }
        }

        return arrayListName;

    }

    public static String getDriver()
    {
        return "com.mysql.jdbc.Driver";
    }

    public static String getConnnectionString()
    {
        return "jdbc:mysql://localhost/trainreservation";
    }

    public static String getUserName()
    {
        return "root";
    }

    public static String getPassword()
    {
        return "";
    }
}
