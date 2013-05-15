package com.thesyncme.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Interface that defines the default operations for the HBase column-oriented store.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface HBaseOperations {
	
	/**
	 * Adds a record with any number of column families.
	 * 
	 * @param tableName the table name.
	 * @param rowKey the row key.
	 * @param columnFamilyNameArr the column family array.
	 * @param columnNameArr the column name array.
	 * @param dataArr the data array.
	 * @throws IOException
	 */
	public void addRecord (String tableName, String rowKey, String[] columnFamilyNameArr, String[][] columnNameArr, String[][] dataArr) throws IOException;
	
	/**
	 * Writes a record to a table having just one column family or write only a portion of a record.
	 * 
	 * @param rowKey the row key.
	 * @param tableName the table name.
	 * @param columnFamilyName the column family.
	 * @param columnNameArr the column name array.
	 * @param dataArr the data array.
	 * @throws IOException
	 */
	public void addRecordWithSingleColumnFamily (String tableName, String rowKey, String columnFamilyName, String[] columnNameArr, String[] dataArr) throws IOException;
	
	/**
	 * Assigns a value to a particular column of a record.
	 * 
	 * @param tableName the table name.
	 * @param columnFamilyName the column family.
	 * @param rowKey the row key.
	 * @param columnName the column name.
	 * @param data the value to be added.
	 * @throws IOException
	 */
	public void addColumnEntry (String tableName, String columnFamilyName, byte[] rowKey, String columnName, String data) throws IOException;
	
	/**
	 * Assigns a value to a particular column of a record.
	 * 
	 * @param tableName the table name.
	 * @param columnFamilyName the column family name.
	 * @param rowKey the row key as a string.
	 * @param columnName the column name.
	 * @param data the value to be added.
	 * @throws IOException
	 */
	public void addColumnEntry (String tableName, String columnFamilyName, String rowKey, String columnName, String data) throws IOException;
	
	/**
	 * Removes column entries from a specific record.
	 * 
	 * @param tableName the table name.
	 * @param rowKey the row key.
	 * @param columnFamilyName the column family name.
	 * @param columnNamePrefix the column name prefix.
	 * @throws IOException
	 */
	public void removeColumnEntries (String tableName, String rowKey, String columnFamilyName, String columnNamePrefix) throws IOException;
	
	/**
	 * Gets a row as a map according with the row id.
	 * 
	 * @param tableName the table name.
	 * @param rowKey the row key.
	 * @param columnFamilyName the column family name.
	 * @param columnNameArr the column name array.
	 * @return a map containing the row key-values.
	 * @throws IOException
	 */
	public Map<String,String> getRow (String tableName, byte[] rowKey, String columnFamilyName, String[] columnNameArr) throws IOException;
	
	/**
	 * Gets a list of HashMap, each HashMap containing entries of a single record.
	 * 
	 * @param tableName the table name.
	 * @param columnFamilyNameArr the column family array;
	 * @param columnNameArr the column name array.
	 * @return list of HashMap.
	 * @throws IOException
	 */
	public  ArrayList<HashMap<String, String>> getTable (String tableName, String[] columnFamilyNameArr, String[][] columnNameArr) throws IOException;
	
	/**
	 * Gets a list of HashMap containing entries of a single record
	 * according with the specified column family array.
	 * 
	 * @param tableName the table name.
	 * @param columnFamilyNameArr the column family name array.
	 * @return list of HashMap.
	 * @throws IOException
	 */
	public  ArrayList<HashMap<String, String>> getTableByColumnFamilyName (String tableName, String[] columnFamilyNameArr) throws IOException;
	
	/**
	 * Gets the row key from a row given its value.
	 * 
	 * @param tableName the table name.
	 * @param columnNameFamilyName the column name family.
	 * @param columnName the column name.
	 * @param value the value.
	 * @return a array of bytes representing the row key.
	 * @throws IOException
	 */
	public byte[] getRowKey (String tableName, String columnNameFamilyName, String columnName, String value) throws IOException;
	
	/**
	 * Obtains distinct column entries from a given table.
	 * 
	 * @param tableName the table name.
	 * @param columnFamilyName the column family name.
	 * @param columnName the column name.
	 * @return
	 * @throws IOException
	 */
	public Set<String> distinct (String tableName, String columnFamilyName, String columnName) throws IOException;	

}
