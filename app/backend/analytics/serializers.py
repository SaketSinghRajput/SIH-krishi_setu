from rest_framework import serializers
from .models import UsageStat, SystemMetric

class UsageStatSerializer(serializers.ModelSerializer):
    class Meta:
        model = UsageStat
        fields = '__all__'

class SystemMetricSerializer(serializers.ModelSerializer):
    class Meta:
        model = SystemMetric
        fields = '__all__'
