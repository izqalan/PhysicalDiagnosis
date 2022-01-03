package com.PhysicalDiagnosis;

public class PredictionResult {
	private String prediction;
	private String description;
	private String imageUrl;

	public PredictionResult(String result) {
		switch (result) {
			case "A":
				this.prediction = "Athletic";
				this.description = "Looking good! your are blowing the competition out!";
				this.imageUrl = "https://images.pexels.com/photos/3764163/pexels-photo-3764163.jpeg";
				break;
			case "B":
				this.prediction = "Healthy and maintaining";
				this.description = "Daily warriors! Compare yourself only to who you were yesterday. Be your own competition.";
				this.imageUrl = "https://images.pexels.com/photos/163491/bike-mountain-mountain-biking-trail-163491.jpeg";
				break;
			case "C":
				this.prediction = "Sedentary";
				this.description = "That ain't it chief. It's time to hit the gym!";
				this.imageUrl = "https://images.pexels.com/photos/4498294/pexels-photo-4498294.jpeg";
				break;
			case "D":
				this.prediction = "Poor fitness";
				this.description = "There is no better time than now to start living healthy.";
				this.imageUrl = "https://images.pexels.com/photos/6551415/pexels-photo-6551415.jpeg";
				break;
		}
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
