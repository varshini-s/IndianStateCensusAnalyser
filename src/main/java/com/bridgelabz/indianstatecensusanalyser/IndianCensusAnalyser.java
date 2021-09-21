package com.bridgelabz.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IndianCensusAnalyser 
{

	private static final String FILE_NAME_PATTERN="^.*[.]{1}csv$";

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException 
	{
		try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
		{

			Pattern pattern = Pattern.compile(FILE_NAME_PATTERN);
			if(pattern.matcher(csvFilePath).matches()==false)
			{
				throw new CensusAnalyserException("Invalid file type",
												  CensusAnalyserException.ExceptionType.INCORRECT_FILE_TYPE);
			}

			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator  = csvToBean.iterator();

			Iterable<IndiaCensusCSV> iterable = () -> censusCSVIterator;

			int numberOfEntries = (int) StreamSupport.stream(iterable.spliterator(), false).count();
			return numberOfEntries;

		}
		catch (IOException e) 
		{
			throw new CensusAnalyserException(e.getMessage(),
											  CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) 
		{
			throw new CensusAnalyserException(e.getMessage(),
											  CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
		catch (RuntimeException e) 
		{
			throw new CensusAnalyserException(e.getMessage(),
											  CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}


}
