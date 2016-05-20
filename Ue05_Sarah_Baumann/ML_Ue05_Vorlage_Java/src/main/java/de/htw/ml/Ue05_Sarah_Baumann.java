package de.htw.ml;

import java.io.IOException;
import java.util.Arrays;

import org.jblas.FloatMatrix;
import org.jblas.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Ue05_Sarah_Baumann extends Application {

	public static final String title = "Line Chart";
	public static final String xAxisLabel = "Car Index";
	public static final String yAxisLabel = "mpg";
	public static final int M = 392;
	
	public static void main(String[] args) throws IOException {
		FloatMatrix cars = FloatMatrix.loadCSVFile("cars_jblas.csv");
		FloatMatrix cars_norm = normalize(cars);
		int[] liste = new int[] {0,1,2,3,4,5};
		FloatMatrix cars_norm_ohnegpm = cars_norm.getColumns(liste);
		FloatMatrix mpg_norm = cars_norm.getColumn(6);
//		System.out.println(cars_norm_ohnegpm);
		

		// TODO plotte die RMSE Werte
		float[] mpg = cars.getColumn(6).toArray();
		
		Random.seed(7);
		Random.seed(7);
		FloatMatrix theta = FloatMatrix.rand(6, 1);
//		System.out.println(theta);
		
		FloatMatrix predict_one = calcPredict(cars_norm_ohnegpm, theta);
//		System.out.println(predict_one.transpose());
//		System.out.println(mpg_norm);
		
		double alpha = 0.01;
//		alpha, m, theta, iteration, mpg_predict, mpg_norm, norm, mpg)
		
		System.out.println(predict_one.subColumnVector(mpg_norm));
//		System.out.println(cars_norm.mul(predict_one.subColumnVector(mpg_norm)));
//				.sub(mpg_norm));
		
		plot(mpg); 
	}

	
	
	// ---------------------------------------------------------------------------------
	// ------------ Alle Änderungen ab hier geschehen auf eigene Gefahr ----------------
	// ---------------------------------------------------------------------------------
	
	/**
	 * Equivalent zu linspace in Octave
	 * 
	 * @param lower
	 * @param upper
	 * @param num
	 * @return
	 */
	private static FloatMatrix linspace(float lower, float upper, int num) {
        float[] data = new float[num];
        float step = Math.abs(lower-upper) / (num-1);
        for (int i = 0; i < num; i++)
            data[i] = lower + (step * i);
        data[0] = lower;
        data[data.length-1] = upper;
        return new FloatMatrix(data);
    }
	
	private static float[] dataY;
	
	/**
	 * Startet die eigentliche Applikation
	 * 
	 * @param gdppp
	 * @param lifespan
	 * @param xValues
	 * @param yValues
	 * @param args
	 */
	public static void plot(float[] yValues) {
		dataY = yValues;
		Application.launch(new String[0]);
	}
	
	/**
	 * Zeichnet das Diagram
	 */
	@SuppressWarnings("unchecked")
	@Override public void start(Stage stage) {

		stage.setTitle(title);
		
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel(xAxisLabel);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisLabel);
        
		final LineChart<Number, Number> sc = new LineChart<>(xAxis, yAxis);

		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
		series1.setName("Data");
		for (int i = 0; i < dataY.length; i++) {
			series1.getData().add(new XYChart.Data<Number, Number>(i, dataY[i]));
		}

		sc.setAnimated(false);
		sc.setCreateSymbols(true);

		sc.getData().addAll(series1);

		Scene scene = new Scene(sc, 500, 400);
		stage.setScene(scene);
		stage.show();
    }
	
	/**
	 * Normalisiert Werte
	 * @param column - column, which should get normalized
	 */
	public static FloatMatrix normalize(FloatMatrix matrix){
		FloatMatrix max = matrix.columnMaxs();
		FloatMatrix min = matrix.columnMins();
		return matrix.subRowVector(min.transpose()).divRowVector(max.sub(min));
	}
	
	/**
	 * Denormalisiert Werte
	 * @param column, die zu denormalisierte Spalte
	 * @param zielwerte, die Werte, mit denen denormalisiert wird
	 */
	public FloatMatrix deNormalize(FloatMatrix normMatrix, FloatMatrix zielwerte){
		FloatMatrix max = zielwerte.columnMaxs();
		FloatMatrix min = zielwerte.columnMins();
		return normMatrix.mulRowVector(max.subi(min)).addi(min);
	}
	
	/**
	 * calculates rmse for a column
	 * @param prediction , calculated prediction
	 * @param zielwerte, real values
	 */
	public float calcRMSE(FloatMatrix prediction, FloatMatrix zielwerte){
		float sum = 0;
		sum = prediction.subRowVector(zielwerte).mul(prediction.subRowVector(zielwerte)).sum();
		return (float) Math.sqrt(sum/zielwerte.length);
	}
	
	/**
	 * calculate prediction
	 */
	public static FloatMatrix calcPredict(FloatMatrix matrixNorm, FloatMatrix koeffs){
		return matrixNorm.mulRowVector(koeffs);
	}
	
	public static FloatMatrix[] regression(double alpha, int M, FloatMatrix theta, FloatMatrix prediction, FloatMatrix mpg_norm, FloatMatrix cars_norm, FloatMatrix mpg){
//		alpha, m, theta, iteration, mpg_predict, mpg_norm, norm, mpg)
		FloatMatrix[] result = new FloatMatrix[mpg_norm.length];
//		diff = prediction.sub(mpg_norm);
		diff = cars_norm.
				prediction.subColumnVector(mpg_norm);
		
//		 diff = [pre_predict .- mpg_norm];
//		    delta_theta = norm' * diff;
//		    delta_theta_strich = (alpha/m) * delta_theta;
//		    theta_new = theta .- delta_theta_strich';
//		    pre_predict_denorm = denorm(pre_predict, mpg);
//		    rmse_temp = sqrt(sum((pre_predict_denorm .- mpg).^2)/ length(mpg));
//		    rmse_temp_matrix(i+1,1) = rmse_temp;
//		    pre_predict = calculatePrediction(norm, theta_new, 0);
//		    theta = theta_new;
		return result;
	}
	
}
