from django.urls import path
from .views import SoilAlertListCreateView, SoilAlertDetailView

urlpatterns = [
    path('', SoilAlertListCreateView.as_view(), name='soilalert-list-create'),
    path('<int:pk>/', SoilAlertDetailView.as_view(), name='soilalert-detail'),
]
