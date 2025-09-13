from rest_framework import serializers

class SoilDataSerializer(serializers.Serializer):
    ph = serializers.FloatField()
    nitrogen = serializers.FloatField()
    phosphorus = serializers.FloatField()
    potassium = serializers.FloatField()
    moisture = serializers.FloatField()
    ec = serializers.FloatField()
    temperature = serializers.FloatField()
    humidity = serializers.FloatField()
