
from django.contrib.auth.models import AbstractUser
from django.db import models

class Farmer(AbstractUser):
	mobile = models.CharField(max_length=15, unique=True)
	aadhaar = models.CharField(max_length=12, blank=True, null=True)
	kyc_document = models.FileField(upload_to='kyc_docs/', blank=True, null=True)
	role = models.CharField(max_length=20, choices=[('farmer', 'Farmer'), ('buyer', 'Buyer'), ('govt', 'Govt')], default='farmer')
	land_area = models.FloatField(blank=True, null=True)
	crops = models.TextField(blank=True, null=True)  # CSV or JSON
	history = models.TextField(blank=True, null=True)  # CSV or JSON

	def __str__(self):
		return f"{self.username} ({self.role})"

class Scheme(models.Model):
	name = models.CharField(max_length=100)
	description = models.TextField()
	eligibility_criteria = models.TextField()
	api_url = models.URLField(blank=True, null=True)

	def __str__(self):
		return self.name

class SchemeApplication(models.Model):
	farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE, related_name='scheme_applications')
	scheme = models.ForeignKey(Scheme, on_delete=models.CASCADE)
	status = models.CharField(max_length=50, default='Pending')
	applied_at = models.DateTimeField(auto_now_add=True)
	token = models.CharField(max_length=100, blank=True, null=True)

	def __str__(self):
		return f"{self.farmer.username} - {self.scheme.name} ({self.status})"
