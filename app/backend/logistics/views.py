from rest_framework import generics
from .models import Delivery, Vehicle
from .serializers import DeliverySerializer, VehicleSerializer

class DeliveryListCreateView(generics.ListCreateAPIView):
    queryset = Delivery.objects.all()
    serializer_class = DeliverySerializer

class VehicleListCreateView(generics.ListCreateAPIView):
    queryset = Vehicle.objects.all()
    serializer_class = VehicleSerializer
