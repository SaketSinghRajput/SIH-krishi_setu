from rest_framework import generics, permissions
from .models import Chemical, DosageReminder
from .serializers import ChemicalSerializer, DosageReminderSerializer

class ChemicalListCreateView(generics.ListCreateAPIView):
	queryset = Chemical.objects.all()
	serializer_class = ChemicalSerializer
	permission_classes = [permissions.IsAuthenticated]

class ChemicalDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Chemical.objects.all()
	serializer_class = ChemicalSerializer
	permission_classes = [permissions.IsAuthenticated]

class DosageReminderListCreateView(generics.ListCreateAPIView):
	queryset = DosageReminder.objects.all()
	serializer_class = DosageReminderSerializer
	permission_classes = [permissions.IsAuthenticated]

class DosageReminderDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = DosageReminder.objects.all()
	serializer_class = DosageReminderSerializer
	permission_classes = [permissions.IsAuthenticated]
