from django.urls import path
from .views import SoilDataListCreateView, SoilDataDetailView, WeatherAlertListCreateView, WeatherAlertDetailView

urlpatterns = [
    path('soil/', SoilDataListCreateView.as_view(), name='soil-list-create'),
    path('soil/<int:pk>/', SoilDataDetailView.as_view(), name='soil-detail'),
    path('weather/', WeatherAlertListCreateView.as_view(), name='weather-list-create'),
    path('weather/<int:pk>/', WeatherAlertDetailView.as_view(), name='weather-detail'),
]
