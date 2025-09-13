from django.urls import path
from .views import ChemicalListCreateView, ChemicalDetailView, DosageReminderListCreateView, DosageReminderDetailView

urlpatterns = [
    path('chemicals/', ChemicalListCreateView.as_view(), name='chemical-list-create'),
    path('chemicals/<int:pk>/', ChemicalDetailView.as_view(), name='chemical-detail'),
    path('reminders/', DosageReminderListCreateView.as_view(), name='reminder-list-create'),
    path('reminders/<int:pk>/', DosageReminderDetailView.as_view(), name='reminder-detail'),
]
