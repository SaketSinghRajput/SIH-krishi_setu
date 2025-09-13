from django.db import models

class Farmer(models.Model):
	name = models.CharField(max_length=100)
	phone = models.CharField(max_length=15)
	soil_data = models.TextField()
	wallet_id = models.CharField(max_length=100)

	def __str__(self):
		return self.name

class Buyer(models.Model):
	name = models.CharField(max_length=100)
	company = models.CharField(max_length=100)
	wallet_id = models.CharField(max_length=100)

	def __str__(self):
		return self.name
