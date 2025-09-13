import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC
from xgboost import XGBClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, confusion_matrix, classification_report
import joblib

# Load dataset
df = pd.read_csv('Crop and fertilizer dataset.csv')

# Select relevant columns and rename for consistency
columns = {
    'Nitrogen': 'N',
    'Phosphorus': 'P',
    'Potassium': 'K',
    'pH': 'pH',
    'Rainfall': 'Rainfall',
    'Temperature': 'Temperature',
    'Crop': 'Crop',
    'Fertilizer': 'Fertilizer'
}
df = df[list(columns.keys())]
df.rename(columns=columns, inplace=True)

# Handle missing values
for col in ['N', 'P', 'K', 'pH', 'Rainfall', 'Temperature']:
    df[col] = df[col].fillna(df[col].mean())
df['Crop'] = df['Crop'].fillna(df['Crop'].mode()[0])
df['Fertilizer'] = df['Fertilizer'].fillna(df['Fertilizer'].mode()[0])

# Encode categorical variables
le_crop = LabelEncoder()
df['Crop_enc'] = le_crop.fit_transform(df['Crop'])
le_fert = LabelEncoder()
df['Fertilizer_enc'] = le_fert.fit_transform(df['Fertilizer'])

# Normalize numerical features
scaler = StandardScaler()
numeric_features = ['N', 'P', 'K', 'pH', 'Rainfall', 'Temperature']
df[numeric_features] = scaler.fit_transform(df[numeric_features])

# Feature selection
X = df[numeric_features + ['Crop_enc']]
y = df['Fertilizer_enc']

# Split data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train models
models = {
    'RandomForest': RandomForestClassifier(random_state=42),
    'XGBoost': XGBClassifier(random_state=42, use_label_encoder=False, eval_metric='mlogloss'),
    'SVM': SVC(probability=True, random_state=42)
}
results = {}
for name, model in models.items():
    model.fit(X_train, y_train)
    y_pred = model.predict(X_test)
    results[name] = {
        'accuracy': accuracy_score(y_test, y_pred),
        'precision': precision_score(y_test, y_pred, average='weighted'),
        'recall': recall_score(y_test, y_pred, average='weighted'),
        'f1': f1_score(y_test, y_pred, average='weighted'),
        'confusion_matrix': confusion_matrix(y_test, y_pred),
        'classification_report': classification_report(y_test, y_pred)
    }
    print(f"\n{name} Results:")
    print(f"Accuracy: {results[name]['accuracy']:.4f}")
    print(f"Precision: {results[name]['precision']:.4f}")
    print(f"Recall: {results[name]['recall']:.4f}")
    print(f"F1 Score: {results[name]['f1']:.4f}")
    print("Confusion Matrix:")
    print(results[name]['confusion_matrix'])
    print("Classification Report:")
    print(results[name]['classification_report'])
    # Cross-validation
    cv_scores = cross_val_score(model, X, y, cv=5, scoring='accuracy')
    print(f"Cross-validation accuracy: {cv_scores.mean():.4f}")

# Save best model (RandomForest as example)
best_model = models['RandomForest']
joblib.dump(best_model, 'fertilizer_best_model.pkl')
joblib.dump(le_crop, 'crop_encoder.pkl')
joblib.dump(le_fert, 'fertilizer_encoder.pkl')
joblib.dump(scaler, 'scaler.pkl')
print("Best model and encoders saved.")

# Prediction function
def recommend_fertilizer(N, P, K, pH, rainfall, temperature, crop):
    # Load encoders and model
    model = joblib.load('fertilizer_best_model.pkl')
    le_crop = joblib.load('crop_encoder.pkl')
    le_fert = joblib.load('fertilizer_encoder.pkl')
    scaler = joblib.load('scaler.pkl')
    # Prepare input
    crop_enc = le_crop.transform([crop])[0] if crop in le_crop.classes_ else 0
    X_input = np.array([[N, P, K, pH, rainfall, temperature]])
    X_input_scaled = scaler.transform(X_input)
    X_input_final = np.append(X_input_scaled, [[crop_enc]], axis=1)
    pred_enc = model.predict(X_input_final.reshape(1, -1))[0]
    return le_fert.inverse_transform([pred_enc])[0]


# Interactive user input for prediction
if __name__ == "__main__":
    print("Available crops:")
    for crop in le_crop.classes_:
        print('-', crop)
    N = float(input('Enter Nitrogen value: '))
    P = float(input('Enter Phosphorus value: '))
    K = float(input('Enter Potassium value: '))
    pH = float(input('Enter pH value: '))
    Rainfall = float(input('Enter Rainfall value: '))
    Temperature = float(input('Enter Temperature value: '))
    crop = input('Enter Crop name (as shown above): ')
    fertilizer = recommend_fertilizer(N, P, K, pH, Rainfall, Temperature, crop)
    print(f'Predicted Fertilizer: {fertilizer}')
