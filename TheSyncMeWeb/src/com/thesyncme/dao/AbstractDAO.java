package com.thesyncme.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

/**
 * Abstract DAO class for all HBase domain objects.
 * 
 * @author Josivan Ribeiro
 *
 */
public abstract class AbstractDAO implements HBaseOperations {
	
	/**
	 * Attribute that defines the HBaseConfiguration instance.
	 */
	private static Configuration conf;
	
	private static Logger logger = Logger.getLogger (AbstractDAO.class);
	
	/**
	 * Default constructor.
	 */
	protected AbstractDAO () {
		conf = HBaseConfiguration.create (); 
	}	
	
	public void addRecord (String tableName, String rowKey, String[] columnFamilyNameArr, String[][] columnNameArr, String[][] dataArr) throws IOException {
		logger.info ("Start executing the method addRecord");
		logger.info ("rowKey [" + rowKey + "]");
		HTable table = new HTable (conf, tableName);
	    byte[] rowKeyArr = Bytes.toBytes (rowKey);
	    Put put = new Put (rowKeyArr);
	    for (int j = 0 ; j < columnNameArr.length ; j++) {
	    	if (columnNameArr[j].length == dataArr[j].length) {
	    		for (int i = 0 ; i < columnNameArr[j].length ; i++) {
	    			put.add (Bytes.toBytes (columnFamilyNameArr[j]), Bytes.toBytes (columnNameArr[j][i]), Bytes.toBytes (dataArr[j][i]));
	    		}
	    	}
	    }
	    logger.info ("Finish executing the method addRecord");
	    table.put (put);
    }
	
