from django.db import models

class Crop(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField(blank=True)
    price_msp = models.DecimalField(max_digits=10, decimal_places=2)
    available_quantity = models.PositiveIntegerField()
    farmer = models.CharField(max_length=100)
    created_at = models.DateTimeField(auto_now_add=True)

class Transaction(models.Model):
    crop = models.ForeignKey(Crop, on_delete=models.CASCADE)
    buyer = models.CharField(max_length=100)
    quantity = models.PositiveIntegerField()
    total_price = models.DecimalField(max_digits=12, decimal_places=2)
    transaction_hash = models.CharField(max_length=256, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)

class Subsidy(models.Model):
    farmer = models.CharField(max_length=100)
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    reason = models.TextField(blank=True)
    blockchain_tx_hash = models.CharField(max_length=256, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
