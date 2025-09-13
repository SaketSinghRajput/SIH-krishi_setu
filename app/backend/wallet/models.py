from django.db import models
from users.models import Farmer

class Wallet(models.Model):
    farmer = models.OneToOneField(Farmer, on_delete=models.CASCADE)
    balance = models.FloatField(default=0.0)

    def __str__(self):
        return f"Wallet of {self.farmer.username}"
