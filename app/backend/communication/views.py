from rest_framework import generics, permissions
from .models import SMSNotification, VoiceCall
from .serializers import SMSNotificationSerializer, VoiceCallSerializer

class SMSNotificationListCreateView(generics.ListCreateAPIView):
    queryset = SMSNotification.objects.all()
    serializer_class = SMSNotificationSerializer
    permission_classes = [permissions.IsAuthenticated]

class SMSNotificationDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = SMSNotification.objects.all()
    serializer_class = SMSNotificationSerializer
    permission_classes = [permissions.IsAuthenticated]

class VoiceCallListCreateView(generics.ListCreateAPIView):
    queryset = VoiceCall.objects.all()
    serializer_class = VoiceCallSerializer
    permission_classes = [permissions.IsAuthenticated]

class VoiceCallDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = VoiceCall.objects.all()
    serializer_class = VoiceCallSerializer
    permission_classes = [permissions.IsAuthenticated]
