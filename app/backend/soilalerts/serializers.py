from rest_framework import serializers
from .models import SoilAlert

class SoilAlertSerializer(serializers.ModelSerializer):
    class Meta:
        model = SoilAlert
        fields = '__all__'
