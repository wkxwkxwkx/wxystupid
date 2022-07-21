package src.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author wangkx
 */
public class MysqlUtil {


	/**
	 *
	 * @param sql insert
	 * @return
	 */
	public static int add(String sql) {
//		System.out.println("sql="+sql);
        int i=0;
        DBConnection db = new DBConnection();
        try {        
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.executeUpdate();
            preStmt.close();
            db.close();
            i = 1;
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
	}
	
	/**
	 *
	 * @param sql
	 * @param columns
	 * @return
	 */
    public static ArrayList<String[]> showUtil( String sql, String[] columns){
        
    	 ArrayList<String[]>  result = new  ArrayList<String[]>();
         DBConnection db = new DBConnection();
         try {
            Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while(rs.next()){

               String[] dataRow = new String[columns.length];
               for( int i = 0; i < dataRow.length; i++ ) {
            	   dataRow[i] = rs.getString( columns[i] );
               }
               result.add(dataRow);
            }
            rs.close();
            stmt.close();
            db.close();//
        } catch (SQLException e) {
            e.printStackTrace();
        } 
         
         return result;
    }
    
    
    /**
     *
     * @param sql
     * @return
     */
    public static int getId(String sql){
        int id = 0;
        DBConnection db = new DBConnection();
        Statement stmt = null;
        try {
            stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return id;
    }

    public static int getCount(String sql) {
		int sum = 0;
		DBConnection db = new DBConnection();
		try {
			Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
            	rs.getInt(1);
                sum++;
            	}
            rs.close();
            stmt.close();
            db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sum;
	}
    
    /**
     *
     * @param sql
     * @param columns
     * @return
     */
    public static String getJsonBySql( String sql, String[] columns){
        
//     System.err.println("sql:" + sql);
   	 ArrayList<String[]>  result = new  ArrayList<String[]>();
        DBConnection db = new DBConnection();
        try {
           Statement stmt = (Statement) db.conn.createStatement();
           ResultSet rs = (ResultSet) stmt.executeQuery(sql);
           while(rs.next()){
              String[] dataRow = new String[columns.length];
              for( int i = 0; i < dataRow.length; i++ ) {
           	   dataRow[i] = rs.getString( columns[i] );
              }
              result.add(dataRow);
           }
           rs.close();
           db.close();//
       } catch (SQLException e) {
           e.printStackTrace();
       } 
        
        return listToJson(result,columns);
   }

    /**
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        int i =0;
        DBConnection db = new DBConnection();
        try {
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.executeUpdate();
            preStmt.close();
            db.close();
            i = 1;
            System.out.println("sql" + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    

    /**
     *
     * @param sql select * from
     * @param params [id,name,sex,age]
     * @return
     */
    public static String show(String sql, String[] params){
    	
    	List< Map<String,String> > listMap = new ArrayList<>();
    	
         DBConnection db = new DBConnection();
         ResultSet rs = null;
         try {
            Statement stmt = (Statement) db.conn.createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while(rs.next()){
            	Map<String,String> map = new HashMap<String,String>();
            	for(int i = 0; i < params.length; i++) {
            		map.put(params[i], rs.getString(params[i]));
            	}
            	listMap.add(map);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return mapToJson(listMap);
    }
    
  
    

    public static int del(String delStr) {
        int i = 0;
        DBConnection db = new DBConnection();
        try {    
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(delStr);
            preStmt.executeUpdate();
            
            preStmt.close();
            db.close();
            i = 1;
            System.out.println("sql" + delStr);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    public static String mapToJson( List<Map<String,String>> mapList ) {
    	String jsonData = "{ \"data\":[";
		for(int i = 0; i < mapList.size(); i++) {
			String outstr = "[\"" ;
			int size = 0;
			for(String value : mapList.get(i).values()){
				size += 1;
				outstr += value;
				if( size < mapList.get(i).values().size() ) {
				     outstr += "\",\"";
				}
			}
		    outstr += "\"]";
		    
		    if(i < mapList.size() -1) {
		    	outstr += ",";
		    }
			jsonData += outstr;
			
		}
		jsonData += "]}";
		
		return jsonData;
    }

    public static String listToJson( ArrayList<String[]> list,String[] columns) {

    	String jsonStr = "{ \"data\":[";
    			for(int i = 0; i < list.size(); i++) {
    				String arr = "{";
    				for( int j = 0; j < list.get(0).length; j++) {
    					
    					if( list.get(i)[j] == null || "NULL".equals(list.get(i)[j])) {
    						arr += "\"\"";
    					}else {
    						arr += "\"" + columns[j] + "\""+":" ;
    						arr +=  "\"" + list.get(i)[j].replace("\"","\\\"") + "\"";
    					}
    					
    					if( j < list.get(0).length - 1 ) {
    						arr += ",";
    					}
    				}
    				arr += "}";
    				if( i < list.size() - 1 ) {
						arr += ",";
					}
    				
    				jsonStr += arr;
    			}
    			jsonStr += "]}";
    	return jsonStr;
    }

}
