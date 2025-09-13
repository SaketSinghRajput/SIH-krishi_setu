
from rest_framework import generics, permissions
from .models import Advice
from .serializers import AdviceSerializer

class AdviceListCreateView(generics.ListCreateAPIView):
	queryset = Advice.objects.all()
	serializer_class = AdviceSerializer
	permission_classes = [permissions.IsAuthenticated]

class AdviceDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Advice.objects.all()
	serializer_class = AdviceSerializer
	permission_classes = [permissions.IsAuthenticated]
