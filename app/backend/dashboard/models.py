
from django.db import models
from users.models import Farmer

class SoilData(models.Model):
	farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE, related_name='soil_data')
	ph = models.FloatField()
	n = models.IntegerField()
	p = models.IntegerField()
	k = models.IntegerField()
	moisture = models.FloatField()
	ec = models.FloatField()
	status = models.CharField(max_length=50)
	created_at = models.DateTimeField(auto_now_add=True)

	def __str__(self):
		return f"SoilData for {self.farmer.username} at {self.created_at}"

class WeatherAlert(models.Model):
	farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE, related_name='weather_alerts')
	location = models.CharField(max_length=100)
	alert_type = models.CharField(max_length=50)
	message = models.TextField()
	alert_date = models.DateTimeField()
	created_at = models.DateTimeField(auto_now_add=True)

	def __str__(self):
		return f"WeatherAlert for {self.location} ({self.alert_type})"
