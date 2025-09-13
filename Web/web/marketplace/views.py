from rest_framework import viewsets
from .models import Crop, Transaction, Subsidy
from .serializers import CropSerializer, TransactionSerializer, SubsidySerializer

class CropViewSet(viewsets.ModelViewSet):
    queryset = Crop.objects.all()
    serializer_class = CropSerializer

class TransactionViewSet(viewsets.ModelViewSet):
    queryset = Transaction.objects.all()
    serializer_class = TransactionSerializer

class SubsidyViewSet(viewsets.ModelViewSet):
    queryset = Subsidy.objects.all()
    serializer_class = SubsidySerializer
