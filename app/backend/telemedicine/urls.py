from django.urls import path
from .views import DoctorListCreateView, AppointmentListCreateView, PrescriptionListCreateView

urlpatterns = [
    path('doctors/', DoctorListCreateView.as_view(), name='doctor-list-create'),
    path('appointments/', AppointmentListCreateView.as_view(), name='appointment-list-create'),
    path('prescriptions/', PrescriptionListCreateView.as_view(), name='prescription-list-create'),
]
