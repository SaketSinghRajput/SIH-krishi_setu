from django.db import models
from users.models import Farmer

class SoilAlert(models.Model):
    farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE)
    alert_type = models.CharField(max_length=100)
    message = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"{self.alert_type} for {self.farmer.username}"
