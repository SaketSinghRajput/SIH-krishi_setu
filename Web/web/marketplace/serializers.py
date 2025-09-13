from rest_framework import serializers
from .models import Crop, Transaction, Subsidy

class CropSerializer(serializers.ModelSerializer):
    class Meta:
        model = Crop
        fields = '__all__'

class TransactionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Transaction
        fields = '__all__'

class SubsidySerializer(serializers.ModelSerializer):
    class Meta:
        model = Subsidy
        fields = '__all__'
