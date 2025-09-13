from django.db import models
from users.models import Farmer

class Order(models.Model):
    farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE)
    crop = models.CharField(max_length=100)
    quantity = models.FloatField()
    price = models.FloatField()
    status = models.CharField(max_length=50, default='Pending')
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"{self.crop} ({self.quantity}kg) - {self.status}"
