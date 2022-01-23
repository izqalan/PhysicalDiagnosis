package com.PhysicalDiagnosis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader.ArffReader;

@ManagedBean @SessionScoped
public class PhysicalFitnessBean {

	Environment env = new Environment();
	private String modelfile = env.getModelPath();
	private String trainFile = env.getTrainPath();


	private double height, weight, bodyFat, gripForce, sitBendForward;
	private int diastolic, systolic, sitUps, boardJump, age;
	private char gender;
	private String prediction;
	private ArrayList<Suggestions> suggestion = new ArrayList<Suggestions>(); 
	
	private PredictionResult predictionResult;
	
	public ArrayList<Suggestions> getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(ArrayList<Suggestions> suggestion) {
		this.suggestion = suggestion;
	}


	public PredictionResult getPredictionResult() {
		return predictionResult;
	}
	public void setPredictionResult(PredictionResult predictionResult) {
		this.predictionResult = predictionResult;
	}
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBodyFat() {
		return bodyFat;
	}
	public void setBodyFat(double bodyFat) {
		this.bodyFat = bodyFat;
	}
	public double getGripForce() {
		return gripForce;
	}
	public void setGripForce(double gripForce) {
		this.gripForce = gripForce;
	}
	public double getSitBendForward() {
		return sitBendForward;
	}
	public void setSitBendForward(double sitBendForward) {
		this.sitBendForward = sitBendForward;
	}
	public int getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}
	public int getSystolic() {
		return systolic;
	}
	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}
	public int getSitUps() {
		return sitUps;
	}
	public void setSitUps(int sitUps) {
		this.sitUps = sitUps;
	}
	public int getBoardJump() {
		return boardJump;
	}
	public void setBoardJump(int boardJump) {
		this.boardJump = boardJump;
	}

	static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
		return inputReader;
	}
	
	public static Evaluation classify(Classifier model, Instances trainingSet, Instances testingSet) throws Exception {
		//To evaluate the training dataset
		Evaluation evaluation = new Evaluation(trainingSet);
		//To create model of the classifiers using training dataset
		model.buildClassifier(trainingSet);
		//To evaluate the created model on the testing dataset
		evaluation.evaluateModel(model, testingSet);

		return evaluation;
	}
	
	public static double calculateAccuracy(FastVector predictions) {
		double correct = 0;
		//To calculate correct accuracy by comparing the predicted and actual values 
		for (int i = 0; i < predictions.size(); i++) {
			NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
			if (np.predicted() == np.actual()) {
				correct++;
			}
		}
 
		return 100 * correct / predictions.size();
	}
	
	public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
		Instances[][] split = new Instances[2][numberOfFolds];
 //To create data sampling for training and testing using split cross-validation based on specific number of fold 
		for (int i = 0; i < numberOfFolds; i++) {
			split[0][i] = data.trainCV(numberOfFolds, i);
			split[1][i] = data.testCV(numberOfFolds, i);
		}
 
		return split;
	}
	
	public void bodyPerformanceTest() {
		Instances data;
		BufferedReader datafile = readDataFile(trainFile);
		try {
			ArffReader arff = new ArffReader(datafile);
			data = arff.getData();
			
			data.setClassIndex(data.numAttributes() - 1);
			 
			// Do 10-split cross validation
			Instances[][] split = crossValidationSplit(data, 5);
	 
			// Separate split into training and testing arrays
			Instances[] trainingSplits = split[0];
			Instances[] testingSplits = split[1];
	 
			// Use a set of classifiers
			Classifier[] models = { 
					new J48(), // a decision tree
			};
	 
			// Run for each model
			for (int j = 0; j < models.length; j++) {
	 
				// Collect every group of predictions for current model in a FastVector
				FastVector predictions = new FastVector();
	 
				// For each training-testing split pair, train and test the classifier
				for (int i = 0; i < trainingSplits.length; i++) {
					Evaluation validation;
					try {
						validation = classify(models[j], trainingSplits[i], testingSplits[i]);
						predictions.appendElements(validation.predictions());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					// Uncomment to see the summary for each training-testing pair.
					System.out.println(models[j].toString());
				}
	 
				// Calculate overall accuracy of current classifier on all splits
				double accuracy = calculateAccuracy(predictions);
	 
				// Print current classifier's name and accuracy in a complicated,
				// but nice-looking way.
				System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
						+ String.format("%.2f%%", accuracy)
						+ "\n---------------------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String predict() {
		// get input
		final Attribute attributeAge = new Attribute("age");
		final Attribute attributeGenderF = new Attribute("gender=F");
		final Attribute attributeHeight = new Attribute("height_cm");
		final Attribute attributeWeight = new Attribute("weight_kg");
		final Attribute attributeBodyFat = new Attribute("body fat_%");
		final Attribute attributeDiastolic = new Attribute("diastolic");
		final Attribute attributeSystolic = new Attribute("systolic");
		final Attribute attributeGripForce = new Attribute("gripForce");
		final Attribute attributeSitAndBend = new Attribute("sit and bend forward_cm");
		final Attribute attributeSitUps = new Attribute("sit-ups counts");
		final Attribute attributeBoardJump = new Attribute("board jump_cm");
		final List<String> classes = new ArrayList<String>(){
			{
				add("A");
				add("B");
				add("C");
				add("D");
			}
		};
		
		// create an instance from input given
		ArrayList<Attribute> attributeList = new ArrayList<Attribute>() {
			{
				add(attributeAge);
				add(attributeGenderF);
				add(attributeHeight);
				add(attributeWeight);
				add(attributeBodyFat);
				add(attributeDiastolic);
				add(attributeSystolic);
				add(attributeGripForce);
				add(attributeSitAndBend);
				add(attributeSitUps);
				add(attributeBoardJump);
				Attribute attributeClass = new Attribute("@@class@@", classes);
				add(attributeClass);
			}
		};
		
		// create new dataset to predict
		Instances dataUnpredicted = new Instances("newInstance", attributeList, 1);
        dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1); 
        
        int genderCodeF = 0;
        if (getGender() == 77) {
        	genderCodeF = 0;
        } else if(getGender() == 70) {
        	genderCodeF = 1;
        }
        
        final int isFemale = genderCodeF;
        
		DenseInstance newInstanceBodyPerformance = new DenseInstance(dataUnpredicted.numAttributes()) {
			{
				setValue(attributeAge, getAge());
				setValue(attributeGenderF, isFemale);
				setValue(attributeHeight, getHeight());
				setValue(attributeWeight, getWeight());
				setValue(attributeBodyFat, getBodyFat());
				setValue(attributeDiastolic, getDiastolic());
				setValue(attributeSystolic, getSystolic());
				setValue(attributeGripForce, getGripForce());
				setValue(attributeSitAndBend, getSitBendForward());
				setValue(attributeSitUps, getSitUps());
				setValue(attributeBoardJump, getBoardJump());
			}
		};
		
		// predict instance using trained model
		DenseInstance newInstance = newInstanceBodyPerformance;
		newInstance.setDataset(dataUnpredicted);
		
		Classifier classifier = null;
		try {
			classifier = (Classifier) SerializationHelper.read(modelfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (classifier == null) return "";
		
		// predict input data
		try {
			double result = classifier.classifyInstance(newInstance);
			System.out.println(newInstance);
			this.setPrediction(classes.get(new Double(result).intValue()));
			predictionResult = new PredictionResult(classes.get(new Double(result).intValue()));
			System.out.println("Index of predicted class label: " + result + ", which corresponds to class: " + classes.get(new Double(result).intValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "output";
	}

}
