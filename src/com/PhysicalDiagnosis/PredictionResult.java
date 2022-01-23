package com.PhysicalDiagnosis;

import java.util.ArrayList;

public class PredictionResult {
	private String prediction;
	private String description;
	private String imageUrl;
	private ArrayList<Suggestions> suggestion = new ArrayList<Suggestions>();

	public ArrayList<Suggestions> getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(ArrayList<Suggestions> suggestion) {
		this.suggestion = suggestion;
	}

	public PredictionResult(String result) {

		Suggestions s = new Suggestions();
		Suggestions s2 = new Suggestions();
		
		switch (result) {
			case "A":
				this.prediction = "Athletic";
				this.description = "Looking good! your are blowing the competition out!";
				this.imageUrl = "https://images.pexels.com/photos/3764163/pexels-photo-3764163.jpeg";
				s.addSuggestion("Heart Risks Associated With Extreme Exercise", "Chronic extreme exercise training and competing in endurance events can lead to heart damage and rhythm disorders. People with genetic risk factors are especially vulnerable.", "https://health.clevelandclinic.org/can-too-much-extreme-exercise-damage-your-heart/");
				suggestion.add(s);
				break;
			case "B":
				this.prediction = "Healthy and maintaining";
				this.description = "Daily warriors! Compare yourself only to who you were yesterday. Be your own competition.";
				this.imageUrl = "https://images.pexels.com/photos/163491/bike-mountain-mountain-biking-trail-163491.jpeg";
				s.addSuggestion("10K Training : Intermediate", "individuals who want to improve their performances. What defines an Intermediate runner? You should be running five to six times a week, averaging 15-25 miles weekly training. ", "https://www.halhigdon.com/training-programs/10k-training/intermediate-10k/");
				suggestion.add(s);
				
				break;
			case "C":
				this.prediction = "Sedentary";
				this.description = "That ain't it chief. It's time to hit the gym!";
				this.imageUrl = "https://images.pexels.com/photos/4498294/pexels-photo-4498294.jpeg";
				s.addSuggestion("Beginner's Guide to Mobility & Stretching", "When you’re doing mobility work you’re actually making the muscle more pliable, allowing it to withstand more strain", "https://www.menshealth.com/uk/fitness/a755395/the-beginners-guide-to-mobility-stretching/");
				suggestion.add(s);
				
				s2.addSuggestion("Healthy Eating", "Healthy eating. It's something everyone knows they should do, but few of us do as consistently as we would like. The purpose of this guide is to share practical strategies for how to eat healthy and break down the science of why we often fail to do so.", "https://jamesclear.com/eat-healthy");
				
				suggestion.add(s2);
				break;
			case "D":
				this.prediction = "Poor fitness";
				this.description = "There is no better time than now to start living healthy.";
				this.imageUrl = "https://images.pexels.com/photos/6551415/pexels-photo-6551415.jpeg";
				
				s.addSuggestion("Couch To 5K", "C25K, is a fantastic program that's been designed to get just about anyone from the couch to running 5 kilometers or 30 minutes in just 9 weeks. Tailored for first time runners.", "http://www.c25k.com/");
				suggestion.add(s);
				
				s2.addSuggestion("Healthy Eating", "Healthy eating. It's something everyone knows they should do, but few of us do as consistently as we would like. The purpose of this guide is to share practical strategies for how to eat healthy and break down the science of why we often fail to do so.", "https://jamesclear.com/eat-healthy");
				
				suggestion.add(s2);
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
