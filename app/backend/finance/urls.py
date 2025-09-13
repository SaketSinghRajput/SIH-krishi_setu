from django.urls import path
from .views import LoanListCreateView, InsuranceListCreateView, LoanDetailView, InsuranceDetailView

urlpatterns = [
    path('loans/', LoanListCreateView.as_view(), name='loan-list-create'),
    path('loans/<int:pk>/', LoanDetailView.as_view(), name='loan-detail'),
    path('insurances/', InsuranceListCreateView.as_view(), name='insurance-list-create'),
    path('insurances/<int:pk>/', InsuranceDetailView.as_view(), name='insurance-detail'),
]
