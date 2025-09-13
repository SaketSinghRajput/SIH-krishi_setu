import joblib
import numpy as np
import requests

# --- Load Models and Encoders ---
CROP_MODEL_PATH = r'f:/collage/SIH/ecosystem/SIH-krishi_setu/AI/Crop prediction model/ai_advisory/soil_crop_model.pkl'
import os
CROP_MODEL_PATH = os.path.join(os.path.dirname(__file__), 'CROP PREDICTION MODEL', 'ai_advisory', 'soil_crop_model.pkl')
FERTILIZER_MODEL_PATH = r'f:/collage/SIH/ecosystem/SIH-krishi_setu/AI/Fertilizer prediction model/fertilizer_best_model.pkl'
CROP_ENCODER_PATH = r'f:/collage/SIH/ecosystem/SIH-krishi_setu/AI/Fertilizer prediction model/crop_encoder.pkl'
FERTILIZER_ENCODER_PATH = r'f:/collage/SIH/ecosystem/SIH-krishi_setu/AI/Fertilizer prediction model/fertilizer_encoder.pkl'
SCALER_PATH = r'f:/collage/SIH/ecosystem/SIH-krishi_setu/AI/Fertilizer prediction model/scaler.pkl'

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
    try:
        response = requests.post(
            f'{GEMINI_API_URL}?key={GEMINI_API_KEY}',
            json=data, headers=headers, timeout=60
        )
        if response.status_code == 200:
            return response.json()['candidates'][0]['content']['parts'][0]['text']
        else:
            return f'Gemini API error: {response.text}'
    except requests.Timeout:
        return 'Gemini API request timed out.'
    except Exception as e:
        return f'Gemini API error: {e}'

def main():
    # --- Collect Inputs ---
    print('Enter the following values (press Enter for default):')
    def get_float(prompt, default):
        try:
            val = input(f"{prompt} [{default}]: ")
            return float(val) if val.strip() else default
        except Exception:
            print(f"Invalid input, using default {default}")
            return default
    inputs = {}
    inputs['nitrogen'] = get_float('Nitrogen (N)', 86)
    inputs['phosphorus'] = get_float('Phosphorus (P)', 85)
    inputs['potassium'] = get_float('Potassium (K)', 45)
    inputs['ph'] = get_float('pH', 6.5)
    inputs['moisture'] = get_float('Moisture', 20)
    inputs['humidity'] = get_float('Humidity', 60)
    inputs['rainfall'] = get_float('Rainfall', 100)
    inputs['temperature'] = get_float('Temperature', 25)
    inputs['calcium'] = get_float('Calcium (Ca)', 10)
    inputs['magnesium'] = get_float('Magnesium (Mg)', 5)
    inputs['sulfur'] = get_float('Sulfur (S)', 2)

    crop = predict_crop(inputs)
    print(f"Crop: {crop}")

    # Get cheapest government-backed organic fertilizer for the crop
    prompt = f"For crop '{crop}', suggest the cheapest organic fertilizer that is backed by the Indian government. Name only the fertilizer." 
    cheapest_fertilizer = query_gemini(prompt)
    print(f"Cheapest Govt-backed Organic Fertilizer: {cheapest_fertilizer}")

    # Get government scheme and subsidy for the fertilizer/crop
    prompt = f"For crop '{crop}' and fertilizer '{cheapest_fertilizer}', list the main government scheme and subsidy available for farmers in India. Give only the scheme name and subsidy details, no extra info."
    scheme_subsidy = query_gemini(prompt)
    print("Government Scheme & Subsidy:")
    print(scheme_subsidy)

    # Get concise step-by-step process
    prompt = f"Give a very concise, simple step-by-step process to apply organic fertilizer '{cheapest_fertilizer}' for crop '{crop}'. Only steps, no extra info."
    steps = query_gemini(prompt)
    print("Step-by-Step Process:")
    print(steps)
    import sys
    sys.exit(0)

if __name__ == '__main__':
    main()
