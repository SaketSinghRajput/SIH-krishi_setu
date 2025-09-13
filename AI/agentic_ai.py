import joblib
import numpy as np
import requests

# --- Load Models and Encoders ---
CROP_MODEL_PATH = r'f:/collage/SIH/AI/Crop prediction model/ai_advisory/soil_crop_model.pkl'
FERTILIZER_MODEL_PATH = r'f:/collage/SIH/AI/Fertilizer prediction model/fertilizer_best_model.pkl'
CROP_ENCODER_PATH = r'f:/collage/SIH/AI/Fertilizer prediction model/crop_encoder.pkl'
FERTILIZER_ENCODER_PATH = r'f:/collage/SIH/AI/Fertilizer prediction model/fertilizer_encoder.pkl'
SCALER_PATH = r'f:/collage/SIH/AI/Fertilizer prediction model/scaler.pkl'

# --- Gemini API Config ---
GEMINI_API_URL = 'https://generativelanguage.googleapis.com/v1/models/gemini-2.5-pro:generateContent'
GEMINI_API_KEY = 'AIzaSyCQjMkWLLY3_bKsfcMoNTEC4EqjDjUu_r8'  # <-- Inserted user key

def predict_crop(inputs):
    model = joblib.load(CROP_MODEL_PATH)
    features = np.array([
        inputs['ph'], inputs['nitrogen'], inputs['phosphorus'], inputs['potassium'],
        inputs['moisture'], inputs['temperature'], inputs['humidity']
    ]).reshape(1, -1)
    crop = model.predict(features)[0]
    return crop

def recommend_fertilizer(inputs, crop):
    model = joblib.load(FERTILIZER_MODEL_PATH)
    le_crop = joblib.load(CROP_ENCODER_PATH)
    le_fert = joblib.load(FERTILIZER_ENCODER_PATH)
    scaler = joblib.load(SCALER_PATH)
    # Check if crop is in encoder
    if crop in le_crop.classes_:
        crop_enc = le_crop.transform([crop])[0]
        X_input = np.array([
            inputs['nitrogen'], inputs['phosphorus'], inputs['potassium'],
            inputs['ph'], inputs['rainfall'], inputs['temperature']
        ]).reshape(1, -1)
        X_input_scaled = scaler.transform(X_input)
        X_input_final = np.append(X_input_scaled, [[crop_enc]], axis=1)
        pred_enc = model.predict(X_input_final.reshape(1, -1))[0]
        fertilizer = le_fert.inverse_transform([pred_enc])[0]
        return fertilizer
    else:
        return None

def query_gemini(prompt):
    headers = {'Content-Type': 'application/json'}
    data = {
        'contents': [{'parts': [{'text': prompt}]}]
    }
    response = requests.post(
        f'{GEMINI_API_URL}?key={GEMINI_API_KEY}',
        json=data, headers=headers
    )
    if response.status_code == 200:
        return response.json()['candidates'][0]['content']['parts'][0]['text']
    else:
        return f'Gemini API error: {response.text}'

def main():
    # --- Collect Inputs ---
    print('Enter the following values:')
    inputs = {}
    inputs['nitrogen'] = float(input('Nitrogen (N): '))
    inputs['phosphorus'] = float(input('Phosphorus (P): '))
    inputs['potassium'] = float(input('Potassium (K): '))
    inputs['ph'] = float(input('pH: '))
    inputs['moisture'] = float(input('Moisture: '))
    inputs['humidity'] = float(input('Humidity: '))
    inputs['rainfall'] = float(input('Rainfall: '))
    inputs['temperature'] = float(input('Temperature: '))

    crop = predict_crop(inputs)
    print(f'Predicted Crop: {crop}')

    fertilizer = recommend_fertilizer(inputs, crop)
    if fertilizer:
        print(f'Recommended Fertilizer: {fertilizer}')
    else:
        print('Crop not found in fertilizer model. Using Gemini for fertilizer advice...')
    prompt = f"Suggest fertilizer for crop '{crop}' with N={inputs['nitrogen']}, P={inputs['phosphorus']}, K={inputs['potassium']}, pH={inputs['ph']}, moisture={inputs['moisture']}, humidity={inputs['humidity']}, rainfall={inputs['rainfall']}, temperature={inputs['temperature']}."
    fertilizer = query_gemini(prompt)
    print(f'Gemini Fertilizer Advice: {fertilizer}')

    # Get government schemes and step-by-step fertilizer application
    prompt = f"List government schemes and step-by-step process to apply fertilizer for crop '{crop}' with N={inputs['nitrogen']}, P={inputs['phosphorus']}, K={inputs['potassium']}, pH={inputs['ph']}, moisture={inputs['moisture']}, humidity={inputs['humidity']}, rainfall={inputs['rainfall']}, temperature={inputs['temperature']}. Fertilizer: {fertilizer}."
    schemes = query_gemini(prompt)
    print('\nGovernment Schemes and Application Steps:')
    print(schemes)

if __name__ == '__main__':
    main()
