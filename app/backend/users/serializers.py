from rest_framework import serializers
from .models import Farmer


class FarmerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Farmer
        fields = ['id', 'username', 'mobile', 'aadhaar', 'kyc_document', 'role', 'land_area', 'crops', 'history']

from .models import Scheme, SchemeApplication

class SchemeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Scheme
        fields = '__all__'

class SchemeApplicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = SchemeApplication
        fields = '__all__'
