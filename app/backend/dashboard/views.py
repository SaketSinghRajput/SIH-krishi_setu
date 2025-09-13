from rest_framework import generics, permissions
from .models import SoilData, WeatherAlert
from .serializers import SoilDataSerializer, WeatherAlertSerializer

class SoilDataListCreateView(generics.ListCreateAPIView):
	queryset = SoilData.objects.all()
	serializer_class = SoilDataSerializer
	permission_classes = [permissions.IsAuthenticated]

class SoilDataDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = SoilData.objects.all()
	serializer_class = SoilDataSerializer
	permission_classes = [permissions.IsAuthenticated]

class WeatherAlertListCreateView(generics.ListCreateAPIView):
	queryset = WeatherAlert.objects.all()
	serializer_class = WeatherAlertSerializer
	permission_classes = [permissions.IsAuthenticated]

class WeatherAlertDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = WeatherAlert.objects.all()
	serializer_class = WeatherAlertSerializer
	permission_classes = [permissions.IsAuthenticated]
