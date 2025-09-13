from django.urls import path
from .views import AdviceListCreateView, AdviceDetailView

urlpatterns = [
    path('advice/', AdviceListCreateView.as_view(), name='advice-list-create'),
    path('advice/<int:pk>/', AdviceDetailView.as_view(), name='advice-detail'),
]
