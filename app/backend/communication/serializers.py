from rest_framework import serializers
from .models import SMSNotification, VoiceCall

class SMSNotificationSerializer(serializers.ModelSerializer):
    class Meta:
        model = SMSNotification
        fields = '__all__'

class VoiceCallSerializer(serializers.ModelSerializer):
    class Meta:
        model = VoiceCall
        fields = '__all__'
