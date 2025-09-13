# Django models for AI advisory app
from django.db import models

class SoilData(models.Model):
    ph = models.FloatField()
    nitrogen = models.FloatField()
    phosphorus = models.FloatField()
    potassium = models.FloatField()
    moisture = models.FloatField()
    ec = models.FloatField()
    temperature = models.FloatField()
    humidity = models.FloatField()
    timestamp = models.DateTimeField(auto_now_add=True)

    class Meta:
        db_table = 'soil_data'
