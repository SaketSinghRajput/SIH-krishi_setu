from django.db import models

class Delivery(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='deliveries')
    product = models.CharField(max_length=100)
    pickup_location = models.CharField(max_length=255)
    drop_location = models.CharField(max_length=255)
    status = models.CharField(max_length=20, default='pending')
    scheduled_date = models.DateTimeField()
    created_at = models.DateTimeField(auto_now_add=True)

class Vehicle(models.Model):
    vehicle_number = models.CharField(max_length=20)
    type = models.CharField(max_length=50)
    capacity = models.PositiveIntegerField()
    available = models.BooleanField(default=True)
