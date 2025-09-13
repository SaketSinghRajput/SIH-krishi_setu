from django.urls import path
from .views import SMSNotificationListCreateView, SMSNotificationDetailView, VoiceCallListCreateView, VoiceCallDetailView

urlpatterns = [
    path('sms/', SMSNotificationListCreateView.as_view(), name='sms-list-create'),
    path('sms/<int:pk>/', SMSNotificationDetailView.as_view(), name='sms-detail'),
    path('voice/', VoiceCallListCreateView.as_view(), name='voice-list-create'),
    path('voice/<int:pk>/', VoiceCallDetailView.as_view(), name='voice-detail'),
]
