from django.urls import path
from .views import UsageStatListCreateView, UsageStatDetailView, SystemMetricListCreateView, SystemMetricDetailView

urlpatterns = [
    path('usage-stats/', UsageStatListCreateView.as_view(), name='usagestat-list-create'),
    path('usage-stats/<int:pk>/', UsageStatDetailView.as_view(), name='usagestat-detail'),
    path('system-metrics/', SystemMetricListCreateView.as_view(), name='systemmetric-list-create'),
    path('system-metrics/<int:pk>/', SystemMetricDetailView.as_view(), name='systemmetric-detail'),
]
