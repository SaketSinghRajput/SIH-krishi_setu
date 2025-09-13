# Django views for AI advisory endpoints
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
import joblib
import os
from .models import SoilData
from .serializers import SoilDataSerializer
import numpy as np
import pandas as pd

MODEL_PATH = os.path.join(os.path.dirname(__file__), 'soil_crop_model.pkl')

class CropAdviceView(APIView):
    def post(self, request):
        serializer = SoilDataSerializer(data=request.data)
        if serializer.is_valid():
            features = [
                serializer.validated_data['ph'],
                serializer.validated_data['nitrogen'],
                serializer.validated_data['phosphorus'],
                serializer.validated_data['potassium'],
                serializer.validated_data['moisture'],
                serializer.validated_data['ec'],
                serializer.validated_data['temperature'],
                serializer.validated_data['humidity']
            ]
            # Normalize features (example: min-max scaling, replace with actual scaler if available)
            features = np.array(features).reshape(1, -1)
            try:
                model = joblib.load(MODEL_PATH)
                crop = model.predict(features)[0]
                return Response({'recommended_crop': crop})
            except Exception as e:
                return Response({'error': str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class FertilizerAdviceView(APIView):
    def post(self, request):
        serializer = SoilDataSerializer(data=request.data)
        if serializer.is_valid():
            # Example logic: recommend NPK based on soil test
            n = serializer.validated_data['nitrogen']
            p = serializer.validated_data['phosphorus']
            k = serializer.validated_data['potassium']
            # Simple rule-based adjustment (replace with ML logic if available)
            dosage = {
                'N': max(80 - n, 20),
                'P': max(40 - p, 10),
                'K': max(20 - k, 5)
            }
            return Response({'fertilizer_dosage': f"{dosage['N']}kg N, {dosage['P']}kg P, {dosage['K']}kg K"})
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class PesticideAdviceView(APIView):
    def post(self, request):
        crop = request.data.get('crop', None)
        # Example: Use crop and soil moisture to suggest pesticide
        moisture = request.data.get('moisture', None)
        if crop and moisture is not None:
            advice = 'Neem oil spray'
            if crop.lower() == 'rice' and float(moisture) > 60:
                advice = 'Copper oxychloride for fungal risk'
            elif crop.lower() == 'wheat':
                advice = 'Imidacloprid for aphids'
            return Response({'pesticide_advice': advice})
        else:
            return Response({'error': 'Missing crop or moisture'}, status=status.HTTP_400_BAD_REQUEST)
