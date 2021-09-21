package com.bridgelabz.indianstatecensusanalyser;


public class CensusAnalyserException extends Exception 
{
	enum ExceptionType 
	{
		CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,INCORRECT_FILE_TYPE,CSV_FILE_INTERNAL_ISSUES
	}

	ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) 
	{
		super(message);
		this.type = type;
	}
}
