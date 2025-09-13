from rest_framework import generics
from .models import Loan, Insurance
from .serializers import LoanSerializer, InsuranceSerializer

class LoanListCreateView(generics.ListCreateAPIView):
    queryset = Loan.objects.all()
    serializer_class = LoanSerializer

class InsuranceListCreateView(generics.ListCreateAPIView):
    queryset = Insurance.objects.all()
    serializer_class = InsuranceSerializer

class LoanDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Loan.objects.all()
    serializer_class = LoanSerializer

class InsuranceDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Insurance.objects.all()
    serializer_class = InsuranceSerializer
