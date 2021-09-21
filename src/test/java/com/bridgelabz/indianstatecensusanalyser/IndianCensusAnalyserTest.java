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

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() 
	{
		try 
		{
			IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} 
		catch (CensusAnalyserException e) 
		{
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	@Test
	public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() 
	{
		try 
		{
			IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE);
		} catch (CensusAnalyserException e) 
		{
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE, e.type);
		}
	}
	@Test
	public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType()
	{
		try 
		{
			IndianCensusAnalyser censusAnalyser = new IndianCensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
		} 
		catch (CensusAnalyserException e) 
		{
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
	

}
