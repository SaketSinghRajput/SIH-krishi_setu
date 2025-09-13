# KrishiSetu AI Advisory Engine

## Overview
This Django app (`ai_advisory`) provides AI-powered recommendations for crop suitability, fertilizer usage, and pesticide advisory based on soil sensor data.

## Features
- REST API endpoints for crop, fertilizer, and pesticide advice
- Integrates with soil sensor database (`soil_data` table)
- Uses pretrained ML model (`soil_crop_model.pkl`)
- Training script (`train_ai.py`) for model retraining

## API Endpoints
- `/api/ai/crop-advice/` (POST): Get best crop for soil data
- `/api/ai/fertilizer-advice/` (POST): Get fertilizer dosage
- `/api/ai/pesticide-advice/` (POST): Get pesticide/insecticide advice

## Setup
1. Create and activate virtual environment:
   ```powershell
   python -m venv venv; .\venv\Scripts\activate
   ```
2. Install dependencies:
   ```powershell
   pip install django djangorestframework scikit-learn pandas numpy joblib torch celery redis
   ```
3. Add `ai_advisory` to Django `INSTALLED_APPS` and include its URLs.

## Training
- Run `train_ai.py` to retrain model on new soil data.

## Deployment
- Serve API via Gunicorn + Nginx
- Use Celery + Redis for scheduled retraining (optional)
