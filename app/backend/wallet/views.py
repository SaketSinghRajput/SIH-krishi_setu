from rest_framework import generics, permissions
from .models import Wallet
from .serializers import WalletSerializer

class WalletRetrieveUpdateView(generics.RetrieveUpdateAPIView):
    queryset = Wallet.objects.all()
    serializer_class = WalletSerializer
    permission_classes = [permissions.IsAuthenticated]
