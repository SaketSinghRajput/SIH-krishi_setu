from django.db import models

class Loan(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='loans')
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    purpose = models.CharField(max_length=255)
    status = models.CharField(max_length=20, default='pending')
    applied_at = models.DateTimeField(auto_now_add=True)

class Insurance(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='insurances')
    policy_number = models.CharField(max_length=50)
    coverage_amount = models.DecimalField(max_digits=10, decimal_places=2)
    status = models.CharField(max_length=20, default='active')
    start_date = models.DateField()
    end_date = models.DateField()
