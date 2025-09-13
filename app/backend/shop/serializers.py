from rest_framework import serializers
from .models import Chemical, DosageReminder

class ChemicalSerializer(serializers.ModelSerializer):
    class Meta:
        model = Chemical
        fields = '__all__'

class DosageReminderSerializer(serializers.ModelSerializer):
    class Meta:
        model = DosageReminder
        fields = '__all__'
