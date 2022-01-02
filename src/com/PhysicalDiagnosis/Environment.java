package com.PhysicalDiagnosis;

public class Environment {
	
	private String modelPath, trainPath;
	
	

	public Environment() {
		this.modelPath = "C:\\Users\\Izqalan\\eclipse-workspace\\PhysicalDiagnosis\\datasets\\J48Model.model";
		this.trainPath = "C:\\Users\\Izqalan\\eclipse-workspace\\PhysicalDiagnosis\\datasets\\bodyPerformance.arff";
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public String getTrainPath() {
		return trainPath;
	}

	public void setTrainPath(String trainPath) {
		this.trainPath = trainPath;
	}	

}
