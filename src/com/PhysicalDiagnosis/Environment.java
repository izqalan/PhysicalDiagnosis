package com.PhysicalDiagnosis;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class Environment extends HttpServlet {
	
	private String modelPath, trainPath;
	
	

	public Environment() {
		String modelRelativeWebPath = "/datasets/NomToBinaryModel.model";
		String trainRelativeWebPath = "/datasets/bodyPerformance.arff";
		
		ServletContext servletContext = (ServletContext) FacesContext
			    .getCurrentInstance().getExternalContext().getContext();

		this.modelPath = servletContext.getRealPath(modelRelativeWebPath);
		this.trainPath = servletContext.getRealPath(trainRelativeWebPath);
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
