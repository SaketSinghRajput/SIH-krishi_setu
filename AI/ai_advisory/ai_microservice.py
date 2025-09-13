from fastapi import FastAPI, Request
from pydantic import BaseModel
from typing import Optional

app = FastAPI()

class CropInput(BaseModel):
    soil: str
    climate: str

class FertilizerInput(BaseModel):
    soil: str
    climate: str
    crop: str

@app.post("/predict_crop")
async def predict_crop(data: CropInput):
    # Dummy logic, replace with real model
    return {"best_crop": "rice", "soil": data.soil, "climate": data.climate}

@app.post("/recommend_fertilizer")
async def recommend_fertilizer(data: FertilizerInput):
    # Dummy logic, replace with real model
    return {"recommended_fertilizer": "NPK", "soil": data.soil, "climate": data.climate, "crop": data.crop}
