package com.xtracta.supplier;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.xtracta.supplier.supplierFinder.SupplierFinder;

public class Main {

	public static void main(String[] args) {
		String supplierFilePath;
		String invoiceFilePath;
		
		Options options = buildCommandLineOptions();

		CommandLineParser parser = new DefaultParser();
	    try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args );
	        if( line.hasOption( "sf" ) && line.hasOption("if")) {
	            // initialise the member variable
	            supplierFilePath = line.getOptionValue( "sf" );
	            invoiceFilePath = line.getOptionValue("if");
	            
	            SupplierFinder supplierFinder = new SupplierFinder();
	            String foundSupplier = supplierFinder.findSupplier(invoiceFilePath, supplierFilePath);
	            String result = foundSupplier != null ? 
	            				"Fonud supplier: " + foundSupplier : 
	            				"No supplier found";
	            System.out.println(result);
	        }
	        else{
	        	HelpFormatter formatter = new HelpFormatter();
	        	formatter.printHelp( "mst", options );
	        }
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    } catch (IOException e) {
			System.err.println("File error: " + e.getMessage());
		}

	}

	/**
	 * build the command line options
	 * @return the options allowed on the command line
	 */
	private static Options buildCommandLineOptions() {
		Options options = new Options();

		Option suppliersFile = Option.builder("sf")
			    .longOpt( "supFile" )
			    .desc( "path to suppliers file"  )
			    .hasArg()
			    .argName( "suppliersFile" )
			    .build();

		Option invoiceFile = Option.builder("if")
			    .longOpt( "invFile" )
			    .desc( "path to invoice file"  )
			    .hasArg()
			    .argName( "invoiceFile" )
			    .build();

		
		options.addOption(suppliersFile);
		options.addOption(invoiceFile);
			
		return options;
	}

}
