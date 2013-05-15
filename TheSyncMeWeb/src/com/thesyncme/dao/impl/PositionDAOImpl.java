package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Position;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.PositionDAO;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class PositionDAOImpl extends AbstractDAO implements PositionDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (PositionDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		"Position"
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		PositionColumns.POSITION_ID.getColumnName (),
		PositionColumns.NAME.getColumnName ()
	};
	/**
	 * Position enum columns.
	 *
	 */
	private enum PositionColumns {
		POSITION_ID ("positionId"), 
		NAME ("name");
						
		private String columnName;
				
		private PositionColumns (String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return columnName;
		}		
	}	
	
	@Override
	public List<Position> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Position> positionList = new ArrayList<Position> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Position table
			list = getTable (Constants.POSITION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String positionId = map.get (PositionColumns.POSITION_ID.getColumnName());
				String name = map.get (PositionColumns.NAME.getColumnName());		
				Position position = new Position ();
				position.setPositionId (new Long(positionId));
				position.setName (name);
				positionList.add (position);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return positionList;
	}

	@Override
	public List<Position> findByPositionNamePrefix (String positionNamePrefix) throws DataAccessException {
		logger.info ("Start executing the method findByPositionNamePrefix.");
		List<Position> positionList = new ArrayList<Position> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Company table
			list = getTable (Constants.POSITION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String positionName = map.get (PositionColumns.NAME.getColumnName());
				if (positionName != null && !"".equals (positionName.trim())) {
					if (positionName.startsWith (positionNamePrefix)) {
						String positionId = map.get (PositionColumns.POSITION_ID.getColumnName());						
						Position position = new Position ();
						position.setPositionId (new Long(positionId));
						position.setName (positionName);						
						positionList.add (position);
					}
				}
			}			
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the search by position name prefix operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByPositionNamePrefix.");
		return positionList;
	}

}
