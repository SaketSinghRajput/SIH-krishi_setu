
from django.db import models
from users.models import Farmer

class Chemical(models.Model):
	CHEMICAL_TYPE_CHOICES = [
		('fertilizer', 'Fertilizer'),
		('pesticide', 'Pesticide'),
	]
	name = models.CharField(max_length=100)
	chemical_type = models.CharField(max_length=20, choices=CHEMICAL_TYPE_CHOICES)
	dosage = models.CharField(max_length=100)
	safe_usage = models.TextField()

	def __str__(self):
		return f"{self.name} ({self.chemical_type})"

class DosageReminder(models.Model):
	farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE, related_name='reminders')
	chemical = models.ForeignKey(Chemical, on_delete=models.CASCADE)
	reminder_date = models.DateField()
	notes = models.TextField(blank=True, null=True)

	def __str__(self):
		return f"Reminder for {self.chemical.name} on {self.reminder_date}"
