package com.bridgelabz.indianstatecensusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndianCensusAnalyserTest {


	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./main/resources/IndiaStateCensusData.csv";
	private static final String WRONG_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusDataWrongDelimeter.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCensusDataIncorrectHeader.csv";

	private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String INDIAN_STATE_CODE_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCodeWrongDelimeter.csv";
	private static final String INDIAN_STATE_CODE_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCodeDataIncorrectHeader.csv";

	@Test
	public void givenIndianCensusCSVFile_ReturnsCorrectRecords() 
	{
		try 
		{
			IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		}
		catch (CensusAnalyserException e) 
		{
		}
	}

	
}
