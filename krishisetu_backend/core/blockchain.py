from web3 import Web3
import os

# Connect to local Ganache or Polygon testnet
WEB3_PROVIDER = os.getenv('WEB3_PROVIDER', 'http://127.0.0.1:8545')
w3 = Web3(Web3.HTTPProvider(WEB3_PROVIDER))

# Example: Store transaction hash for crop sale

def record_crop_sale(seller_wallet, buyer_wallet, crop_details):
    # This is a stub. In production, you'd interact with a smart contract.
    tx_hash = w3.keccak(text=f"{seller_wallet}{buyer_wallet}{crop_details}").hex()
    # You would send a transaction and get the real tx_hash here
    return tx_hash

# Example: Verify transaction hash

def verify_transaction(tx_hash):
    # In production, you'd check the blockchain for tx_hash
    return w3.isChecksumAddress(tx_hash) or tx_hash.startswith('0x')
