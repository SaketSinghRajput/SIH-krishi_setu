
from rest_framework import generics, permissions
from .models import Farmer, Scheme, SchemeApplication
from .serializers import FarmerSerializer, SchemeSerializer, SchemeApplicationSerializer


class FarmerListCreateView(generics.ListCreateAPIView):
	queryset = Farmer.objects.all()
	serializer_class = FarmerSerializer

class SchemeListCreateView(generics.ListCreateAPIView):
	queryset = Scheme.objects.all()
	serializer_class = SchemeSerializer

class SchemeApplicationListCreateView(generics.ListCreateAPIView):
	queryset = SchemeApplication.objects.all()
	serializer_class = SchemeApplicationSerializer
	permission_classes = [permissions.IsAuthenticated]

class FarmerDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Farmer.objects.all()
	serializer_class = FarmerSerializer
	permission_classes = [permissions.IsAuthenticated]
