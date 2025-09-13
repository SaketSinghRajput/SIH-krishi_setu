from django.db import models

class UsageStat(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='usage_stats')
    feature = models.CharField(max_length=100)
    count = models.PositiveIntegerField(default=0)
    last_used = models.DateTimeField(auto_now=True)

class SystemMetric(models.Model):
    metric_name = models.CharField(max_length=100)
    value = models.FloatField()
    recorded_at = models.DateTimeField(auto_now_add=True)