	public void addRecordWithSingleColumnFamily (String tableName, String rowKey, String columnFamilyName, String[] columnNameArr, String[] dataArr) throws IOException {
		logger.info ("Start executing the method addRecordWithSingleColumnFamily");
		logger.info ("rowKey [" + rowKey + "]");
		HTable table = new HTable (conf, tableName);
	    byte[] rowKeyArr = Bytes.toBytes (rowKey);
	    Put put = new Put (rowKeyArr);
	    if (columnNameArr.length == dataArr.length) {
	        for (int i=0; i < columnNameArr.length; i++) {
	        	put.add (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnNameArr[i]), Bytes.toBytes (dataArr[i]));
	        }
	        table.put (put);
	    }
	    logger.info ("Finish executing the method addRecordWithSingleColumnFamily");
    }
	
	public void addColumnEntry (String tableName, String columnFamilyName, byte[] rowKey, String columnName, String data) throws IOException {
		logger.info ("Start executing the method addColumnEntry");
		HTable table = new HTable(conf, tableName);
	    Put putData = new Put (rowKey);
	    putData.add (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnName), Bytes.toBytes (data));
	    table.put (putData);
	    logger.info ("Start executing the method addColumnEntry");
    }
	
	public void addColumnEntry (String tableName, String columnFamilyName, String rowKey, String columnName, String data) throws IOException {
		logger.info ("Start executing the method addColumnEntry");
		logger.info ("rowKey [" + rowKey + "]");
		HTable table = new HTable(conf, tableName);
		byte[] rowKeyArr = Bytes.toBytes (rowKey);
		Put putData = new Put (rowKeyArr);
	    putData.add (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnName), Bytes.toBytes (data));
	    table.put (putData);
	    logger.info ("Finish executing the method addColumnEntry");
    }
	
	public void removeColumnEntries (String tableName, String rowKey, String columnFamilyName, String columnNamePrefix) throws IOException {
		logger.info ("Start executing the method removeColumnEntries");
		logger.info ("rowKey [" + rowKey + "]");
		logger.info ("columnFamilyName [" + columnFamilyName + "]");
		logger.info ("columnNamePrefix [" + columnNamePrefix + "]");
		List<String> columnNameList = new ArrayList<String>();
		List<Delete> deleteList = new ArrayList<Delete>();
		HTable table = new HTable (conf, tableName);
		byte[] rowKeyArr = Bytes.toBytes (rowKey);
		Get getRowData = new Get (rowKeyArr);
        Result res = table.get (getRowData);
        if (res != null && !res.isEmpty()) {
        	NavigableMap<byte[],byte[]> familyMap = res.getFamilyMap (Bytes.toBytes (columnFamilyName));
        	// getting the column names according with the prefix
        	for (Map.Entry<byte[], byte[]> family : familyMap.entrySet()) {
        		byte[] keyArr = family.getKey();
        		String key = Bytes.toString (keyArr);
        		if (key.startsWith (columnNamePrefix)) {
					columnNameList.add (key);
				}
        	}
        }
        // removing the columns
        for (String columnName : columnNameList) {
        	Delete delete = new Delete (Bytes.toBytes(rowKey));
            delete.deleteColumn (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnName));
            deleteList.add (delete);
        }
        table.delete (deleteList);
        logger.info ("Finish executing the method removeColumnEntries");
	}
	
	public Map<String,String> getRow (String tableName, byte[] rowKey, String columnFamilyName, String[] columnNameArr) throws IOException {
		logger.info ("Start executing the method getRow");
		Map<String,String> mapResult = new HashMap<String,String>();
		HTable table = new HTable (conf, tableName);
        Get getRowData = new Get (rowKey);
        Result res = table.get (getRowData);
        if (res != null && !res.isEmpty()) {
        	for (int i = 0; i < columnNameArr.length ; i++) {
            	String columnName = columnNameArr [i];
            	byte[] obtainedRow = res.getValue (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnName));
            	String value = Bytes.toString (obtainedRow);
            	mapResult.put (columnName, value);
            }
        }
        logger.info ("Finish executing the method getRow");
        return mapResult;
	}
	
	public byte[] getRowKey (String tableName, String columnNameFamilyName, String columnName, String value) throws IOException {
		logger.info ("Start executing the method getRowKey");
		logger.info ("columnNameFamilyName [" + columnNameFamilyName + "]");
		logger.info ("columnName [" + columnName + "]");
		logger.info ("value [" + value + "]");
		byte[] rowKey = null;
		HTable table = null;
		ResultScanner rs = null;
		Scan scan = null;
		table = new HTable (conf, tableName);
		scan = new Scan ();
		rs = table.getScanner (scan);
		for (Result result : rs) {
			byte[] row = result.getValue (Bytes.toBytes (columnNameFamilyName), Bytes.toBytes (columnName));
			String rowValue = Bytes.toString (row);
			if (rowValue != null
					&& !"".equals (rowValue.trim())
					&& rowValue.equalsIgnoreCase (value)) {
				rowKey = result.getRow ();
				break;
			}
		}
		logger.info ("Finish executing the method getRowKey");
		return rowKey;
	}
	
	public  ArrayList<HashMap<String, String>> getTable (String tableName, String[] columnFamilyNameArr, String[][] columnNameArr) throws IOException {
		logger.info ("Start executing the method getTable");
		ResultScanner rs = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Result res = null;
		try {
			HTable table = new HTable (conf, tableName);
			Scan scan = new Scan ();
			rs = table.getScanner (scan);
			while((res = rs.next()) != null) {
				HashMap<String, String> map = new HashMap<String,String>();
				String s = null;
				for (int i = 0; i < columnFamilyNameArr.length ; i++) {					
					for (int j = 0 ; j < columnNameArr[i].length ; j++) {
                        byte[] obtainedRow = res.getValue (Bytes.toBytes (columnFamilyNameArr[i]), Bytes.toBytes (columnNameArr[i][j]));
                        s = Bytes.toString (obtainedRow);
                        if (s != null && !"".equals(s.trim())) {
                        	map.put (columnNameArr[i][j], s);
                        }                        
                    }
				}
				list.add (map);
			}
		}
		finally {
			rs.close ();
		}
		logger.info ("Finish executing the method getTable");
        return list;
    }
	
	public  ArrayList<HashMap<String, String>> getTableByColumnFamilyName (String tableName, String[] columnFamilyNameArr) throws IOException {
		logger.info ("Start executing the method getTableByColumnFamilyName.");
		ResultScanner rs = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Result res = null;
		try {
			HTable table = new HTable (conf, tableName);
			Scan scan = new Scan ();
			rs = table.getScanner (scan);
			while((res = rs.next()) != null) {
				HashMap<String, String> map = new HashMap<String,String>();
				for (int i = 0; i < columnFamilyNameArr.length ; i++) {
					String columnFamilyName = columnFamilyNameArr [i];
					NavigableMap<byte[],byte[]> familyMap = res.getFamilyMap (Bytes.toBytes (columnFamilyName));
					for (Map.Entry<byte[], byte[]> family : familyMap.entrySet()) {
		        		byte[] keyArr = family.getKey ();
		        		byte[] valueArr = family.getValue ();
		        		String key = Bytes.toString (keyArr);
		        		String value = Bytes.toString (valueArr);
		        		if (key != null 
		        				&& !"".equals (key.trim())
		        				&& value != null
		        				&& !"".equals (value.trim())) {
		        			map.put (key, value);
		        		}
		        	}
				}
				list.add (map);
			}
		}
		finally {
			rs.close ();
		}
		logger.info ("Finish executing the method getTableByColumnFamilyName.");
        return list;
    }
	
	public Set<String> distinct (String tableName, String columnFamilyName, String columnName) throws IOException {
		logger.info ("Start executing the method distinct");
		Set<String> set = new HashSet<String>();
	    ResultScanner rs = null;
	    Result result = null;
	    String s = null;
	    try {
	        HTable table = new HTable (conf, tableName);
	        Scan scan = new Scan();
	        scan.addColumn (Bytes.toBytes (columnFamilyName),Bytes.toBytes (columnName));
	        rs = table.getScanner (scan);
	        while((result = rs.next()) != null) {
	        	byte [] value = result.getValue (Bytes.toBytes (columnFamilyName),Bytes.toBytes (columnName));
	            s = Bytes.toString (value);
	            set.add (s);
	        }
	    }
	    finally {	        
	    	rs.close ();	        
	    }
	    logger.info ("Finish executing the method distinct");
	    return set;
	}
	  
}
