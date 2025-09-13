
# KrishiSetu Crop Prediction Model Training Script
# Trains a RandomForestClassifier on Crop_recommendation.csv and allows manual crop prediction

import os
import pickle
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

def main():
	# Path to dataset
	data_path = os.path.join(os.path.dirname(os.path.dirname(__file__)), 'Crop_recommendation.csv')
	if not os.path.exists(data_path):
		print(f"Dataset not found: {data_path}")
		return
	# Load dataset
	data = pd.read_csv(data_path)
	required_cols = {'N', 'P', 'K', 'temperature', 'humidity', 'ph', 'rainfall', 'label'}
	if not required_cols.issubset(data.columns):
		print(f"Dataset must contain columns: {required_cols}")
		return
	# Features and target
	X = data[['N', 'P', 'K', 'temperature', 'humidity', 'ph', 'rainfall']]
	y = data['label']
	# Split data
	X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
	# Train model
	model = RandomForestClassifier(n_estimators=100, random_state=42)
	model.fit(X_train, y_train)
	# Evaluate
	y_pred = model.predict(X_test)
	print('Accuracy:', accuracy_score(y_test, y_pred))
	# Save model
	model_path = os.path.join(os.path.dirname(__file__), 'soil_crop_model.pkl')
	with open(model_path, 'wb') as f:
		pickle.dump(model, f)
	print(f"Model saved to {model_path}")

	# Manual prediction
	print("\nEnter soil and climate values to predict crop:")
	try:
		N = float(input("Nitrogen (N): "))
		P = float(input("Phosphorus (P): "))
		K = float(input("Potassium (K): "))
		temperature = float(input("Temperature (°C): "))
		humidity = float(input("Humidity (%): "))
		ph = float(input("pH: "))
		rainfall = float(input("Rainfall (mm): "))
		user_features = [[N, P, K, temperature, humidity, ph, rainfall]]
		predicted_crop = model.predict(user_features)
		print(f"\nPredicted crop: {predicted_crop[0]}")
	except Exception as e:
		print(f"Error in input: {e}")

if __name__ == "__main__":
	main()
