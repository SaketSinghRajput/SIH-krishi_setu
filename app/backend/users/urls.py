from django.urls import path
from .views import FarmerListCreateView, FarmerDetailView, SchemeListCreateView, SchemeApplicationListCreateView

urlpatterns = [
    path('farmers/', FarmerListCreateView.as_view(), name='farmer-list-create'),
    path('farmers/<int:pk>/', FarmerDetailView.as_view(), name='farmer-detail'),
    path('schemes/', SchemeListCreateView.as_view(), name='scheme-list-create'),
    path('scheme-applications/', SchemeApplicationListCreateView.as_view(), name='scheme-application-list-create'),
]
