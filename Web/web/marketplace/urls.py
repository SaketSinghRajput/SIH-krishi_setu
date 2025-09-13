from rest_framework import routers
from .views import CropViewSet, TransactionViewSet, SubsidyViewSet

router = routers.DefaultRouter()
router.register(r'crops', CropViewSet)
router.register(r'transactions', TransactionViewSet)
router.register(r'subsidies', SubsidyViewSet)

urlpatterns = router.urls
