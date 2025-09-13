from rest_framework import generics, permissions
from .models import SoilAlert
from .serializers import SoilAlertSerializer

class SoilAlertListCreateView(generics.ListCreateAPIView):
    queryset = SoilAlert.objects.all()
    serializer_class = SoilAlertSerializer
    permission_classes = [permissions.IsAuthenticated]

class SoilAlertDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = SoilAlert.objects.all()
    serializer_class = SoilAlertSerializer
    permission_classes = [permissions.IsAuthenticated]
