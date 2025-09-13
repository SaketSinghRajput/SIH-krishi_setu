from django.urls import path
from .views import DeliveryListCreateView, VehicleListCreateView

urlpatterns = [
    path('deliveries/', DeliveryListCreateView.as_view(), name='delivery-list-create'),
    path('vehicles/', VehicleListCreateView.as_view(), name='vehicle-list-create'),
]
