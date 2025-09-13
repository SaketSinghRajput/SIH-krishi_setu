from django.db import models

class SMSNotification(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='sms_notifications')
    message = models.TextField()
    sent_at = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=20, default='sent')

class VoiceCall(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='voice_calls')
    call_time = models.DateTimeField()
    duration = models.PositiveIntegerField(help_text='Duration in seconds')
    status = models.CharField(max_length=20, default='completed')
