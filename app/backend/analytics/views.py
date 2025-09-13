from rest_framework import generics, permissions
from .models import UsageStat, SystemMetric
from .serializers import UsageStatSerializer, SystemMetricSerializer

class UsageStatListCreateView(generics.ListCreateAPIView):
    queryset = UsageStat.objects.all()
    serializer_class = UsageStatSerializer
    permission_classes = [permissions.IsAuthenticated]

class UsageStatDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = UsageStat.objects.all()
    serializer_class = UsageStatSerializer
    permission_classes = [permissions.IsAuthenticated]

class SystemMetricListCreateView(generics.ListCreateAPIView):
    queryset = SystemMetric.objects.all()
    serializer_class = SystemMetricSerializer
    permission_classes = [permissions.IsAuthenticated]

class SystemMetricDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = SystemMetric.objects.all()
    serializer_class = SystemMetricSerializer
    permission_classes = [permissions.IsAuthenticated]
