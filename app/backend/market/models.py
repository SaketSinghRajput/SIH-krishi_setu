
from django.db import models

class Product(models.Model):
	name = models.CharField(max_length=100)
	description = models.TextField(blank=True)
	price = models.DecimalField(max_digits=10, decimal_places=2)
	quantity = models.PositiveIntegerField()
	seller = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='products')
	created_at = models.DateTimeField(auto_now_add=True)

class Order(models.Model):
	product = models.ForeignKey(Product, on_delete=models.CASCADE, related_name='orders')
	buyer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='orders')
	quantity = models.PositiveIntegerField()
	total_price = models.DecimalField(max_digits=10, decimal_places=2)
	status = models.CharField(max_length=20, default='pending')
	ordered_at = models.DateTimeField(auto_now_add=True)

class Transaction(models.Model):
	order = models.OneToOneField(Order, on_delete=models.CASCADE, related_name='transaction')
	payment_id = models.CharField(max_length=100)
	amount = models.DecimalField(max_digits=10, decimal_places=2)
	status = models.CharField(max_length=20)
	transaction_date = models.DateTimeField(auto_now_add=True)
