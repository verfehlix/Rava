package rava;

import java.util.ArrayList;
import java.util.Arrays;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Rava {
	RConnection connection;
	
	//CONSTRUCTOR -- establishes new connection to running RServe Server
	public Rava(){
		try {
			this.connection = new RConnection();
			System.out.println("Connection to RServe successful.");
			System.out.println();
		} catch (RserveException e) {
			System.out.println("ERROR!!!! Is the RServe Server running?");
			System.out.println("If not, go into RStudio and type:");
			System.out.println("library(Rserve)");
			System.out.println("Rserve()");
			System.out.println();
			e.printStackTrace();
		}
	}
	
	//============== Methods that interact with RServe ==============
	
	public void calculateMean(ArrayList<Double> numbers){
		try {
			//convert array list to R vector
            String vector = this.doubleListToRVector(numbers);
            
            //calculate mean in R and store resulto into variable "meanValue"
            this.connection.eval("meanValue <- mean(" + vector + ")");
            
            //get variable "meanValue" and store it in Java
            double mean = this.connection.eval("meanVal").asDouble();
            
            //print result
            System.out.println("The mean of given vector is " + mean);
            System.out.println();
            
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }
	}
	
	public void importForecast(){
		try {
			
            String importForecast = "library(forecast)";
            this.connection.eval(importForecast);
            
        } catch (RserveException e) {
            e.printStackTrace();
        }
	}
	
	public void calculateAccuracy(ArrayList<Double> forecast, ArrayList<Double> actual){
		try {
			//convert array lists to R vectors
            String forecastVector = this.doubleListToRVector(forecast);
            String actualVector = this.doubleListToRVector(actual);

            //import forecast module
            this.importForecast();
            
            //calculate different accuracy measures from forecast module using "accuracy" method and save result to "calculated_accuracy" variable in R
            this.connection.eval("calculated_accuracy <- accuracy(" + forecastVector + "," + actualVector + ")");
            
            //get variable "calculated_accuracy" and store it in a String in Java
            String[] columnNames = this.connection.eval("colnames(calculated_accuracy)").asStrings();
            double[] accuracy = this.connection.eval("calculated_accuracy").asDoubles();
            
            //print result
            System.out.println("The Measured accuracy is:");
            System.out.println(Arrays.toString(columnNames));
            System.out.println(Arrays.toString(accuracy));
            
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }
	}
	
	public void plot(ArrayList<Double> data1, ArrayList<Double> data2, String path, String fileName){
		try {
			//convert array lists to R vectors
            String data1Vector = this.doubleListToRVector(data1);
            String data2Vector = this.doubleListToRVector(data2);
            
            //calculate different accuracy measures from forecast module using "accuracy" method and save result to "calculated_accuracy" variable in R
            this.connection.eval("d1 <- " + data1Vector);
            this.connection.eval("d2 <- " + data2Vector);
            this.connection.eval("subDir <- '" + path + "'");
            this.connection.eval("ifelse(!dir.exists(file.path(getwd(), subDir)), dir.create(file.path(getwd(), subDir)), FALSE)");
            this.connection.eval("index <- c(1:length(d1))");
            this.connection.eval("png(filename='"+path + "/" + fileName + ".png',width= 1000, height = 600)");
            this.connection.eval("plot(index,d1,type='la',col='red',ylim=c(min(d1,d2)-2,max(d1,d2)+2))");
            this.connection.eval("lines(index,d2,col='green')");
            this.connection.eval("dev.off()");

            //get variable "calculated_accuracy" and store it in a String in Java
            String pwd = this.connection.eval("getwd()").asString();
            
            //print result
            System.out.println("The generated plot should be saved here:");
            System.out.println(pwd + "/" + path + "/" + fileName + ".png");
            
            
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }
	}
	
	//============== UTILITY METHODS ==============
	
	private String doubleListToRVector(ArrayList<Double> numbers) {
		String result = "c(";
		for (int i = 0; i < numbers.size(); i++) {
			double x = numbers.get(i);
			String numberString = Double.toString(x);
			result += numberString;
			if(i != numbers.size() -1){
				result += ", ";
			}
		}
		result += ")";
		
		return result;
	}
}
